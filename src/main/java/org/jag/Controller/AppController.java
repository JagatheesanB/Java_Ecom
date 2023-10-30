package org.jag.Controller;

import org.jag.Controller.impl.IAppController;
import org.jag.view.WelcomePage;

public class AppController implements IAppController {
    private final WelcomePage welcomePage;
    private final AuthController authController;

    public AppController() {
        welcomePage = new WelcomePage();

        authController = new AuthController(this);
    }

    @Override
    public void init() {
        welcomePage.welcome();
        authController.authMenu();
    }

    @Override
    public void printAuthMenu() {
        welcomePage.printAuthMenu();
    }
}