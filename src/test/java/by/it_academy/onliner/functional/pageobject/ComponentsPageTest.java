package by.it_academy.onliner.functional.pageobject;

import java.util.List;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForInterfaceTypes;
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
    private static ComponentsPage componentsPage = new ComponentsPage();

    @BeforeClass
    @Description("Navigate to Onliner and click on 'Components' tab")
    public static void getOnlinerGetCatalogGetComputersGetComponents() {
        componentsPage = OnlinerPageNavigation.navigateToOnlinerPage()
                .clickOnCatalog(TOP_MENU_SECTION_NAME)
                .clickOnComputers(SUBSECTION_NAME)
                .clickOnComponents(LEFT_MENU_ITEM_NAME);
    }

    @Test
    @Description("Test all elements contain name")
    @Story("Search names across ComponentsPage tab elements")
    public void testAllElementsContainName() {
        List<WebElement> targetWebElementsWithNames = componentsPage.findNamesOfElements();
        List<String> actualWebElementsWithNames = componentsPage.findProductNames();
        Assertions.assertThat(targetWebElementsWithNames)
                .as("Not all elements contain name")
                .hasSameSizeAs(actualWebElementsWithNames);
    }

    @Test
    @Description("Test all elements contain quantity")
    @Story("Search quantity across ComponentsPage tab elements")
    public void testAllElementsContainDescriptionQuantity() {
        List<String> descriptionsList = componentsPage.findProductDescriptions();
        assertThat(descriptionsList)
                .as("Not all elements contain quantity in description")
                .allMatch(s -> s.contains(QUANTITY_CRITERIA));
    }

    @Test
    @Description("Test all elements contain price")
    @Story("Search prices across ComponentsPage tab elements")
    public void testAllElementsContainDescriptionMinPrice() {
        List<String> descriptionsList = componentsPage.findProductDescriptions();
        assertThat(descriptionsList)
                .as("Not all elements contain min price in description")
                .allMatch(s -> s.contains(PRICE_CRITERIA));
    }

//    @Test
//    @Description("Test all elements do not contain price. Test should be failed")
//    @Story("Search prices across ComponentsPage tab elements")
//    public void testNoElementsContainPrice() {
//        List<String> descriptionsList = componentsPage.findProductDescriptions();
//        assertThat(descriptionsList)
//                .as("Not all elements contain min price in description")
//                .noneMatch(s -> s.contains(PRICE_CRITERIA));
//    }
}
