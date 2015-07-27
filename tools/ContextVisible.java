package com.softserveinc.ita.volleymanagementtests.tools;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Dp-076 ATQC
 * This class wraps selenium.WebElement methods: findElement, findElements.
 * And wraps Selenium WebDriverWait.
 */
public final class ContextVisible {

    /**
     * Static WebDriverWait field.
     */
    private static WebDriverWait wait;

    /**
     * Static method for start new WebDriverWait.
     * @return new ContextVisible.
     */
    public static ContextVisible get() {
        return new ContextVisible();
    }

    /**
     * Class constructor.
     * Initializes new WebDriverWait if it not exist yet.
     */
    private ContextVisible() {
        if (wait == null) {
            wait = new WebDriverWait(WebDriverUtils.get(),
                    WebDriverUtils.getExplicitlyWaitTimeout());
        }

    }

    /**
     * Getter for WebElement.
     *
     * @param controlLocation - object with selenium.By locator
     *                                               of searching element.
     * @return Required WebElement.
     */
    public WebElement get(final ControlLocation controlLocation) {
        WebElement webElement;
        try {
        webElement = wait.until(ExpectedConditions
                .visibilityOfElementLocated(controlLocation.getBy()));
        } catch (Exception e) {
            webElement = null;
            }
        return webElement;
    }

    /**
     * Getter for WebElement for parent context.
     * @param parentWrapper - context of required WebElement.
     * @param controlLocation object with selenium.By locator.
     * @return Required WebElement.
     */
    public WebElement getInParent(final ControlWrapper parentWrapper,
                    final ControlLocation controlLocation) {
        WebElement childElement;
        try {
            childElement = parentWrapper
                    .get().findElement(controlLocation.getBy());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            throw new org.openqa.selenium.NoSuchElementException(
                    "Error" + controlLocation.getValue());
        }
        return childElement;
    }

    /**
     * Wrapper for selenium.WebElement findElements() method.
     *
     * @param controlLocation - object with selenium.By locator
     *                          of searching element.
     * @return List<WebElement> of required WebElements.
     */
    public List<WebElement> getList(final ControlLocation controlLocation) {
        List<WebElement> webElementList;
        webElementList = WebDriverUtils
                .get().findElements(controlLocation.getBy());
        return webElementList;
    }
}
