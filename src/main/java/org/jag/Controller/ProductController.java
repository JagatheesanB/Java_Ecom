package org.jag.Controller;

import org.jag.Controller.impl.IHomeController;
import org.jag.Controller.impl.IProductController;
import org.jag.Model.Product;

import org.jag.utils.AppExecption;
import org.jag.utils.FileUtils;
import org.jag.utils.StringUtils;
import org.jag.view.ProductsPage;

import java.util.ArrayList;
import java.util.Scanner;

import static org.jag.utils.Appinput.enterInt;
import static org.jag.utils.LoadUtils.getProducts;
import static org.jag.utils.Utils.println;


public class ProductController implements IProductController {


    private int categoryId = 0;
    private final ProductsPage productsPage;
    private final HomeController homeController;
    private final CartController cartController;

    public ProductController(HomeController homeController) {
        this.homeController = homeController;
        productsPage = new ProductsPage();
        cartController = new CartController(homeController);

    }

    public Product loadproduct() {
        try {
            Scanner scanner = new Scanner(FileUtils.ProductFile());
            while (scanner.hasNext()) {
                String product = scanner.next().trim();
                if (!product.startsWith("id")) {
                    String[] userArray = product.split(",");
                    println(userArray[0] + ". " + userArray[1] + " $" + userArray[2] + " " + userArray[3]);
                }
            }
        } catch (Exception e) {

        }
        return null;
    }


    private void invalidChoice(AppExecption appExecption) {
        println(appExecption.getMessage());
        showProducts(categoryId);
    }

    @Override
    public void showProducts(int categoryId) {
        this.categoryId = categoryId;
        ArrayList<Product> products = getProducts();
        if (categoryId != 0) {
            ArrayList<Product> categoryProducts = new ArrayList<>();
            for (Product product : products) {
                if (product.getCategory().getId() == categoryId) {
                    categoryProducts.add(product);
                }
            }
            products = categoryProducts;
        }

        productsPage.printProducts(products);

        try {
            int choice = enterInt(StringUtils.ENTER_CHOICE);
            int validProductId = 0;

            if (choice == 99) {
                homeController.printMenu();
            } else {
                for (Product product : products) {
                    if (product.getId() == choice) {
                        validProductId = product.getId();
                        break;
                    }
                }

                if (validProductId != 0) {
                    cartController.addToCart(validProductId);
                    productsPage.printSuccess();
                    showProducts(categoryId);
                } else {
                    invalidChoice(new AppExecption(StringUtils.INVALID_CHOICE));
                }
            }
        } catch (AppExecption appException) {
            invalidChoice(appException);
        }
    }
}