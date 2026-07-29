package com.browserstack;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Chrome {
    public static final String USERNAME = "medhaniniwoda_ioRndV";
    public static final String AUTOMATE_KEY = "GbQpEo9tqtdJVLFCrscB";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static void main(String[] args) throws Exception {
        Map<String, Object> browserstackOptions = new HashMap<>();
        browserstackOptions.put("os", "Windows");
        browserstackOptions.put("osVersion", "11");
        browserstackOptions.put("projectName", "MySLT Web Login");
        browserstackOptions.put("buildName", "Build 1.0");
        browserstackOptions.put("sessionName", "MySLT Login - Chrome");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("browserVersion", "latest");
        caps.setCapability("bstack:options", browserstackOptions);

        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);

        try {
            driver.get("https://myslt.slt.lk/");
            System.out.println("Opened MySLT Website");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/button")));

            loginButton.click();
            System.out.println("Clicked Login Button");

            ((JavascriptExecutor) driver).executeScript(
                "browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\":{\"status\": \"passed\",\"reason\": \"Login button clicked successfully\"}}");
            System.out.println("Test Passed on BrowserStack Chrome Browser");
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript(
                "browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\":{\"status\": \"failed\", \"reason\": \"Test Failed\"}}");
            e.printStackTrace();
            System.out.println("Test Failed");
        } finally {
            driver.quit();
        }
    }
}
