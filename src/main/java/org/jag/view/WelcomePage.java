package org.jag.view;

import org.jag.utils.StringUtils;

import static org.jag.utils.Utils.println;

public class WelcomePage {
    public void welcome() {
        try {
            println("*************************************");
            println(StringUtils.WELCOME_MESSAGE);
            println("*************************************");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void printAuthMenu() {
        println("************");
        println(StringUtils.AUTH_MENU);
        println("************");
    }

}