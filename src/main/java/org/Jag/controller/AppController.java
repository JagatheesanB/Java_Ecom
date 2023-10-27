package org.Jag.controller;

import org.Jag.controller.impl.IAppController;
import org.Jag.view.WelcomePage;

public class AppController implements IAppController {

    private  final WelcomePage welcomePage;
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
