package acetoys.pageobjects;

import io.gatling.javaapi.core.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class Category {

    public static ChainBuilder productListByCategory_AllProducts =
            exec(
                    http("Load Product List Page - Category: All Products")
                            .get("/category/all")
                            .check(css("#CategoryName").is("All Products"))
            );

    public static ChainBuilder productListByCategory_BabiesToys =
            exec(
                    http("Load Product List Page - Category: Babies Toys")
                            .get("/category/babies-toys")
                            .check(css("#CategoryName").is("Babies Toys"))
            );

    public static ChainBuilder loadSecondPageOfProducts =
            exec(
                    http("Load Second Page of Products")
                            .get("/category/all?page=1")
                            .check(css(".page-item.active").is("2"))
            );

    public static ChainBuilder loadThirdPageOfProducts =
            exec(
                    http("Load Third Page of Products")
                            .get("/category/all?page=2")
                            .check(css(".page-item.active").is("3"))
            );
}
