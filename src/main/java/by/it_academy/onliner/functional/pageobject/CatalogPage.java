package by.it_academy.onliner.functional.pageobject;

import by.it_academy.onliner.functional.framework.BasePage;
import org.openqa.selenium.By;

import java.util.List;

public class CatalogPage extends BasePage {
    private static final String CATALOG_TOP_MENU = "//*[@class = 'catalog-navigation-classifier__item ']";

    public List<String> catalogLinks() {
        return getTextsFromWebElements(waitForElementsVisible(By.xpath(CATALOG_TOP_MENU)));
    }

//    public ComputersAndNetworksPage clickOnComputers(String linkContains) {
//        waitForElementVisible(By.xpath(String.format(PathName.COMPUTERS_AND_SMTH, linkContains))).click();
//        return new ComputersAndNetworksPage();
//    }
}
