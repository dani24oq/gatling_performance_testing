package videogamedb.scriptfundamentals;

import io.gatling.javaapi.core.Simulation;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.time.Duration;
import java.util.List;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
public class VideoGameDb extends Simulation {

    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("https://videogamedb.uk/api")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");

    private static ChainBuilder authenticate =
            exec(http("Authenticate")
                    .post("/authenticate")
                    .body(StringBody("{\n" +
                                        "  \"password\": \"admin\",\n" +
                                        "  \"username\": \"admin\"\n" +
                                        "}"))
                    .check(jmesPath("token").saveAs("jwt_token")));

    private static ChainBuilder createNewGame =
            exec(http("Create new game")
                    .post("/videogame")
                    .header("Authorization","Bearer #{jwt_token}")
                    .body(StringBody("{\n" +
                                        "  \"category\": \"Platform\",\n" +
                                        "  \"name\": \"Mario\",\n" +
                                        "  \"rating\": \"Mature\",\n" +
                                        "  \"releaseDate\": \"2012-05-04\",\n" +
                                        "  \"reviewScore\": 85\n" +
                                        "}")));

    private static ChainBuilder getAllVideoGames =
            repeat(3).on(
                    exec(http("Get all video game")
                            .get("/videogame")
                            .check(status().not(404), status().not(500)))
            );

    private static ChainBuilder getSpecificVideoGame =
            repeat(5,"myCounter").on(
                    exec(http("Get specific video game with ID - #{myCounter}")
                            .get("/videogame/#{myCounter}")
                            .check(status().is(200)))
            );


    private ScenarioBuilder scn = scenario("Video Game Db - Section 5 code")
            .exec(authenticate)
            .exec(createNewGame);

    {
        setUp(
                scn.injectOpen(atOnceUsers(1))
        ).protocols(httpProtocol);
    }
}
