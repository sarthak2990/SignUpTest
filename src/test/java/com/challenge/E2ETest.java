package com.challenge;

import com.core.base_test.BaseTest;
import com.core.driver.DriverHolder;
import com.domain.helpers.AppParametersHelper;
import com.domain.page.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class E2ETest extends BaseTest {

    @BeforeMethod
    public void setUp() {
        super.setDriver();
        homePage = new HomePage();
        String url = AppParametersHelper.AutomationPractice.getBaseUrl();
        DriverHolder.getDriver().get(url);
    }

    @DataProvider(name = "password")
    public Object[][] getDataFromDataprovider() {
        return new Object[][]
                {
                        {"small", "Please use 8+ characters for secure password"},
                        {"1234aqwe", "So-so password"},
                        {"Europe@1234", "Good password"},
                        {"Europe@1234@Germany@", "Great password"}
                };
    }

    @Test
    public void verifyTitleTest() {
        homePage.openAndValidate();
    }

    @Test
    public void verifyMiroLogoTest() {
        Assert.assertTrue(homePage.isHomeIconPresent());
    }

    @Test
    public void verifySignBtnTest() {
        Assert.assertTrue(homePage.isSignInPresent());
    }

    @Test
    public void verifyAlreadyRegisteredUserTest() {
        Assert.assertTrue(homePage.verifyAlreadyCreatedUserSingUp());
    }

    @Test
    public void verifySocialMEdiaOptiontoSignUp() {
        homePage.verifySocialSignUpOption();
    }

    @Test
    public void verifySucessfulSignUp() {
        homePage.verifySucessfulUserSignUp();
    }

    @Test
    public void verifyWhenNoUserDetailsEnter() {
        Assert.assertTrue(homePage.isPasswordErrorExist());
    }

    @Test
    public void verifyNoEmailNameFiledForSignUp() {
        homePage.verifyNoEmailNameFiledForSignUp();
    }

    @Test
    public void verifyWhenIAgreeNotSelectedForSignUp() {
        homePage.verify1AgreeNotSelectedSignUp();
    }

    @Test
    public void verifyInvalidEmailAddressMessage() {
        homePage.verifyWrongEmailDomainMessage();
    }

    // Using Dataprovider to test different type of password message in signup page
    @Test(dataProvider = "password")
    public void verifyDifferentPasswordMessage(String password, String message) {
        homePage.verifyDifferentPasswordMessage(password, message);
    }

}
