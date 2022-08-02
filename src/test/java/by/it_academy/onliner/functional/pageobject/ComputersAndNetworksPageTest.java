package by.it_academy.onliner.functional.pageobject;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ComputersAndNetworksPageTest extends BaseTest{
    private static final String TOP_MENU_SECTION_NAME = "Каталог";
    private static final String SUBSECTION_NAME = "Компьютеры и";
    private static final List<String> TITLES_OF_ASIDE_LIST = Arrays.asList("Ноутбуки, компьютеры, мониторы",
            "Комплектующие", "Хранение данных", "Сетевое оборудование");
    private static ComputersAndNetworksPage computersAndNetworksPage = new ComputersAndNetworksPage();

    @BeforeClass
    public static void getOnlinerGetCatalogGetComputers() {
        computersAndNetworksPage = OnlinerPageNavigation.navigateToOnlinerPage()
                .clickOnCatalog(TOP_MENU_SECTION_NAME)
                .clickOnComputers(SUBSECTION_NAME);
        computersAndNetworksPage.mouseOver();
    }

    @Test
    public void testSectionContainsTabsList() {
        List<String> computersAndNetworksLinks = computersAndNetworksPage.computersAndNetworksLinks();
        isSectionTitlesContainTargetList(computersAndNetworksLinks);
        assertThat(computersAndNetworksLinks)
                .as("Section computers and networks does not contain all target elements")
                .hasSameSizeAs(TITLES_OF_ASIDE_LIST);
    }

    public boolean isSectionTitlesContainTargetList(List<String> sectionsTitles) {
        return sectionsTitles.retainAll(TITLES_OF_ASIDE_LIST);
    }

    @AfterClass
    public static void closeAllWindows() {
        computersAndNetworksPage.closeBrowser();
    }
}
