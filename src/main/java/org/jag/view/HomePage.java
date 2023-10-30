package org.jag.view;

import org.jag.utils.StringUtils;

import static org.jag.utils.Utils.println;

public class HomePage {
    public void printMenu() {
        try {
            println("#---------------------#");
            println(StringUtils.HOME_MENU);
            println("#---------------------#");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
