package com.personal.genaiqa.ui.pages;

import com.personal.genaiqa.ui.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;
    private final WaitUtils wait;

    private final By usernameInput = By.id("username");     // replace locator
    private final By passwordInput = By.id("password");     // replace locator
    private final By loginButton = By.id("submit");    // replace locator
    private final By errorMessage = By.id("error");  // replace locator

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public LoginPage open(String baseUrl) {
        driver.get(baseUrl);
        return this;
    }

    public LoginPage enterUsername(String username) {
        wait.waitForVisible(usernameInput).clear();
        wait.waitForVisible(usernameInput).sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        wait.waitForVisible(passwordInput).clear();
        wait.waitForVisible(passwordInput).sendKeys(password);
        return this;
    }

    public void clickLogin() {
        wait.waitForClickable(loginButton).click();
    }

    public DashboardPage loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new DashboardPage(driver);
    }

    public String getErrorMessage() {
        return wait.waitForVisible(errorMessage).getText();
    }

    public boolean isLoginButtonEnabled() {
        return wait.waitForVisible(loginButton).isEnabled();
    }
}