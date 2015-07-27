package com.softserveinc.ita.volleymanagementtests.tools;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author Dp-076 ATQC
 * This class wraps selenium.WebDriver.
 */
public final class WebDriverUtils {
    /**
     * Static WebDriver field.
     */
    private static WebDriver driver;

    /**
     * Implicitly wait Timeout for WebDriver setup.
     */
    private static long implicitlyWaitTimeout = 1;
    /**
     * Explicitly wait Timeout for WebDriver setup.
     */
    private static long explicitlyWaitTimeout = 1;

    /**
     * Class constructor.
     */
    private WebDriverUtils() {
    }

    /**
     * Static method for start new WebDriver.
     * @return new selenium.WebDriver if WebDriver not exist yet.
     */
    public static WebDriver get() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver",
                    "./lib/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage()
                    .timeouts()
                    .implicitlyWait(getImplicitlyWaitTimeout(),
                            TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    /**
     * Getter for Implicitly wait Timeout.
     * @return Implicitly wait Timeout.
     */
    public static long getImplicitlyWaitTimeout() {
        return implicitlyWaitTimeout;
    }
    /**
     * Getter for Explicitly wait Timeout.
     * @return Explicitly wait Timeout.
     */
    public static long getExplicitlyWaitTimeout() {
        return explicitlyWaitTimeout;
    }

    /**
     * Static method for stop static WebDriver.
     */
    public static void stop() {
        if (driver != null) {
            driver.quit();
        }
        driver = null;
    }

    /**
     * Static method for start new WebDriver and opening given URL in browser.
     * Or only deleting WebDriver cookies, if it exists already.
     * @param path - URL for load.
     */
    public static void load(final String path) {
        get().get(path);
        driver.manage().deleteAllCookies();
    }

    /**
     * Getter for current URL from static WebDriver.
     * @return current URL.
     */
    public static String getCurrentUrl() {
     return   get().getCurrentUrl();
    }
}
