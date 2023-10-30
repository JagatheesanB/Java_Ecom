package org.jag.utils;

import java.io.File;

public class FileUtils {
    private static File credentailsFile;
    private static File productFile;

    public static File getCredentialsFile() {
        if (credentailsFile == null)
            credentailsFile = new File("src/main/java/org/jag/Assets/credentials.csv");
        return credentailsFile;
    }

    public static File ProductFile() {
        if (productFile == null) {
            productFile = new File("src/main/java/org/jag/Assets/Product.csv");
        }
        return productFile;
    }

    public static String getFilePath() {
        return "src/main/java/org/jag/Assets/";
    }
}