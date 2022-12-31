package by.it_academy.onliner.functional.pageobject;


import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static by.it_academy.onliner.functional.pageobject.BasePage.driver;

public class ShoppingCartTest extends BaseTest {
    private static final String SHOPPING_CART_CONTENT_QUANTITY_PRODUCTS = "1 товар";
    private static final String TARGET_PRODUCT = "Смартфон Samsung Galaxy A52 SM-A525F/DS 4GB/128GB (черный)";
    private static final String MESSAGE_OF_REMOVAL = "Вы удалили";
    private static ProductPage productPage;
    private static ShoppingCart shoppingCart;

    @BeforeClass
    @Description("Navigate to Onliner, get popup window with searching field, search product")
    public static ProductPage getProductPage() {
        return productPage = OnlinerPageNavigation.navigateToOnlinerPage()
                .typeTextInSearchField(" ")
                .obtainPopupWindow()
                .cleanSearchingField()
                .obtainProductPage(TARGET_PRODUCT);
    }

    @Test(priority = 1)
    @Description("Test product in shopping cart equals to target product")
    public static void testCheckCorrectProductIsInShoppingCart() {
        driver.get().switchTo().defaultContent();
        productPage.obtainProductSuppliers();
        productPage.obtainToShoppingCartButton().click();
        productPage.confirmMinskLocationPopupWindow();
        productPage.obtainPopupWindowAfterClickAddToShoppingCart();
        shoppingCart = productPage.obtainShoppingCart();
        assertThat(shoppingCart.findProductName().getText().trim().equals(TARGET_PRODUCT));
    }

    @Test(priority = 2)
    @Description("Test quantity of products in shopping cart is not 0")
    public static void testCheckProductQuantityInShoppingCart() {
        assertThat(shoppingCart
                .findShoppingCartProductsQuantityAndSum()
                .getText()
                .trim()
                .contains(SHOPPING_CART_CONTENT_QUANTITY_PRODUCTS));
    }

    @Test(priority = 3)
    @Description("Test product is deleted from shopping cart")
    public static void testProductIsDeletedFromShoppingCart() {
        shoppingCart.findDeleteProductFromShoppingCartIcon().click();
        assertThat(shoppingCart.findMessageOfRemovalProductFromShoppingCart().getText().trim().contains(MESSAGE_OF_REMOVAL));
    }
}
