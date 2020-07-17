package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class GeneralFunctions {

    //Main driver
    public static WebDriver driver;

    //Constants and variables
    private static final String SOURCE_LINK = "https://www.airbnb.com";

    public static String getSourceLink() {
        return SOURCE_LINK;
    }

    /**
     * General method to initialize the driver
     */
    public static void initializeDriver() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        Map<String, Object> prefs = new HashMap<>();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    /**
     * Method to open the browser using desired link
     *
     * @param link
     */
    public static void openBrowser(String link) {
        initializeDriver();
        driver.get(link);
    }

    /**
     * Method to wait for visibility of elements
     *
     * @param webElement
     */
    public static void waitForElement(By webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
    }

    /**
     * Method to close the browser
     */
    public static void closeBrowser() {
        driver.close();
        driver.quit();
    }

    /**
     * Method to switch to the newly opened browser tab
     */
    public static void switchtoSecondChromeTab() {
        Set<String> handles = driver.getWindowHandles();
        String currentHandle = driver.getWindowHandle();
        for (String handle : handles) {
            if (!handle.equals(currentHandle)) {
                driver.switchTo().window(handle);
            }
        }
    }

    /**
     * General method to return the result element by Index
     *
     * @param index
     * @return
     */
    public static String returnStringElementbyIndexList(int index, By pageElements) {
        List<WebElement> propertyElements = driver.findElements(pageElements);
        String propertyElement = null;
        for (int i = 0; i < propertyElements.size(); i++) {
            if (i == index) {
                propertyElement = propertyElements.get(i).getText();
                break;
            }
        }
        return propertyElement;
    }

    /**
     * General method to return the string from an element
     *
     * @param pageElement
     * @return
     */
    public static String returnStringElement(By pageElement) {
        waitForElement(pageElement);
        String element = driver.findElement(pageElement).getText();
        return element;
    }

}




