package acetoys;

import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class AceToysSimulation extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://acetoys.uk")
    .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36");
  
  private Map<CharSequence, String> headers_0 = Map.ofEntries(
    Map.entry("Sec-Fetch-Dest", "document"),
    Map.entry("Sec-Fetch-Mode", "navigate"),
    Map.entry("Sec-Fetch-Site", "same-origin"),
    Map.entry("Sec-Fetch-User", "?1"),
    Map.entry("Sec-GPC", "1"),
    Map.entry("Upgrade-Insecure-Requests", "1"),
    Map.entry("sec-ch-ua", "Brave\";v=\"113\", \"Chromium\";v=\"113\", \"Not-A.Brand\";v=\"24"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows")
  );
  
  private Map<CharSequence, String> headers_7 = Map.ofEntries(
    Map.entry("Accept", "*/*"),
    Map.entry("Sec-Fetch-Dest", "empty"),
    Map.entry("Sec-Fetch-Mode", "cors"),
    Map.entry("Sec-Fetch-Site", "same-origin"),
    Map.entry("Sec-GPC", "1"),
    Map.entry("X-Requested-With", "XMLHttpRequest"),
    Map.entry("sec-ch-ua", "Brave\";v=\"113\", \"Chromium\";v=\"113\", \"Not-A.Brand\";v=\"24"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows")
  );
  
  private Map<CharSequence, String> headers_12 = Map.ofEntries(
    Map.entry("Cache-Control", "max-age=0"),
    Map.entry("Origin", "https://acetoys.uk"),
    Map.entry("Sec-Fetch-Dest", "document"),
    Map.entry("Sec-Fetch-Mode", "navigate"),
    Map.entry("Sec-Fetch-Site", "same-origin"),
    Map.entry("Sec-Fetch-User", "?1"),
    Map.entry("Sec-GPC", "1"),
    Map.entry("Upgrade-Insecure-Requests", "1"),
    Map.entry("sec-ch-ua", "Brave\";v=\"113\", \"Chromium\";v=\"113\", \"Not-A.Brand\";v=\"24"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows")
  );


  private ScenarioBuilder scn = scenario("AceToysSimulation")
    .exec(
      http("AceToysSimulation_0:GET_https://acetoys.uk/")
        .get("/")
        .headers(headers_0)
    )
    .pause(5)
    .exec(
      http("AceToysSimulation_1:GET_https://acetoys.uk/our-story")
        .get("/our-story")
        .headers(headers_0)
    )
    .pause(3)
    .exec(
      http("AceToysSimulation_2:GET_https://acetoys.uk/get-in-touch")
        .get("/get-in-touch")
        .headers(headers_0)
    )
    .pause(7)
    .exec(
      http("AceToysSimulation_3:GET_https://acetoys.uk/category/all")
        .get("/category/all")
        .headers(headers_0)
    )
    .pause(6)
    .exec(
      http("AceToysSimulation_4:GET_https://acetoys.uk/category/all?page=1")
        .get("/category/all?page=1")
        .headers(headers_0)
    )
    .pause(3)
    .exec(
      http("AceToysSimulation_5:GET_https://acetoys.uk/category/all?page=2")
        .get("/category/all?page=2")
        .headers(headers_0)
    )
    .pause(5)
    .exec(
      http("AceToysSimulation_6:GET_https://acetoys.uk/product/darts-board")
        .get("/product/darts-board")
        .headers(headers_0)
    )
    .pause(5)
    .exec(
      http("AceToysSimulation_7:GET_https://acetoys.uk/cart/add/19")
        .get("/cart/add/19")
        .headers(headers_7)
    )
    .pause(6)
    .exec(
      http("AceToysSimulation_8:GET_https://acetoys.uk/category/babies-toys")
        .get("/category/babies-toys")
        .headers(headers_0)
    )
    .pause(34)
    .exec(
      http("AceToysSimulation_9:GET_https://acetoys.uk/cart/add/4")
        .get("/cart/add/4")
        .headers(headers_7)
    )
    .pause(1)
    .exec(
      http("AceToysSimulation_10:GET_https://acetoys.uk/cart/add/4")
        .get("/cart/add/4")
        .headers(headers_7)
    )
    .pause(10)
    .exec(
      http("AceToysSimulation_11:GET_https://acetoys.uk/cart/view")
        .get("/cart/view")
        .headers(headers_0)
    )
    .pause(10)
    .exec(
      http("AceToysSimulation_12:POST_https://acetoys.uk/login")
        .post("/login")
        .headers(headers_12)
        .formParam("_csrf", "8fd3fbe6-c0e7-492a-98e2-eab06a45cbd6")
        .formParam("username", "user1")
        .formParam("password", "pass")
    )
    .pause(6)
    .exec(
      http("AceToysSimulation_13:GET_https://acetoys.uk/cart/add/19?cartPage=true")
        .get("/cart/add/19?cartPage=true")
        .headers(headers_0)
    )
    .pause(4)
    .exec(
      http("AceToysSimulation_14:GET_https://acetoys.uk/cart/add/19?cartPage=true")
        .get("/cart/add/19?cartPage=true")
        .headers(headers_0)
    )
    .pause(2)
    .exec(
      http("AceToysSimulation_15:GET_https://acetoys.uk/cart/subtract/19")
        .get("/cart/subtract/19")
        .headers(headers_0)
    )
    .pause(12)
    .exec(
      http("AceToysSimulation_16:GET_https://acetoys.uk/cart/checkout")
        .get("/cart/checkout")
        .headers(headers_0)
    )
    .pause(6)
    .exec(
      http("AceToysSimulation_17:POST_https://acetoys.uk/logout")
        .post("/logout")
        .headers(headers_12)
        .formParam("_csrf", "11ecf7ec-5d64-4c1a-8825-eb81aafadb13")
    );

  {
	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
  }
}
