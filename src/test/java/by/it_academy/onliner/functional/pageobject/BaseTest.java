package by.it_academy.onliner.functional.pageobject;

import by.it_academy.onliner.functional.framework.WebDriverNavigator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {

    @BeforeClass
    @Parameters(value = {"browser","remote"})
    public static void setUp(String browser, boolean remote) {
        WebDriverNavigator.getWebDriverByType(browser,remote);
    }

    @AfterClass
    public static void closeAllWindows() {
        BasePage.closeBrowser();
    }
}
