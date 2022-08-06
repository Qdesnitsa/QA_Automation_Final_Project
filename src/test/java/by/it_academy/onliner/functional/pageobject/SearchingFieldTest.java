package by.it_academy.onliner.functional.pageobject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchingFieldTest extends BaseTest {

    private static final String searchWord = "BMW";
    private static SearchingField searchingField = new SearchingField();

    @BeforeClass
    public static void getSearchField() {
        searchingField = OnlinerPageNavigation.navigateToOnlinerPage()
                .obtainPopupWindow();
    }

    @Test
    public static void testCheckAllNames() {
        assertThat(searchingField.findProductNames(searchWord))
                .as("Not all headers contain search word")
                .allMatch(elem -> elem.contains(searchWord));

    }

}
