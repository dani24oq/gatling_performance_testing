package acetoys;

import acetoys.pageobjects.*;
import acetoys.session.UserSession;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class AceToysSimulation extends Simulation {

  private static final String DOMAIN = "acetoys.uk";

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://" + DOMAIN)
    .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("en-US,en;q=0.5");


  private ScenarioBuilder scn = scenario("AceToysSimulation")
          .exec(UserSession.initSession)
          .exec(StaticPages.homePage)
          .pause(2)
          .exec(StaticPages.ourStory)
          .pause(2)
          .exec(StaticPages.getInTouch)
          .pause(2)
          .exec(Category.productListByCategory)
          .pause(2)
          .exec(Category.cyclePagesOfProducts)
          .pause(2)
          .exec(Product.loadProductDetailsPage)
          .pause(2)
          .exec(Product.addProductToCart)
          .pause(2)
          .exec(Category.productListByCategory)
          .pause(2)
          .exec(Product.addProductToCart)
          .pause(2)
          .exec(Cart.viewCart)
          .pause(2)
          .exec(Cart.increaseQuantityInCart)
          .pause(2)
          .exec(Cart.increaseQuantityInCart)
          .pause(2)
          .exec(Cart.decreaseQuantityInCart)
          .pause(2)
          .exec(Cart.checkout)
          .pause(2)
          .exec(Customer.logout);

  {
	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
  }
}
