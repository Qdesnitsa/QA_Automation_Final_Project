package by.it_academy.onliner.functional.pageobject;

import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchingFieldTest extends BaseTest {

    private static final String anySearchWord = "BMW";
    private static final String exactSearchWord = "Смартфон Samsung Galaxy A52 SM-A525F/DS 4GB/128GB (черный)";
    private static SearchingField searchingField;

    @BeforeMethod
    @Description("Navigate to Onliner and get popup window with searching field")
    public static SearchingField getSearchField() {
        return searchingField = OnlinerPageNavigation.navigateToOnlinerPage()
                .typeTextInSearchField(" ")
                .obtainPopupWindow()
                .cleanSearchingField();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test all elements contain target searching word")
    @Issue("123 - Bug number from Jira")
    @Story("Search word across search field results tab elements")
    public static void testCheckAllNames() {
        assertThat(searchingField.findProductNames(anySearchWord))
                .as("Not all headers contain search word")
                .allMatch(elem -> elem.contains(anySearchWord));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test single target element appears on the screen")
    @Issue("124 - Bug number from Jira")
    @Story("Search target word")
    public static void testCheckSingleElement() {
        List<String> targetElement = searchingField.findProductNames(exactSearchWord);
        assertThat(targetElement)
                .as("Search result does not contain target word")
                .hasSize(1);
        assertThat(targetElement)
                .as("Target element does not equal search word")
                .allMatch(elem -> elem.equals(exactSearchWord));
    }
}
