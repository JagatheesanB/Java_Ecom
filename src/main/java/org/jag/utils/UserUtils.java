package org.jag.utils;

import org.jag.Model.User;


public class UserUtils {
    private static User loggedInUser;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        UserUtils.loggedInUser = loggedInUser;
    }
}