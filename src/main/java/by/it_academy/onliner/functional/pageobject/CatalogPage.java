package by.it_academy.onliner.functional.pageobject;

import org.openqa.selenium.By;

import java.util.List;

import static java.lang.String.format;

public class CatalogPage extends BasePage {
    private static final By CATALOG_TOP_MENU_LOCATOR = By.xpath("//*[@class = 'catalog-navigation-classifier__item ']");
    public static final String COMPUTERS_AND_SMTH_XPATH_PATTERN
            = "//*[@class = 'catalog-navigation-classifier__item-title-wrapper' and contains(text(), '%s')]";

    public List<String> catalogLinks() {
        return getTextsFromWebElements(waitForElementsVisible(CATALOG_TOP_MENU_LOCATOR));
    }

    public ComputersAndNetworksPage clickOnComputers(String linkContains) {
        waitForElementVisible(By.xpath(format(COMPUTERS_AND_SMTH_XPATH_PATTERN, linkContains))).click();
        return new ComputersAndNetworksPage();
    }
}
