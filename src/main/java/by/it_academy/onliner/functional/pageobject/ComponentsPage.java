package by.it_academy.onliner.functional.pageobject;

import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComponentsPage extends BasePage {
    public static final String COMPONENTS_NAME_LOCATOR
            = "//*[@class='catalog-navigation-list__aside-title' and contains(text(), 'Комплектующие') " +
            "and not (contains(text(), 'Комплектующие для'))]" +
            "//following-sibling::div[@class='catalog-navigation-list__dropdown']" +
            "//span//*[contains(@class, 'catalog-navigation-list__dropdown-title')]";
    public static final String COMPONENTS_DESCRIPTION_LOCATOR
            = "//*[@class='catalog-navigation-list__aside-title' and contains(text(), 'Комплектующие') " +
            "and not (contains(text(), 'Комплектующие для'))]" +
            "//following-sibling::div//span//*[@class='catalog-navigation-list__dropdown-description']";

    public List<String> findProductNames() {
        List<String> webElementsWithNames = obtainListOfWebElements(COMPONENTS_NAME_LOCATOR);
        return webElementsWithNames;
    }

    public List<String> findProductDescriptions() {
        List<String> webElementsWithDescriptions = obtainListOfWebElements(COMPONENTS_DESCRIPTION_LOCATOR);
        return webElementsWithDescriptions;
    }

    private List<String> obtainListOfWebElements(String xpath) {
        List<String> listOfWebElements = findElements(By.xpath(xpath))
                .stream()
                .flatMap(s -> Stream.ofNullable(s))
                .map(p -> p.getText())
                .filter(el -> !el.isEmpty())
                .collect(Collectors.toList());
        return listOfWebElements;
    }
}
