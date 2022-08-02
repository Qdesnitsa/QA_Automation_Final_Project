package by.it_academy.onliner.functional.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class ComputersAndNetworksPage extends BasePage {
    public static final String COMPUTERS_AND_NETWORKS_LIST_LEFT_LOCATOR
            = "//*[@class = 'catalog-navigation-list__aside catalog-navigation-list__aside_active']" +
            "/div[@class = 'catalog-navigation-list__aside-list']" +
            "/div[contains(@class, 'catalog-navigation-list__aside-item')]" +
            "/div[contains(@class, 'catalog-navigation-list__aside-title')]";
    public static final String NOTEBOOKS_COMPUTERS_MONITORS_LOCATOR
            = "//*[contains(@class, 'catalog-navigation-list__aside-title') " +
            "and contains(text(), ' Ноутбуки, компьютеры, мониторы')]";
    public static final String COMPONENTS_SECTION_XPATH_PATTERN
            = "//*[@class='catalog-navigation-list__aside-title' and contains(text(), '%s') " +
            "and not (contains(text(), 'Комплектующие для'))]";

    public List<String> computersAndNetworksLinks() {
        return getTextsFromWebElements(waitForElementsVisible(By.xpath(COMPUTERS_AND_NETWORKS_LIST_LEFT_LOCATOR)));
    }

    public void mouseOver() {
        Actions action = new Actions(driver.get());
        waitForElementsVisible(By.xpath(NOTEBOOKS_COMPUTERS_MONITORS_LOCATOR));
        WebElement webElement = findElement(By.xpath(NOTEBOOKS_COMPUTERS_MONITORS_LOCATOR));
        action.moveToElement(webElement).build().perform();
    }

    public ComponentsPage clickOnComponents(String linkContains) {
        waitForElementVisible(By.xpath(String.format(COMPONENTS_SECTION_XPATH_PATTERN, linkContains))).click();
        return new ComponentsPage();
    }
}
