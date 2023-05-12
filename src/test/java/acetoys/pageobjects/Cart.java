package acetoys.pageobjects;

import acetoys.session.UserSession;
import io.gatling.javaapi.core.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class Cart {

    public static ChainBuilder viewCart =
            doIf(session -> !session.getBoolean("customerLoggedIn"))
                    .then(exec(Customer.login))
                    .exec(
                            http("View Cart")
                                    .get("/cart/view")
                                    .check(css("#CategoryHeader").is("Cart Overview"))
                    );

    public static ChainBuilder increaseQuantityInCart =
            exec(UserSession.increaseItemsInBasketForSession)
                    .exec(UserSession.increaseSessionBasketTotal)
                    .exec(
                    http("Increase Product Quantity in Cart - Product Name: #{name}")
                            .get("/cart/add/#{id}?cartPage=true")
                            .check(css("#grandTotal").isEL("$#{basketTotal}"))
            );

    public static ChainBuilder decreaseQuantityInCart =
            exec(UserSession.decreaseItemsInBasketForSession)
                    .exec(UserSession.decreaseSessionBasketTotal)
                    .exec(
                    http("Subtract Product Quantity in Cart - Product Name: #{name}")
                            .get("/cart/subtract/#{id}")
                            .check(css("#grandTotal").isEL("$#{basketTotal}"))
            );

    public static ChainBuilder checkout =
            exec(
                    http("Checkout")
                            .get("/cart/checkout")
                            .check(substring("Your products are on their way to you now!!"))
            );
}
