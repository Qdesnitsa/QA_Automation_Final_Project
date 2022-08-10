package by.it_academy.onliner.functional.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static java.lang.String.format;

public class OnlinerPage extends BasePage {
    private static final String TOP_MENU_SECTION_XPATH_PATTERN
            = "//*[@class='b-main-navigation__text' and contains(text(), '%s')]";
    private static final By POPUP_WINDOW_LOCATOR = By.xpath("//*[@class='modal-iframe']");
    private static final By SEARCH_FIELD_LOCATOR = By.xpath("//*[@class='search__input']");
    private static final By SEARCH_FIELD = By.xpath("//*[@class='fast-search__input']");
    private static final String ANY_INPUT = "abc";

    public CatalogPage clickOnCatalog(String linkContains) {
        waitForElementVisible(By.xpath(format(TOP_MENU_SECTION_XPATH_PATTERN, linkContains))).click();
        return new CatalogPage();
    }

    public WebElement searchField() {
        return waitForElementVisible(SEARCH_FIELD);
    }

    public OnlinerPage typeTextInSearchField(String text) {
        searchField().clear();
        searchField().sendKeys(text);
        return this;
    }

    public SearchingField obtainPopupWindow() {
        WebElement popUpWindow = findElement(POPUP_WINDOW_LOCATOR);
        driver.get().switchTo().frame(popUpWindow);
        Actions action = new Actions(driver.get());
        waitForElementsVisible(SEARCH_FIELD_LOCATOR);
        WebElement webElement = findElement(SEARCH_FIELD_LOCATOR);
        action.moveToElement(webElement).build().perform();
        webElement.clear();
        webElement.sendKeys(Keys.CONTROL + ANY_INPUT);
        webElement.sendKeys(Keys.DELETE);
        return new SearchingField();
    }
}
