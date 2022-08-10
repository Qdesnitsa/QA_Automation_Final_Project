package by.it_academy.onliner.listener;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import static by.it_academy.onliner.util.ScreenshotUtil.takeScreenshotAndAttachToAllureReport;

public class AllureListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult tr) {
        takeScreenshotAndAttachToAllureReport();
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        takeScreenshotAndAttachToAllureReport();
    }
}
