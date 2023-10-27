package org.Jag.utills;

import java.io.File;

public class FileUtil {

    private static File credentailsFile;

    public static File getCredentailsFile() {
        if (credentailsFile == null)
            credentailsFile = new File("src/main/java/org/Jag/assets/credentials.csv");
        return credentailsFile;
    }

    private static File productsFile;

    public static File getProductsFile() {
        if (productsFile == null)
            productsFile = new File("src/main/java/org/Jag/assets/Product.csv");
        return productsFile;
    }
}
