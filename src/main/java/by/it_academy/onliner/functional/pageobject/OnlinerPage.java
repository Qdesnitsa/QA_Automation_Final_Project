package by.it_academy.onliner.functional.pageobject;

import by.it_academy.onliner.functional.framework.BasePage;
import org.openqa.selenium.By;

public class OnlinerPage extends BasePage {
    private static final String CATALOG = "//*[@class='b-main-navigation__text' and contains(text(), '%s')]";

    public CatalogPage clickOnCatalog(String linkContains) {
        waitForElementVisible(By.xpath(String.format(CATALOG, linkContains))).click();
        return new CatalogPage();
    }
}
