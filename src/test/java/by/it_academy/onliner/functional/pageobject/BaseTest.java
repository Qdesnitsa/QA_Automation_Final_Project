package by.it_academy.onliner.functional.pageobject;

import by.it_academy.onliner.functional.framework.WebDriverNavigator;
import by.it_academy.onliner.listener.AllureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

@Listeners(AllureListener.class)
public class BaseTest {
    private static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);

    @BeforeClass
    @Parameters(value = {"browser","remote"})
    public static void setUp(String browser, boolean remote) {
        LOG.info("Listener AllureSelenium was added");
        WebDriverNavigator.getWebDriverByType(browser,remote);
    }

    @AfterClass
    public static void closeAllWindows() {
        BasePage.closeBrowser();
    }
}
