package acetoys;

import acetoys.pageobjects.*;
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
    .exec(StaticPages.homePage)
    .pause(2)
    .exec(StaticPages.ourStory)
    .pause(2)
    .exec(StaticPages.getInTouch)
    .pause(2)
    .exec(Category.productListByCategory_AllProducts)
    .pause(2)
    .exec(Category.loadSecondPageOfProducts)
    .pause(2)
    .exec(Category.loadThirdPageOfProducts)
    .pause(2)
    .exec(Product.loadProductDetailPage_DartBoards)
    .pause(2)
    .exec(Product.addProductToCart_Product19)
    .pause(2)
    .exec(Category.productListByCategory_BabiesToys)
    .pause(2)
    .exec(Product.addProductToCart_Product4)
    .pause(2)
    .exec(Product.addProductToCart_Product5)
    .pause(2)
    .exec(Cart.viewCart)
    .pause(2)
    .exec(Customer.login)
    .pause(2)
    .exec(Cart.increaseQuantityInCart)
    .pause(2)
    .exec(Cart.increaseQuantityInCart)
    .pause(2)
    .exec(Cart.decreaseQuantityInCart)
    .pause(2)
    .exec(Cart.checkOut)
    .pause(2)
    .exec(Customer.logout);

  {
	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
  }
}
