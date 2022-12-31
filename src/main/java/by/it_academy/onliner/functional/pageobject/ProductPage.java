package by.it_academy.onliner.functional.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductPage extends BasePage {
    private static final By PRODUCT_HEAD_LOCATOR = By.xpath("//*[contains(@class,'catalog-masthead__title')]");
    private static final By PRODUCT_DESCRIPTION_LOCATOR = By.xpath("//*[contains(@class,'offers-description__specs')]");
    private static final By PRODUCT_PRICE_LOCATOR =
            By.xpath("//div[contains(@class,'offers-list__description') and contains(text(),' р.')]");
    private static final By PRODUCT_SUPPLIERS_LOCATOR = By.xpath("//span[contains(text(),'Предложения продавцов')]");
    private static final By PRODUCT_TO_SHOPPING_CART_LOCATOR =
            By.xpath("//div[@class='offers-list__part offers-list__part_action']//a[contains(text(),'В корзину')]");
    private static final By ARE_YOU_FROM_MINSK_POPUP_LOCATOR =
            By.xpath("//span[@class='button-style button-style_another button-style_base offers-form__button']");
    private static final By ICON_VALUE_SHOPPING_CART_COUNT_LOCATOR = By.xpath("//div[@class='auth-bar__counter']");
    private static final By SHOPPING_CART_LOCATOR = By.xpath("//a[@href='https://cart.onliner.by' and @title='Корзина']");
    private static final By POPUP_WINDOW_PRODUCT_ADDED_TO_SHOPPING_CART_LOCATOR =
            By.xpath("//div[@class='product-recommended__sidebar-overflow']");
    private static final By CLOSE_POPUP_WINDOW_PRODUCT_ADDED_TO_SHOPPING_CART_LOCATOR =
            By.xpath("//div[@class='product-recommended__sidebar-close']");
    private static final String ALL_SYMBOLS_EXCEPT_NUMBERS_PATTERN = "[^0-9]";
    private static final String EMPTY_PATTERN = "";
    private static final String COMMA = ",";
    private static final String POINT = ".";


    public ProductPage obtainProductSuppliers() {
        waitForElementVisible(PRODUCT_SUPPLIERS_LOCATOR).click();
        return new ProductPage();
    }

    public WebElement findProductName() {
        WebElement productName = findElement(PRODUCT_HEAD_LOCATOR);
        return productName;
    }

    public WebElement findProductDescription() {
        WebElement productDescription = waitForElementVisible(PRODUCT_DESCRIPTION_LOCATOR);
        return productDescription;
    }

    public WebElement findPopupWindowAfterClickAddToShoppingCart() {
        WebElement popupWindowAddedToShoppingCart = waitForElementVisible(POPUP_WINDOW_PRODUCT_ADDED_TO_SHOPPING_CART_LOCATOR);
        return popupWindowAddedToShoppingCart;
    }

    public List<WebElement> findProductPrices() {
        List<WebElement> productPrices = waitForElementsVisible(PRODUCT_PRICE_LOCATOR);
        return productPrices;
    }

    public List<WebElement> findPutProductToShoppingCartButtons() {
        List<WebElement> productToShoppingCart = waitForElementsVisible(PRODUCT_TO_SHOPPING_CART_LOCATOR);
        return productToShoppingCart;
    }

    public ProductPage confirmMinskLocationPopupWindow() {
        try {
            waitForElementVisible(ARE_YOU_FROM_MINSK_POPUP_LOCATOR).click();
        } catch (NullPointerException e) {
            return this;
        }
        return this;
    }

    public WebElement obtainIconShoppingCartCountProducts() {
        WebElement iconCountProducts = waitForElementVisible(ICON_VALUE_SHOPPING_CART_COUNT_LOCATOR);
        return iconCountProducts;
    }

    public ShoppingCart obtainShoppingCart() {
        waitForElementVisible(SHOPPING_CART_LOCATOR).click();
        return new ShoppingCart();
    }

    public WebElement obtainToShoppingCartButton() {
        List<WebElement> productToShoppingCart = findPutProductToShoppingCartButtons();
        List<Double> productPricesDouble = obtainProductPrices();
        Map<Double, WebElement> map = zipToMap(productPricesDouble, productToShoppingCart);
        WebElement toShoppingCartButton = obtainWebElementWithMinKey(map);
        return toShoppingCartButton;
    }

    public void obtainPopupWindowAfterClickAddToShoppingCart() {
        WebElement popupWindowAfterClickAddToShoppingCart = findPopupWindowAfterClickAddToShoppingCart();
        if (popupWindowAfterClickAddToShoppingCart != null) {
            findClosePopupWindowAfterClickAddToShoppingCart().click();
        }
    }

    private WebElement findClosePopupWindowAfterClickAddToShoppingCart() {
        WebElement closePopupWindowAddedToShoppingCart = waitForElementVisible(CLOSE_POPUP_WINDOW_PRODUCT_ADDED_TO_SHOPPING_CART_LOCATOR);
        return closePopupWindowAddedToShoppingCart;
    }

    private List<Double> obtainProductPrices() {
        List<Double> productPricesDouble = findProductPrices()
                .stream()
                .map(p -> p.getText()
                        .replace(COMMA, POINT)
                        .replaceAll(ALL_SYMBOLS_EXCEPT_NUMBERS_PATTERN, EMPTY_PATTERN)
                        .trim())
                .map(p -> Double.parseDouble(p))
                .collect(Collectors.toList());
        return productPricesDouble;
    }

    private <K, V> Map<K, V> zipToMap(List<K> keys, List<V> values) {
        Map<K, V> map = new HashMap<>();
        for (int i = 0; i < keys.size() && i < values.size(); i++) {
            map.put(keys.get(i), values.get(i));
        }
        return map;
    }

    private <K extends Comparable, V> V obtainWebElementWithMinKey(Map<K, V> map) {
        V putToShoppingCartButton = map.entrySet()
                .stream()
                .min((Map.Entry<K, V> e1, Map.Entry<K, V> e2) -> e1.getKey()
                        .compareTo(e2.getKey())).get().getValue();
        return putToShoppingCartButton;
    }
}
