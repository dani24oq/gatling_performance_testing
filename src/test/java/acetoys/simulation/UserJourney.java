package acetoys.simulation;

import acetoys.pageobjects.*;
import acetoys.session.UserSession;
import io.gatling.core.pause.Custom;
import io.gatling.javaapi.core.*;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class UserJourney {
    private static final Duration SHORT_PAUSE = Duration.ofMillis(1000);
    private static final Duration LONG_PAUSE = Duration.ofMillis(3000);

    public static ChainBuilder browseStore =
            exec(UserSession.initSession)
                    .exec(StaticPages.homePage)
                    .pause(LONG_PAUSE)
                    .exec(StaticPages.ourStory)
                    .pause(SHORT_PAUSE, LONG_PAUSE)
                    .exec(StaticPages.getInTouch)
                    .pause(SHORT_PAUSE, LONG_PAUSE)
                    .repeat(3).on(
                            exec(Category.productListByCategory)
                                    .pause(SHORT_PAUSE, LONG_PAUSE)
                                    .exec(Category.cyclePagesOfProducts)
                                    .pause(SHORT_PAUSE, LONG_PAUSE)
                                    .exec(Product.loadProductDetailsPage)
                    );

    public static ChainBuilder abandonBasket =
            exec(UserSession.initSession)
                    .exec(StaticPages.homePage)
                    .pause(SHORT_PAUSE, LONG_PAUSE)
                    .exec(Category.productListByCategory)
                    .pause(SHORT_PAUSE, LONG_PAUSE)
                    .exec(Product.loadProductDetailsPage)
                    .pause(SHORT_PAUSE, LONG_PAUSE)
                    .exec(Product.addProductToCart);

    public static ChainBuilder completePurchase =
            exec(UserSession.initSession)
                    .exec(StaticPages.homePage)
                    .pause(SHORT_PAUSE, LONG_PAUSE)
                    .exec(Category.productListByCategory)
                    .pause(SHORT_PAUSE, LONG_PAUSE)
                    .exec(Product.loadProductDetailsPage)
                    .pause(SHORT_PAUSE, LONG_PAUSE)
                    .exec(Product.addProductToCart)
                    .pause(SHORT_PAUSE, LONG_PAUSE)
                    .exec(Cart.viewCart)
                    .pause(SHORT_PAUSE, LONG_PAUSE)
                    .exec(Cart.increaseQuantityInCart)
                    .pause(SHORT_PAUSE)
                    .exec(Cart.decreaseQuantityInCart)
                    .pause(SHORT_PAUSE)
                    .exec(Cart.checkout)
                    .pause(SHORT_PAUSE, LONG_PAUSE)
                    .exec(Customer.logout);

}
