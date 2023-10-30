package org.jag.Controller;

import org.jag.Controller.impl.IHomeController;

import org.jag.Controller.impl.IProductController;
import org.jag.utils.AppExecption;
import org.jag.utils.StringUtils;
import org.jag.view.HomePage;

import static org.jag.utils.Appinput.enterInt;
import static org.jag.utils.UserUtils.setLoggedInUser;
import static org.jag.utils.Utils.println;

public class HomeController implements IHomeController {

    private final HomePage homePage;
    private final AuthController authController;
    private final ProductController productController;
    private final CartController cartController;
    private final OrderController orderController;
    private final CategoryController categoryController;

    public HomeController(AuthController authController) {
        homePage = new HomePage();
        this.authController = authController;
        productController = new ProductController(this);
        cartController = new CartController(this);
        orderController = new OrderController(this);
        categoryController = new CategoryController(this);
    }

    @Override
    public void printMenu() {
        homePage.printMenu();
        try {
            int choice = enterInt(StringUtils.ENTER_CHOICE);
            switch (choice) {
                case 1:
                    displayCategories();
                    break;
                case 2:
                    productController.loadproduct();
                    productController.showProducts(0);
                    break;
                case 3:
                    cartController.printCart();
                    break;
                case 4:
                    printMenu();
                    break;
                case 5:
                    setLoggedInUser(null);
                    authController.authMenu();
                    break;
                default:
                    invalidChoice(new AppExecption(StringUtils.INVALID_CHOICE));
            }
        } catch (AppExecption appException) {
            invalidChoice(appException);
        }
    }

    private void displayCategories() {
        println("1. Shoe");
        println("2. Watch");
        println("3. Phone");
        categoryController.chooseCategory();
    }


    private void invalidChoice(AppExecption appException) {
        println(appException.getMessage());
        printMenu();
    }


}