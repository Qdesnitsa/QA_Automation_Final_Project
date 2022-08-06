package by.it_academy.onliner.functional.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchingField extends BasePage {
    public static final String HEADER_NAMES_LOCATOR
            = "//*[@class='search__content-wrapper']//following::div//*[@class='product__title']";

    public List<String> findProductNames(String word) {
        Actions action = new Actions(driver.get());
        action.sendKeys(word).build().perform();
        List<String> webElementsWithNames = obtainListOfWebElements(HEADER_NAMES_LOCATOR);
        return webElementsWithNames;
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

