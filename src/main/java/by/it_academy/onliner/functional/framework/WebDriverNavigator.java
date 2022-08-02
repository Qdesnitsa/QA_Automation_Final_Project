package by.it_academy.onliner.functional.framework;

import by.it_academy.onliner.functional.framework.impl.ChromeDriverCreator;
import by.it_academy.onliner.functional.framework.impl.EdgeDriverCreator;
import by.it_academy.onliner.functional.framework.impl.OperaDriverCreator;
import by.it_academy.onliner.functional.pageobject.BasePage;
import by.it_academy.onliner.util.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Arrays;

public enum WebDriverNavigator {
    CHROME("chrome", new ChromeDriverCreator()),
    EDGE("MicrosoftEdge", new EdgeDriverCreator()),
    OPERA("opera", new OperaDriverCreator());

    private String driverType;
    private WebDriverCreator webDriver;

    WebDriverNavigator(String driverType, WebDriverCreator webDriver) {
        this.driverType = driverType;
        this.webDriver = webDriver;
    }

    public String getDriverType() {
        return driverType;
    }

    public WebDriverCreator getWebDriver() {
        return webDriver;
    }

    public static void getWebDriverByType(String driverType, boolean remote) {
        if (!remote) {
            WebDriverNavigator driverNavigator = null;
            driverNavigator = Arrays.stream(WebDriverNavigator.values())
                    .filter(type -> type.getDriverType().equals(driverType))
                    .findAny()
                    .orElseThrow(() -> new RuntimeException("Driver not found."));
            BasePage.driver.set((WebDriver) driverNavigator.getWebDriver().createDriver());
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(driverType);
            capabilities.setCapability("os", PropertiesReader.getConfigProperty("os.type"));
            capabilities.setCapability("os_version", PropertiesReader.getConfigProperty("os.version"));
            URL gridURL = PropertiesReader.getConfigURL("grid.url");
            BasePage.driver.set(new RemoteWebDriver(gridURL, capabilities));
        }
    }

    public static void getWebDriverByType() {
        String driverType = System.getProperty("driverType");
        if (System.getProperty("isRemote").equals("false")) {
            WebDriverNavigator driverNavigator = null;
            driverNavigator = Arrays.stream(WebDriverNavigator.values())
                    .filter(type -> type.getDriverType().equals(driverType))
                    .findAny()
                    .orElseThrow(() -> new RuntimeException("Driver not found."));
            BasePage.driver.set((WebDriver) driverNavigator.getWebDriver().createDriver());
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(PropertiesReader.getConfigProperty("driverType"));
            capabilities.setCapability("os", PropertiesReader.getConfigProperty("os.type"));
            capabilities.setCapability("os_version", PropertiesReader.getConfigProperty("os.version"));
            URL gridURL = PropertiesReader.getConfigURL("grid.url");
            BasePage.driver.set(new RemoteWebDriver(gridURL, capabilities));
        }
    }
}
