package videogamedb.finalsimulation;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class VideoGameDbFullTest extends Simulation {

    //HTTP Protocol

    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("https://videogamedb.uk/api")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");

    //Runtime parameters

    private static final int USERS = Integer.parseInt(System.getProperty("USERS","5"));
    private static final int RAMP_DURATION = Integer.parseInt(System.getProperty("RAMP_DURATION", "10"));
    private static final int TEST_DURATION = Integer.parseInt(System.getProperty("TEST_DURATION", "20"));

    @Override
    public void before(){
        System.out.printf("Running test with %d users%n", USERS);
        System.out.printf("Ramping users over %d seconds%n", RAMP_DURATION);
        System.out.printf("Total test duration %d seconds%n", TEST_DURATION);
    }

    //Feeder for test - JSON

    private static FeederBuilder.FileBased<Object> jsonFeeder = jsonFile("data/gameJsonFile.json").random();

    //HTTP Calls

    private static ChainBuilder getAllVideoGames =
            exec(http("Get all video games")
                    .get("/videogame")
                    .check(bodyString().saveAs("responseBody")));

    private static ChainBuilder authenticate =
            exec(http("Authenticate")
                    .post("/authenticate")
                    .body(StringBody("{\n" +
                                        "  \"password\": \"admin\",\n" +
                                        "  \"username\": \"admin\"\n" +
                                        "}"))
                    .check(jmesPath("token").saveAs("jwtToken")));

    private static ChainBuilder createNewGame =
            feed(jsonFeeder)
                    .exec(http("Create new game - #{name}")
                    .post("/videogame")
                            .header("Authorization","Bearer #{jwtToken}")
                            .body(ElFileBody("bodies/newGameTemplate.json")).asJson()
                            .check(bodyString().saveAs("responseBody2")))

                    .exec(session -> {
                        System.out.println(session.getString("responseBody2"));
                        return session;
                    });

    private static ChainBuilder getLastPostedGame =
            exec(http("Get last posted game - #{name}")
                    .get("/videogame/#{id}")
                    .check(jmesPath("name").isEL("#{name}")));

    private static ChainBuilder deleteNewVideoGame =
            exec(http("Delete new video game - #{name}")
                    .delete("/videogame/#{id}")
                    .header("Authorization","Bearer #{jwtToken}")
                    .check(bodyString().is("Video game deleted")));


    //Scenario

    private ScenarioBuilder scn = scenario("Final simulation")
            .forever().on(
                    exec(getAllVideoGames)
                            .pause(2)
                            .exec(authenticate)
                            .pause(1)
                            .exec(createNewGame)
                            .pause(2)
                            .exec(getLastPostedGame)
                            .pause(2)
                            .exec(deleteNewVideoGame)
            );


    //Load simulation

    {
        setUp(
                scn.injectOpen(
                        nothingFor(5),
                        rampUsers(USERS).during(RAMP_DURATION)
        ).protocols(httpProtocol)).maxDuration(TEST_DURATION);
    }

    @Override
    public void after(){
        System.out.println("Stress simulation test completed");
    }
}
