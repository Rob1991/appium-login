/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login_appium;

import java.net.URL;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import javax.lang.model.element.Element;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 *
 * @author 1991r
 */

public class Login_appium {

    public static AppiumDriver driver;

    public static void main(String[] args) throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.example.tsolak.loginexample");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("appActivity", "com.example.tsolak.loginexample.SplashActivity");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformVersion", "8.1.0");
        capabilities.setCapability("app", "C:\\Users\\1991r\\OneDrive\\Desktop\\app-debug.apk");

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities) {
        };

        Thread.sleep(5000);
        checkText(driver, "LoginExample");

//check the login by empty email and password
        login(driver, "", "");
        Thread.sleep(5000);

//check the login by wrong email and empty password
        login(driver, "aaaaa", "");
        Thread.sleep(5000);

//check the login by wrong email and wrong password
        login(driver, "aaaaa", "a");
        Thread.sleep(5000);

//check the login by right email and short password
        login(driver, "aaaaa@mail.ru", "a");
        Thread.sleep(5000);

//check the login by right email and right password
        login(driver, "email@example.com", "1111111");
        Thread.sleep(5000);

        checkText(driver, "Welcome User");
        checkText(driver, "Email: email@example.com");
        checkText(driver, "Country: Armenia");
        checkText(driver, "Age: 21");
        checkText(driver, "Gender: Male");

//click logout button
        driver.findElementByClassName("android.widget.Button").click();
        Thread.sleep(5000);

        driver.quit();
    }

//function for login
    public static void login(AppiumDriver driver, String email, String password) throws InterruptedException {
        if (email.length() > 0) {
            driver.findElementById("edt_username").clear();
            driver.findElementById("edt_username").sendKeys(email);
            driver.hideKeyboard();
            Thread.sleep(5000);
        }
        if (password.length() > 0) {
            driver.findElementById("edt_password").clear();
            driver.findElementById("edt_password").sendKeys(password);
            driver.hideKeyboard();
            Thread.sleep(5000);
        }
        driver.findElementById("btn_login").click();

    }

//function for check text
    public static void checkText(AppiumDriver driver, String text) {
        boolean k = false;
        try {
            driver.findElementByXPath("//android.widget.TextView[@text='" + text + "']").isDisplayed();
        } catch (RuntimeException e) {
            k = true;
        }
        Assert.assertFalse(text + " not found", k == true);
    }
}
