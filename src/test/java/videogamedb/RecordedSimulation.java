package videogamedb;

import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class RecordedSimulation extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://videogamedb.uk")
    .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
    .acceptHeader("image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36");
  
  private Map<CharSequence, String> headers_0 = Map.ofEntries(
    Map.entry("Sec-Fetch-Dest", "image"),
    Map.entry("Sec-Fetch-Mode", "no-cors"),
    Map.entry("Sec-Fetch-Site", "same-origin"),
    Map.entry("Sec-GPC", "1"),
    Map.entry("sec-ch-ua", "Chromium\";v=\"112\", \"Brave\";v=\"112\", \"Not:A-Brand\";v=\"99"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows")
  );
  
  private Map<CharSequence, String> headers_1 = Map.ofEntries(
    Map.entry("Sec-Fetch-Dest", "empty"),
    Map.entry("Sec-Fetch-Mode", "cors"),
    Map.entry("Sec-Fetch-Site", "same-origin"),
    Map.entry("Sec-GPC", "1"),
    Map.entry("accept", "application/json"),
    Map.entry("sec-ch-ua", "Chromium\";v=\"112\", \"Brave\";v=\"112\", \"Not:A-Brand\";v=\"99"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows")
  );
  
  private Map<CharSequence, String> headers_6 = Map.ofEntries(
    Map.entry("Content-Type", "application/json"),
    Map.entry("Origin", "https://videogamedb.uk"),
    Map.entry("Sec-Fetch-Dest", "empty"),
    Map.entry("Sec-Fetch-Mode", "cors"),
    Map.entry("Sec-Fetch-Site", "same-origin"),
    Map.entry("Sec-GPC", "1"),
    Map.entry("accept", "application/json"),
    Map.entry("sec-ch-ua", "Chromium\";v=\"112\", \"Brave\";v=\"112\", \"Not:A-Brand\";v=\"99"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows")
  );
  
  private Map<CharSequence, String> headers_9 = Map.ofEntries(
    Map.entry("Content-Type", "application/json"),
    Map.entry("Origin", "https://videogamedb.uk"),
    Map.entry("Sec-Fetch-Dest", "empty"),
    Map.entry("Sec-Fetch-Mode", "cors"),
    Map.entry("Sec-Fetch-Site", "same-origin"),
    Map.entry("Sec-GPC", "1"),
    Map.entry("accept", "application/json"),
    Map.entry("authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY4MzA0NzIwMCwiZXhwIjoxNjgzMDUwODAwfQ.MZxT_7wL9UpL78Iw5C8RHLeS7B_nX9wNN5GAmrXJNhU"),
    Map.entry("sec-ch-ua", "Chromium\";v=\"112\", \"Brave\";v=\"112\", \"Not:A-Brand\";v=\"99"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows")
  );
  
  private Map<CharSequence, String> headers_13 = Map.ofEntries(
    Map.entry("Origin", "https://videogamedb.uk"),
    Map.entry("Sec-Fetch-Dest", "empty"),
    Map.entry("Sec-Fetch-Mode", "cors"),
    Map.entry("Sec-Fetch-Site", "same-origin"),
    Map.entry("Sec-GPC", "1"),
    Map.entry("accept", "application/json"),
    Map.entry("authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY4MzA0NzIwMCwiZXhwIjoxNjgzMDUwODAwfQ.MZxT_7wL9UpL78Iw5C8RHLeS7B_nX9wNN5GAmrXJNhU"),
    Map.entry("sec-ch-ua", "Chromium\";v=\"112\", \"Brave\";v=\"112\", \"Not:A-Brand\";v=\"99"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows")
  );


  private ScenarioBuilder scn = scenario("RecordedSimulation")
    .exec(
      http("request_0")
        .get("/swagger-ui/favicon-32x32.png?v=3.0.4")
        .headers(headers_0)
    )
    .pause(2)
    .exec(
      http("request_1")
        .get("/api/videogame")
        .headers(headers_1)
    )
    .pause(313)
    .exec(
      http("request_2")
        .get("/swagger-ui/favicon-32x32.png?v=3.0.4")
        .headers(headers_0)
    )
    .pause(6)
    .exec(
      http("request_3")
        .get("/swagger-ui/favicon-32x32.png?v=3.0.4")
        .headers(headers_0)
    )
    .pause(4)
    .exec(
      http("request_4")
        .get("/api/videogame/2")
        .headers(headers_1)
    )
    .pause(4)
    .exec(
      http("request_5")
        .get("/swagger-ui/favicon-32x32.png?v=3.0.4")
        .headers(headers_0)
    )
    .pause(3)
    .exec(
      http("request_6")
        .post("/api/authenticate")
        .headers(headers_6)
        .body(RawFileBody("videogamedb/recordedsimulation/0006_request.json"))
    )
    .pause(28)
    .exec(
      http("request_7")
        .get("/swagger-ui/favicon-32x32.png?v=3.0.4")
        .headers(headers_0)
    )
    .pause(5)
    .exec(
      http("request_8")
        .get("/swagger-ui/favicon-32x32.png?v=3.0.4")
        .headers(headers_0)
    )
    .pause(7)
    .exec(
      http("request_9")
        .post("/api/videogame")
        .headers(headers_9)
        .body(RawFileBody("videogamedb/recordedsimulation/0009_request.json"))
    )
    .pause(11)
    .exec(
      http("request_10")
        .get("/swagger-ui/favicon-32x32.png?v=3.0.4")
        .headers(headers_0)
    )
    .pause(5)
    .exec(
      http("request_11")
        .put("/api/videogame/3")
        .headers(headers_9)
        .body(RawFileBody("videogamedb/recordedsimulation/0011_request.json"))
    )
    .pause(3)
    .exec(
      http("request_12")
        .get("/swagger-ui/favicon-32x32.png?v=3.0.4")
        .headers(headers_0)
    )
    .pause(4)
    .exec(
      http("request_13")
        .delete("/api/videogame/3")
        .headers(headers_13)
    );

  {
	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
  }
}
