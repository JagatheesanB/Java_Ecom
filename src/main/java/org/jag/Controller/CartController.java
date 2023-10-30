package org.jag.Controller;

import org.jag.Controller.impl.ICartController;
import org.jag.Model.Cart;
import org.jag.Model.CartProduct;
import org.jag.Model.Product;
import org.jag.Model.User;
import org.jag.utils.AppExecption;
import org.jag.utils.StringUtils;
import org.jag.view.CartPage;


import java.util.ArrayList;

import static org.jag.utils.Appinput.enterInt;
import static org.jag.utils.LoadUtils.getProducts;
import static org.jag.utils.UserUtils.getLoggedInUser;
import static org.jag.utils.UserUtils.setLoggedInUser;
import static org.jag.utils.Utils.println;

public class CartController implements ICartController {
    private final HomeController homeController;
    private final OrderController orderController;
    private final CartPage cartPage;

    public CartController(HomeController homeController) {
        this.homeController = homeController;
        orderController = new OrderController(homeController);
        cartPage = new CartPage();
    }

    @Override
    public void addToCart(int ProductId) {
        User loggedInUser = getLoggedInUser();
        ArrayList<Product> products = getProducts();

        Product userProduct = null;
        for (Product product : products) {
            if (product.getId() == ProductId) {
                userProduct = product;
                break;
            }
        }

        if (loggedInUser.getUserCart() != null) {
            Cart cart = loggedInUser.getUserCart();

            boolean isFound = false;
            for (CartProduct cartProduct : cart.getCartProducts()) {
                if (cartProduct.getProduct().getId() == ProductId) {
                    cartProduct.setCount(cartProduct.getCount() + 1);
                    isFound = true;
                }
            }

            if (!isFound) {
                cart.getCartProducts().add(new CartProduct(userProduct, 1));
            }

            loggedInUser.setUserCart(cart);
        } else {
            Cart cart = new Cart();
            ArrayList<CartProduct> cartProducts = new ArrayList<>();
            cartProducts.add(new CartProduct(userProduct, 1));
            cart.setCartProducts(cartProducts);
            loggedInUser.setUserCart(cart);
        }
        setLoggedInUser(loggedInUser);

    }

    public void printCart() {
        User loggedInUser = getLoggedInUser();
        if (loggedInUser.getUserCart() == null) {
            cartPage.printEmptyCart();
            homeController.printMenu();
        } else {
            ArrayList<CartProduct> cartProducts = loggedInUser.getUserCart().getCartProducts();
            cartPage.printCart(cartProducts);

            cartPage.printCheckout();
            cartPage.printBack();

            try {
                int choice = enterInt(StringUtils.ENTER_CHOICE);
                if (choice == 88) {
                    checkout();
                } else if (choice == 99) {
                    homeController.printMenu();
                } else {
                    invalidChoice(new AppExecption(StringUtils.INVALID_CHOICE));
                }
            } catch (AppExecption appExecption) {
                invalidChoice(appExecption);
            }

        }
    }

    private void checkout() {

    }

    private void invalidChoice(AppExecption appExecption) {
        println(appExecption.getMessage());
        printCart();
    }
}
