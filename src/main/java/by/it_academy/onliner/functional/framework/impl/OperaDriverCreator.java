package by.it_academy.onliner.functional.framework.impl;

import by.it_academy.onliner.functional.framework.WebDriverCreator;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

public class OperaDriverCreator implements WebDriverCreator {

    @Override
    public WebDriver createDriver() {
        ThreadLocal<WebDriver> driver = new ThreadLocal<>();
        WebDriverManager.operadriver().setup();
        driver.set(new OperaDriver());
        return driver.get();
    }
}