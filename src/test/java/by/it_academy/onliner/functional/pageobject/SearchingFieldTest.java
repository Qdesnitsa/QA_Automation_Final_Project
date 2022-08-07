package by.it_academy.onliner.functional.pageobject;

import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchingFieldTest extends BaseTest {

    private static final String searchWord = "BMW";
    private static SearchingField searchingField = new SearchingField();

    @BeforeClass
    @Description("Navigate to Onliner and get popup window with searching field")
    public static void getSearchField() {
        searchingField = OnlinerPageNavigation.navigateToOnlinerPage()
                .typeTextInSearchField(searchWord)
                .obtainPopupWindow();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test all elements contain target searching word")
    @Issue("123 - Bug number from Jira")
    @Story("Search word across search field results tab elements")
    public static void testCheckAllNames() {
        assertThat(searchingField.findProductNames(searchWord))
                .as("Not all headers contain search word")
                .allMatch(elem -> elem.contains(searchWord));

    }
}
