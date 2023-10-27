package org.Jag.view;

import org.Jag.utills.StringUtil;

import static org.Jag.utills.Utils.println;

public class WelcomePage {
    public void welcome() {
        try {
            println(StringUtil.WELCOME_MESSAGE);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void printAuthMenu() {
        println(StringUtil.AUTH_MENU);
    }
}
