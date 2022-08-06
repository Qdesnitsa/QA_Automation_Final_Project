package by.it_academy.onliner.functional.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static java.lang.String.format;

public class OnlinerPage extends BasePage {
    private static final String TOP_MENU_SECTION_XPATH_PATTERN
            = "//*[@class='b-main-navigation__text' and contains(text(), '%s')]";
    private static final String POPUP_WINDOW_LOCATOR = "//*[@class='modal-iframe']";
    private static final String SEARCH_FIELD_LOCATOR = "//*[@class='search__input']";

    public CatalogPage clickOnCatalog(String linkContains) {
        waitForElementVisible(By.xpath(format(TOP_MENU_SECTION_XPATH_PATTERN, linkContains))).click();
        return new CatalogPage();
    }

    public SearchingField obtainPopupWindow() {
        WebElement popUpWindow = findElement(By.xpath(POPUP_WINDOW_LOCATOR));
        driver.get().switchTo().frame(popUpWindow);
        Actions action = new Actions(driver.get());
        waitForElementsVisible(By.xpath(SEARCH_FIELD_LOCATOR));
        WebElement webElement = findElement(By.xpath(SEARCH_FIELD_LOCATOR));
        action.moveToElement(webElement).build().perform();
        webElement.clear();
        webElement.sendKeys(Keys.CONTROL + "a");
        webElement.sendKeys(Keys.DELETE);
        return new SearchingField();
    }
}
