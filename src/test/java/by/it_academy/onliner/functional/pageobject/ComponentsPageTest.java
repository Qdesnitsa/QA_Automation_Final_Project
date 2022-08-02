package by.it_academy.onliner.functional.pageobject;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ComponentsPageTest extends BaseTest {
    private static final String PATTERN_QUANTITY_OF_PRODUCTS_AVAILABLE = "[\\d]+\\s(товар)";
    private static final String PATTERN_MIN_PRICE_OF_PRODUCT = "(от)\\s\\d+[.,]?[0-9]+\\s(р)";
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
        List<WebElement> targetWebElementsWithDescriptionsQuantity
                = componentsPage.findElements(By.xpath(COMPONENTS_DESCRIPTION_LOCATOR));
        List<String> descriptionsList = componentsPage.findProductDescriptions();
        Pattern pattern = Pattern.compile(PATTERN_QUANTITY_OF_PRODUCTS_AVAILABLE);
        List<String> actualList = descriptionsList
                .stream()
                .flatMap(s -> Stream.ofNullable(s))
                .filter(pattern.asPredicate())
                .collect(Collectors.toList());
        Assertions.assertThat(targetWebElementsWithDescriptionsQuantity)
                .as("Not all elements contain quantity in description")
                .hasSameSizeAs(actualList);
    }

    @Test
    public void testAllElementsContainDescriptionMinPrice() {
        List<WebElement> targetWebElementsWithDescriptionsMinPrice
                = componentsPage.findElements(By.xpath(COMPONENTS_DESCRIPTION_LOCATOR));
        List<String> descriptionsList = componentsPage.findProductDescriptions();
        Pattern pattern = Pattern.compile(PATTERN_MIN_PRICE_OF_PRODUCT);
        List<String> actualList = descriptionsList
                .stream()
                .flatMap(s -> Stream.ofNullable(s))
                .filter(pattern.asPredicate())
                .collect(Collectors.toList());
        Assertions.assertThat(targetWebElementsWithDescriptionsMinPrice)
                .as("Not all elements contain min price in description")
                .hasSameSizeAs(actualList);
    }

    @AfterClass
    public static void closeAllWindows() {
        componentsPage.closeBrowser();
    }
}
