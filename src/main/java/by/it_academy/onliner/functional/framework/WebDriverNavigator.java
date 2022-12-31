package by.it_academy.onliner.functional.framework;

import by.it_academy.onliner.functional.framework.impl.ChromeDriverCreator;
import by.it_academy.onliner.functional.framework.impl.EdgeDriverCreator;
import by.it_academy.onliner.functional.framework.impl.OperaDriverCreator;
import by.it_academy.onliner.functional.pageobject.BasePage;
import by.it_academy.onliner.util.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Arrays;
import java.util.stream.Stream;

public enum WebDriverNavigator {
    CHROME("chrome", new ChromeDriverCreator()),
    EDGE("MicrosoftEdge", new EdgeDriverCreator()),
    OPERA("opera", new OperaDriverCreator());

    private static final Logger LOG = LoggerFactory.getLogger(WebDriverNavigator.class);
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
        WebDriverNavigator driverNavigator = null;
        driverNavigator = Arrays.stream(WebDriverNavigator.values())
                .flatMap(s -> Stream.ofNullable(s))
                .filter(type -> type.getDriverType().equals(driverType))
                .findAny()
                .orElseGet(() -> WebDriverNavigator.CHROME);
        if (!remote) {
            LOG.info("LocalWebDriver was created");
            BasePage.driver.set((WebDriver) driverNavigator.getWebDriver().createDriver());
            BasePage.driver.get().manage().window().maximize();
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(driverNavigator.getDriverType());
            capabilities.setCapability("os", PropertiesReader.getConfigProperty("os.type"));
            capabilities.setCapability("os_version", PropertiesReader.getConfigProperty("os.version"));
            URL gridURL = PropertiesReader.getConfigURL("grid.url");
            LOG.info("RemoteWebDriver was created");
            BasePage.driver.set(new RemoteWebDriver(gridURL, capabilities));
        }
    }

//    public static void getWebDriverByType() {
//        String driverType = System.getProperty("driverType");
//        if (System.getProperty("isRemote").equals("false")) {
//            WebDriverNavigator driverNavigator = null;
//            driverNavigator = Arrays.stream(WebDriverNavigator.values())
//                    .flatMap(s -> Stream.ofNullable(s))
//                    .filter(type -> type.getDriverType().equals(driverType))
//                    .findAny()
//                    .orElseThrow(() -> new RuntimeException("Driver not found."));
//            LOG.info("LocalWebDriver was created");
//            BasePage.driver.set((WebDriver) driverNavigator.getWebDriver().createDriver());
//        } else {
//            DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setBrowserName(PropertiesReader.getConfigProperty("driverType"));
//            capabilities.setCapability("os", PropertiesReader.getConfigProperty("os.type"));
//            capabilities.setCapability("os_version", PropertiesReader.getConfigProperty("os.version"));
//            URL gridURL = PropertiesReader.getConfigURL("grid.url");
//            LOG.info("RemoteWebDriver was created");
//            BasePage.driver.set(new RemoteWebDriver(gridURL, capabilities));
//        }
//    }
}
