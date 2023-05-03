package videogamedb;

import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class RecordedSimulationProxy extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://videogamedb.uk")
    .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("PostmanRuntime/7.32.2");
  
  private Map<CharSequence, String> headers_0 = Map.of("Postman-Token", "73eac571-428a-4f61-83c1-2ebc4bf0c473");
  
  private Map<CharSequence, String> headers_1 = Map.of("Postman-Token", "4e4e1d2b-4a6a-4a2a-a328-1b67abb47baa");
  
  private Map<CharSequence, String> headers_2 = Map.ofEntries(
    Map.entry("Content-Type", "application/json"),
    Map.entry("Postman-Token", "33852457-d002-471c-81ac-200a34b30575")
  );
  
  private Map<CharSequence, String> headers_3 = Map.ofEntries(
    Map.entry("Content-Type", "application/json"),
    Map.entry("Postman-Token", "5557e9f7-a2fb-4199-b0f2-94488c0d081a"),
    Map.entry("authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY4MzA3MzA4MiwiZXhwIjoxNjgzMDc2NjgyfQ.rcRFwXCTae-zIKXQPQw6hbdogC2nqbRwOfpRJGBDqoo")
  );
  
  private Map<CharSequence, String> headers_4 = Map.ofEntries(
    Map.entry("Content-Type", "application/json"),
    Map.entry("Postman-Token", "92537cf6-ad3e-4218-ae02-5149225ffe2f"),
    Map.entry("authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY4MzA3MzA4MiwiZXhwIjoxNjgzMDc2NjgyfQ.rcRFwXCTae-zIKXQPQw6hbdogC2nqbRwOfpRJGBDqoo")
  );
  
  private Map<CharSequence, String> headers_5 = Map.ofEntries(
    Map.entry("Postman-Token", "fad93940-2566-49c5-b24d-05956c00197a"),
    Map.entry("authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY4MzA3MzA4MiwiZXhwIjoxNjgzMDc2NjgyfQ.rcRFwXCTae-zIKXQPQw6hbdogC2nqbRwOfpRJGBDqoo")
  );
  
  private Map<CharSequence, String> headers_6 = Map.ofEntries(
    Map.entry("Content-Type", "application/json"),
    Map.entry("Postman-Token", "90723ca9-c9b0-4b92-ba80-3e5c2eb05360"),
    Map.entry("authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY4MzA3MzA4MiwiZXhwIjoxNjgzMDc2NjgyfQ.rcRFwXCTae-zIKXQPQw6hbdogC2nqbRwOfpRJGBDqoo")
  );
  
  private Map<CharSequence, String> headers_7 = Map.ofEntries(
    Map.entry("Content-Type", "application/json"),
    Map.entry("Postman-Token", "2d0a5bf2-2a96-4394-8a8a-be1a50fadc29"),
    Map.entry("authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY4MzA3MzA4MiwiZXhwIjoxNjgzMDc2NjgyfQ.rcRFwXCTae-zIKXQPQw6hbdogC2nqbRwOfpRJGBDqoo")
  );


  private ScenarioBuilder scn = scenario("RecordedSimulationProxy")
    .exec(
      http("request_0")
        .get("/api/videogame")
        .headers(headers_0)
    )
    .pause(3)
    .exec(
      http("request_1")
        .get("/api/videogame/2")
        .headers(headers_1)
    )
    .pause(22)
    .exec(
      http("request_2")
        .post("/api/authenticate")
        .headers(headers_2)
        .body(RawFileBody("videogamedb/recordedsimulationproxy/0002_request.json"))
    )
    .pause(24)
    .exec(
      http("request_3")
        .post("/api/videogame")
        .headers(headers_3)
        .body(RawFileBody("videogamedb/recordedsimulationproxy/0003_request.json"))
    )
    .pause(12)
    .exec(
      http("request_4")
        .put("/api/videogame/3")
        .headers(headers_4)
        .body(RawFileBody("videogamedb/recordedsimulationproxy/0004_request.json"))
    )
    .pause(3)
    .exec(
      http("request_5")
        .delete("/api/videogame/2")
        .headers(headers_5)
    )
    .pause(17)
    .exec(
      http("request_6")
        .post("/api/videogame/2")
        .headers(headers_6)
        .body(RawFileBody("videogamedb/recordedsimulationproxy/0006_request.json"))
        .check(status().is(405))
        .resources(
          http("request_7")
            .post("/api/videogame/2")
            .headers(headers_7)
            .body(RawFileBody("videogamedb/recordedsimulationproxy/0007_request.json"))
            .check(status().is(405))
        )
    );

  {
	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
  }
}
