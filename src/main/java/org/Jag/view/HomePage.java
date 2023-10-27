package org.Jag.view;

import org.Jag.utills.StringUtil;

import static org.Jag.utills.Utils.println;

public class HomePage {
    public void printMenu() {
        try {
            println("#---------------------#");
            println(StringUtil.HOME_MENU);
            println("#---------------------#");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
