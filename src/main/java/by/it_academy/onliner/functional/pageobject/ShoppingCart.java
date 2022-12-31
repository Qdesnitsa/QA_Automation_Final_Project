package by.it_academy.onliner.functional.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ShoppingCart extends BasePage {
    private static final By PRODUCT_HEAD_LOCATOR =
            By.xpath("//div[contains(@class,'cart-form__description')]/a[contains(@href,'catalog.onliner.by')]");
    private static final By PRODUCT_QUANTITY_AND_PRICE_LOCATOR =
            By.xpath("//div[contains(@class,'cart-form__description_other cart-form__description_extended')]");
    private static final By TRASH_ICON_LOCATOR = By.xpath("//*[contains(@class,'cart-form__button_remove')]");
    private static final By MESSAGE_AFTER_PRODUCT_DELETION_LOCATOR =
            By.xpath("//*[contains(@class,'cart-form__description_condensed-extra')]");
    public WebElement findProductName() {
        WebElement productName = waitForElementVisible(PRODUCT_HEAD_LOCATOR);
        return productName;
    }

    public WebElement findShoppingCartProductsQuantityAndSum() {
        WebElement shoppingCartShortContent = waitForElementVisible(PRODUCT_QUANTITY_AND_PRICE_LOCATOR);
        return shoppingCartShortContent;
    }

    public WebElement findDeleteProductFromShoppingCartIcon() {
        Actions action = new Actions(driver.get());
        WebElement header = waitForElementVisible(PRODUCT_HEAD_LOCATOR);
        action.moveToElement(header).build().perform();
        WebElement trashIcon = waitForElementVisible(TRASH_ICON_LOCATOR);
        return trashIcon;
    }

    public WebElement findMessageOfRemovalProductFromShoppingCart() {
        WebElement webElement = waitForElementVisible(MESSAGE_AFTER_PRODUCT_DELETION_LOCATOR);
        return webElement;
    }
}
