package by.it_academy.onliner.functional.pageobject;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ComponentsPageTest extends BaseTest {
    private static final String QUANTITY_CRITERIA = "товар";
    private static final String PRICE_CRITERIA = "р.";
    //private static final String PATTERN_QUANTITY_OF_PRODUCTS_AVAILABLE = "[\\d]+\\s(товар)";
    //private static final String PATTERN_MIN_PRICE_OF_PRODUCT = "(от)\\s\\d+[.,]?[0-9]+\\s(р)";
    private static final String TOP_MENU_SECTION_NAME = "Каталог";
    private static final String SUBSECTION_NAME = "Компьютеры и";
    private static final String LEFT_MENU_ITEM_NAME = "Комплектующие";
    public static final String COMPONENTS_NAME_LOCATOR
            = "//*[@class='catalog-navigation-list__aside-title' and contains(text(), 'Комплектующие') " +
            "and not (contains(text(), 'Комплектующие для'))]" +
            "//following-sibling::div[@class='catalog-navigation-list__dropdown']" +
            "//span//*[contains(@class, 'catalog-navigation-list__dropdown-title')]";
    public static final String COMPONENTS_DESCRIPTION_LOCATOR
            = "//*[@class='catalog-navigation-list__aside-title' and contains(text(), 'Комплектующие') " +
            "and not (contains(text(), 'Комплектующие для'))]" +
            "//following-sibling::div//span//*[@class='catalog-navigation-list__dropdown-description']";
    private static ComponentsPage componentsPage = new ComponentsPage();

    @BeforeClass
    public static void getOnlinerGetCatalogGetComputersGetComponents() {
        componentsPage = OnlinerPageNavigation.navigateToOnlinerPage()
                .clickOnCatalog(TOP_MENU_SECTION_NAME)
                .clickOnComputers(SUBSECTION_NAME)
                .clickOnComponents(LEFT_MENU_ITEM_NAME);
    }

    @Test
    public void testAllElementsContainName() {
        List<WebElement> targetWebElementsWithNames = componentsPage.findElements(By.xpath(COMPONENTS_NAME_LOCATOR));
        List<String> actualWebElementsWithNames = componentsPage.findProductNames();
        Assertions.assertThat(targetWebElementsWithNames)
                .as("Not all elements contain name")
                .hasSameSizeAs(actualWebElementsWithNames);
    }

    @Test
    public void testAllElementsContainDescriptionQuantity() {
        List<String> descriptionsList = componentsPage.findProductDescriptions();
        assertThat(descriptionsList)
                .as("Not all elements contain quantity in description")
                .allMatch(s -> s.contains(QUANTITY_CRITERIA));
    }

    @Test
    public void testAllElementsContainDescriptionMinPrice() {
        List<String> descriptionsList = componentsPage.findProductDescriptions();
        assertThat(descriptionsList)
                .as("Not all elements contain min price in description")
                .allMatch(s -> s.contains(PRICE_CRITERIA));
    }

    @AfterClass
    public static void closeAllWindows() {
        componentsPage.closeBrowser();
    }
}
