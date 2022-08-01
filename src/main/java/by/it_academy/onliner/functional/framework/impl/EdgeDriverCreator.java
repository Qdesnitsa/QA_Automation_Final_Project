package by.it_academy.onliner.functional.framework.impl;

import by.it_academy.onliner.functional.framework.WebDriverCreator;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverCreator implements WebDriverCreator {
    @Override
    public WebDriver createDriver() {
        ThreadLocal<WebDriver> driver = new ThreadLocal<>();
        WebDriverManager.edgedriver().setup();
        driver.set(new EdgeDriver());
        return driver.get();
    }
}