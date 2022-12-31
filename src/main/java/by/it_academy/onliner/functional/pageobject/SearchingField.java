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
            = "//*[@class='search__content-wrapper']//following::div//*[@class='product__title-link']";
    private static final By SEARCH_FIELD_LOCATOR = By.xpath("//*[@class='search__input']");

    public List<String> findProductNames(String word) {
        Actions action = new Actions(driver.get());
        action.sendKeys(word + Keys.ENTER).build().perform();
        List<String> webElementsWithNames = obtainListOfWebElements(HEADER_NAMES_LOCATOR);
        return webElementsWithNames;
    }

    private List<String> obtainListOfWebElements(String xpath) {
        List<String> listOfWebElements = waitForElementsVisible(By.xpath(xpath))
                .stream()
                .flatMap(s -> Stream.ofNullable(s))
                .map(p -> p.getText())
                .filter(el -> !el.isEmpty())
                .collect(Collectors.toList());
        return listOfWebElements;
    }

    public SearchingField cleanSearchingField() {
        Actions action = new Actions(driver.get());
        waitForElementsVisible(SEARCH_FIELD_LOCATOR);
        WebElement webElement = findElement(SEARCH_FIELD_LOCATOR);
        action.moveToElement(webElement).build().perform();
        webElement.clear();
        webElement.sendKeys(Keys.chord(Keys.SHIFT,Keys.CONTROL,"a"));
        webElement.sendKeys(Keys.DELETE);
        return new SearchingField();
    }

    public ProductPage obtainProductPage(String searchWord) {
        Actions action = new Actions(driver.get());
        action.sendKeys(searchWord + Keys.ENTER).build().perform();
        waitForElementVisible(By.xpath(HEADER_NAMES_LOCATOR)).click();
        return new ProductPage();
    }
}

