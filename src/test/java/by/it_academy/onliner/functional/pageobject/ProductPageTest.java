package by.it_academy.onliner.functional.pageobject;

import io.qameta.allure.Description;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static by.it_academy.onliner.functional.pageobject.BasePage.driver;

public class ProductPageTest extends BaseTest {
    public static final String EXACT_SEARCH_WORD = "Смартфон Samsung Galaxy A52 SM-A525F/DS 4GB/128GB (черный)";
    public static final String SEARCH_WORD = "Galaxy A52 SM-A525F/DS 4GB/128GB";
    private static final String SINGLE_PRODUCT_COUNT = "1";
    private static ProductPage productPage = new ProductPage();

    @BeforeClass
    @Description("Navigate to Onliner, get popup window with searching field, search product")
    public static ProductPage getProductPage() {
        return productPage = OnlinerPageNavigation.navigateToOnlinerPage()
                .typeTextInSearchField(" ")
                .obtainPopupWindow()
                .cleanSearchingField()
                .obtainProductPage(EXACT_SEARCH_WORD);
    }

    @Test
    @Description("Test search product equals product page")
    public static void testCheckSearchProductCorrespondsToProductPage() {
        WebElement productHeader = productPage.findProductName();
        assertThat(productHeader.getText())
                .as("Product page does not correspond to search product")
                .contains(SEARCH_WORD);
    }

    @Test
    @Description("Test search product description block exists on page")
    public static void testCheckSearchProductDescriptionExistsOnPage() {
        WebElement productDescription = productPage.findProductDescription();
        assertThat(productDescription)
                .as("Product description does not exist on page")
                .isNotNull();
    }

    @Test
    @Description("Test product with min price is in shopping cart")
    public static void testCheckProductWithMinPriceIsInCart() {
        driver.get().switchTo().defaultContent();
        productPage.obtainProductSuppliers();
        productPage.obtainToShoppingCartButton().click();
        productPage.confirmMinskLocationPopupWindow();
        productPage.obtainPopupWindowAfterClickAddToShoppingCart();
        assertThat(productPage.obtainIconShoppingCartCountProducts().getText()).isEqualTo(SINGLE_PRODUCT_COUNT);
    }
}
