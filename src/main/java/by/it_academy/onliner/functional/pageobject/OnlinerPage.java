package by.it_academy.onliner.functional.pageobject;

import org.openqa.selenium.By;

import static java.lang.String.format;

public class OnlinerPage extends BasePage {
    private static final String TOP_MENU_SECTION_XPATH_PATTERN
            = "//*[@class='b-main-navigation__text' and contains(text(), '%s')]";

    public CatalogPage clickOnCatalog(String linkContains) {
        waitForElementVisible(By.xpath(format(TOP_MENU_SECTION_XPATH_PATTERN, linkContains))).click();
        return new CatalogPage();
    }
}
