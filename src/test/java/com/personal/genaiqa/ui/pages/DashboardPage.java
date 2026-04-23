package com.personal.genaiqa.ui.pages;

import com.personal.genaiqa.ui.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

    private final WebDriver driver;
    private final WaitUtils wait;

    private final By dashboardHeader = By.cssSelector("h1"); // replace locator

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public boolean isLoaded() {
        return wait.waitForVisible(dashboardHeader).isDisplayed();
    }

    public boolean isOnDashboardUrl() {
        return wait.waitForUrlContains("logged-in-successfully");
    }
}