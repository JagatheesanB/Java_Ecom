package org.Jag.view;

import static org.Jag.utills.Utils.println;

public class LoginPage {
    public void printInvalidCredentials() {
        try {
            println("<--------------------->");
            println("Invalid Credentials!!!");
            println("<--------------------->");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
