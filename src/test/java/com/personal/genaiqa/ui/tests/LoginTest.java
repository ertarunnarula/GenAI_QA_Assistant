package com.personal.genaiqa.ui.tests;

import com.personal.genaiqa.ui.base.BaseTest;
import com.personal.genaiqa.ui.pages.DashboardPage;
import com.personal.genaiqa.ui.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Verify login with valid credentials")
    public void shouldLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver).open(baseUrl);

        DashboardPage dashboardPage = loginPage.loginAs("student", "Password123");

        Assert.assertTrue(dashboardPage.isOnDashboardUrl(), "Expected dashboard URL after login");
        Assert.assertTrue(dashboardPage.isLoaded(), "Expected dashboard to be visible after login");
    }

    @Test(description = "Verify login fails with invalid password")
    public void shouldShowErrorForInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver).open(baseUrl);

        loginPage.enterUsername("testuser@example.com")
                 .enterPassword("WrongPassword")
                 .clickLogin();

        Assert.assertTrue(loginPage.getErrorMessage().contains("invalid"),
                "Expected error message for invalid login");
    }

    @Test(dataProvider = "invalidLoginData", description = "Verify invalid login scenarios")
    public void shouldHandleInvalidLoginScenarios(String username, String password) {
        LoginPage loginPage = new LoginPage(driver).open(baseUrl);

        loginPage.enterUsername(username)
                 .enterPassword(password)
                 .clickLogin();

        Assert.assertTrue(loginPage.getErrorMessage().length() > 0,
                "Expected validation or error message");
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][]{
            {"", "Password123"},
            {"invalidlogindata", ""},
            {"", ""},
            {"invalidlogindata@example.com", "Password123"}
        };
    }
}