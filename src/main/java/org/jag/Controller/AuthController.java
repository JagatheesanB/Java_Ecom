package org.jag.Controller;

import org.jag.Controller.impl.IAuthController;
import org.jag.Model.Role;
import org.jag.Model.User;
import org.jag.utils.AppExecption;
import org.jag.utils.StringUtils;
import org.jag.view.LoginPage;
import org.jag.view.RegisterPage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.jag.utils.Appinput.enterString;
import static org.jag.utils.Appinput.enterInt;
import static org.jag.utils.Utils.println;
import static org.jag.utils.FileUtils.getCredentialsFile;

public class AuthController implements IAuthController {
    private final HomeController homeController;
    private final AppController appController;
    private final LoginPage loginPage;
    private final RegisterPage registerPage;
    private final AppExecption appExecption = null;

    // Constructor
    public AuthController(AppController appController) {
        this.appController = appController;
        homeController = new HomeController(this);
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
    }

    // Implementation of authMenu method
    @Override
    public void authMenu() {
        appController.printAuthMenu();
        int choice;
        try {
            choice = enterInt(StringUtils.ENTER_CHOICE);
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                default:
                    invalidChoice(new AppExecption(StringUtils.INVALID_CHOICE));
                    break;
            }

        } catch (AppExecption appException) {
            invalidChoice(appException);
        }
    }

    // Implementation of login method
    @Override
    public void login() {
        String email, password;
        email = enterString(StringUtils.ENTER_EMAIL);
        password = enterString(StringUtils.ENTER_PASSWORD);

        User user = validateUser(email, password);
        if (user != null) {
            homeController.printMenu();
        } else {
            loginPage.printInvalidCredentials();
            authMenu();
        }
    }

    // Implementation of register method
    @Override
    public void register() {
        String name, email, password, c_password;
        name = enterString(StringUtils.ENTER_NAME);
        email = enterString(StringUtils.ENTER_EMAIL);
        password = enterString(StringUtils.ENTER_PASSWORD);
        c_password = enterString(StringUtils.ENTER_PASSWORD_AGAIN);

        if (password.equals(c_password)) {
            try {
                FileWriter csvWriter = new FileWriter(getCredentialsFile(), true);
                int id = (int) (Math.random() * 100);
                csvWriter.append("\n");
                csvWriter.append(id + "," + name + "," + email + "," + password);
                csvWriter.flush();
                csvWriter.close();
                registerPage.printRegistrationSuccessful();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            registerPage.passwordMisMatch();
        }
        authMenu();
    }

    // Implementation of logout method
    @Override
    public void logout() {

    }


    private User validateUser(String email, String password) {
        try {
            Scanner scanner = new Scanner(getCredentialsFile());
            while (scanner.hasNextLine()) {
                String value = scanner.nextLine().trim();
                if (!value.startsWith("id")) {
                    String[] userArray = value.split(",");
                    if (userArray.length >= 4 && userArray[2].equals(email) && userArray[3].equals(password)) {
                        User user = new User();
                        user.setId(Integer.parseInt(userArray[0]));
                        user.setName(userArray[1]);
                        user.setEmail(userArray[2]);
                        user.setPassword(userArray[3]);
                        if (user.getEmail().equals("admin@admin.com"))
                            user.setRole(Role.ADMIN);
                        else
                            user.setRole(Role.USER);
                        return user;
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Private method to handle invalid choices
    private void invalidChoice(AppExecption e) {
        println(e.getMessage());
        authMenu();
    }
}