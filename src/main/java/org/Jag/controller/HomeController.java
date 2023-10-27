package org.Jag.controller;

import org.Jag.controller.impl.IHomeController;
import org.Jag.utills.AppException;
import org.Jag.utills.StringUtil;
import org.Jag.view.HomePage;

import static org.Jag.utills.AppInput.enterInt;
import static org.Jag.utills.Utils.println;

public class HomeController implements IHomeController {

    private final HomePage homePage;
    private final AuthController authController;
    private final CategoryController categoryController;

    public HomeController(AuthController authController) {
        homePage = new HomePage();
        this.authController = authController;
        categoryController = new CategoryController(this);
    }

    @Override
    public void printMenu() {
        homePage.printMenu();
        try {
            int choice = enterInt(StringUtil.ENTER_CHOICE);
            if (choice == 1) {
            } else if (choice == 2) {

            } else if (choice == 3) {

            } else if (choice == 4) {

            } else if (choice == 5) {

            } else {
                invalidChoice(new AppException(StringUtil.INVALID_CHOICE));
            }
        }catch (AppException appException){
            invalidChoice(appException);
        }

    }

    private void invalidChoice(AppException appException) {
        println(appException.getMessage());
        printMenu();
    }
}
