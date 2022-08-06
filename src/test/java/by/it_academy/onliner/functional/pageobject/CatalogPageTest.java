package by.it_academy.onliner.functional.pageobject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CatalogPageTest extends BaseTest {
    private static final String TOP_MENU_SECTION_NAME = "Каталог";
    private static final List<String> SECTIONS_LIST = Arrays.asList("Электроника", "Компьютеры и сети", "Бытовая техника",
            "Стройка и ремонт", "Дом и сад", "Авто и мото", "Красота и спорт", "Детям и мамам", "Работа и офис");
    private static CatalogPage catalogpage = new CatalogPage();

    @BeforeClass
    public static void getOnlinerGetCatalog() {
        catalogpage = OnlinerPageNavigation.navigateToOnlinerPage()
                .clickOnCatalog(TOP_MENU_SECTION_NAME);
    }

    @Test
    public void testCatalogContainsSectionList() {
        List<String> sectionsTitles = catalogpage.catalogLinks();
        assertThat(isPageContainSectionList(sectionsTitles))
                .as("Page sections does not contain all items of target list")
                .isTrue();
    }

    private boolean isPageContainSectionList(List<String> sectionsTitles) {
        return sectionsTitles.containsAll(SECTIONS_LIST);
    }

}
