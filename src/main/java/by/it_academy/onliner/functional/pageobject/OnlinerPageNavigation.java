package by.it_academy.onliner.functional.pageobject;

import by.it_academy.onliner.functional.framework.BasePage;

public class OnlinerPageNavigation extends BasePage {
    private static final String ONLINER_LINK = "https://www.onliner.by/";

    public static OnlinerPage navigateToOnlinerPage() {
        OnlinerPage onlinerPage = new OnlinerPage();
        onlinerPage.navigate(ONLINER_LINK);
        return onlinerPage;
    }
}
