package org.jag.Controller;

import org.jag.Model.Product;
import org.jag.utils.AppExecption;
import org.jag.utils.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.jag.utils.Appinput.enterInt;
import static org.jag.utils.Utils.println;

public class CategoryController {
    private final HomeController homeController;
    private final CartController cartController;

    public CategoryController(HomeController homeController) {
        this.homeController = homeController;
        this.cartController = new CartController(homeController);
    }

    public void chooseCategory() {

        try {
            int categoryChoice = 0;
            categoryChoice = enterInt(StringUtils.ENTER_CHOICE);
            switch (categoryChoice) {
                case 1:
                    displayProductsByCategory("Shoe");
                    break;
                case 2:
                    displayProductsByCategory("Watch");
                    break;
                case 3:
                    displayProductsByCategory("Phone");
                    break;
                case 88:
                    checkout();
                    break;
                case 99:
                    homeController.printMenu();
                    break;
                default:
                    invalidChoice(new AppExecption("Invalid category choice."));
            }
            int choice = enterInt(StringUtils.ENTER_CHOICE);
            cartController.addToCart(choice);
        } catch (AppExecption appException) {
            invalidChoice(appException);
        }
    }

    private void displayProductsByCategory(String category) {
        println("Products in " + category + ":");
        List<String[]> products = readProductsFromFile();
        for (String[] product : products) {
            if (product.length >= 5 && product[4].equalsIgnoreCase(category)) {
                println(product[0] + ". " + product[1] + " Price: $" + product[2] + " " + product[3]);
            }
        }


    }

    private List<String[]> readProductsFromFile() {
        List<String[]> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/org/jag/Assets/Product.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] productArray = line.split(",");
                products.add(productArray);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }

    private void checkout() {

    }

    private void invalidChoice(AppExecption e) {
        println(e.getMessage());
    }

}