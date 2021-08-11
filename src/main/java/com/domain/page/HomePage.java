package com.domain.page;

import com.core.driver.DriverHolder;
import com.domain.helpers.AppParametersHelper;
import com.domain.helpers.WebLibrary;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.util.Properties;

@Slf4j
public class HomePage {

    private WebDriverWait webDriverWait;

    public HomePage() {
        webDriverWait = new WebDriverWait(DriverHolder.getDriver(), 60, 30);
    }

    WebLibrary webLibrary = new WebLibrary(DriverHolder.getDriver());
    String slash = File.separator;
    private Properties homePageElement = webLibrary.readProperty("src" + slash + "main" + slash + "java" + slash + "com" + slash + "domain" + slash + "page_object" + slash + "HomePage.properties");


    public void openAndValidate() {
//        String url = AppParametersHelper.AutomationPractice.getBaseUrl();
//        DriverHolder.getDriver().get(url);
        Assert.assertEquals(webLibrary.verifyTitle("Sign up | Miro | Online Whiteboard for Visual Collaboration"), true);
    }

    public boolean isHomeIconPresent() {
        Reporter.log("Verify Miro Icon");
        return webLibrary.isElementPresent(homePageElement.getProperty("miroIcon"));

    }


    public boolean isSignInPresent() {
        Reporter.log("Verify Sign In option");
        return webLibrary.isElementPresent(homePageElement.getProperty("signIn"));

    }

    public boolean verifyAlreadyCreatedUserSingUp() {
        webLibrary.fill(homePageElement.getProperty("name"), homePageElement.getProperty("existUser"));
        webLibrary.fill(homePageElement.getProperty("email"), homePageElement.getProperty("existEmail"));
        webLibrary.fill(homePageElement.getProperty("password"), homePageElement.getProperty("exitPassword"));
        webLibrary.click(homePageElement.getProperty("agreeTerms"));
        webLibrary.click(homePageElement.getProperty("signUp"));
        return webLibrary.isElementPresent(homePageElement.getProperty("alreadyRegisteredMethod"));
    }

    public void verifySocialSignUpOption() {
        Assert.assertTrue(webLibrary.isElementPresent(homePageElement.getProperty("signUpGoogle")));
        Assert.assertTrue(webLibrary.isElementPresent(homePageElement.getProperty("slack")));
        Assert.assertTrue(webLibrary.isElementPresent(homePageElement.getProperty("office")));
        Assert.assertTrue(webLibrary.isElementPresent(homePageElement.getProperty("apple")));
        Assert.assertTrue(webLibrary.isElementPresent(homePageElement.getProperty("facebook")));

    }

    public void verifySucessfulUserSignUp() {
        String email = "MiroUser" + Math.random() + "@gmail.com";
        webLibrary.fill(homePageElement.getProperty("name"), homePageElement.getProperty("existUser"));
        webLibrary.fill(homePageElement.getProperty("email"), email);
        webLibrary.fill(homePageElement.getProperty("password"), homePageElement.getProperty("exitPassword"));
        webLibrary.click(homePageElement.getProperty("agreeTerms"));
        webLibrary.click(homePageElement.getProperty("signUp"));
        Assert.assertTrue(webLibrary.verifyTitle("Please check your email, Online Whiteboard | Miro"));
    }

    public boolean isPasswordErrorExist() {
        webLibrary.click(homePageElement.getProperty("signUp"));
        return webLibrary.isElementPresent(homePageElement.getProperty("passwordError"));
    }

    public void verifyNoEmailNameFiledForSignUp() {
        String email = "MiroUser" + Math.random() + "@gmail.com";
        webLibrary.fill(homePageElement.getProperty("password"), homePageElement.getProperty("exitPassword"));
        webLibrary.click(homePageElement.getProperty("agreeTerms"));
        webLibrary.click(homePageElement.getProperty("signUp"));
        Assert.assertTrue(webLibrary.isTextPresentOnElement(homePageElement.getProperty("nameError"), "Please enter your name."));
        Assert.assertTrue(webLibrary.isTextPresentOnElement(homePageElement.getProperty("emailError"), "Please enter your email address."));
    }

    public void verify1AgreeNotSelectedSignUp() {
        String email = "MiroUser" + Math.random() + "@gmail.com";
        webLibrary.fill(homePageElement.getProperty("name"), homePageElement.getProperty("existUser"));
        webLibrary.fill(homePageElement.getProperty("email"), email);
        webLibrary.fill(homePageElement.getProperty("password"), homePageElement.getProperty("exitPassword"));
        webLibrary.click(homePageElement.getProperty("signUp"));
        Assert.assertTrue(webLibrary.isTextPresentOnElement(homePageElement.getProperty("agreeError"), "Please agree with the Terms to sign up."));
    }


    public void verifyDifferentPasswordMessage(String password, String message) {
        webLibrary.fill(homePageElement.getProperty("password"), password);
        Assert.assertTrue(webLibrary.isTextPresentOnElement(homePageElement.getProperty("passwordError"), message));

    }

    public void verifyWrongEmailDomainMessage() {
        String email = "Saje@test.com";
        webLibrary.fill(homePageElement.getProperty("name"), homePageElement.getProperty("existUser"));
        webLibrary.fill(homePageElement.getProperty("email"), email);
        webLibrary.fill(homePageElement.getProperty("password"), homePageElement.getProperty("exitPassword"));
        webLibrary.click(homePageElement.getProperty("agreeTerms"));
        webLibrary.click(homePageElement.getProperty("signUp"));
        Assert.assertTrue(webLibrary.isTextPresentOnElement(homePageElement.getProperty("emailFormat"), "This email can not be registered, please try another domain"));
    }


    public void verifyForbiddenEmailMessage() {
        String email = "Saje.com";
        webLibrary.fill(homePageElement.getProperty("name"), homePageElement.getProperty("existUser"));
        webLibrary.fill(homePageElement.getProperty("email"), email);
        webLibrary.fill(homePageElement.getProperty("password"), homePageElement.getProperty("exitPassword"));
        webLibrary.click(homePageElement.getProperty("agreeTerms"));
        webLibrary.click(homePageElement.getProperty("signUp"));
        Assert.assertTrue(webLibrary.isTextPresentOnElement(homePageElement.getProperty("emailFormat"), "This email can not be registered, please try another domain"));
    }
}
