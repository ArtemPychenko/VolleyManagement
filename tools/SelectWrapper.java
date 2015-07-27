package com.softserveinc.ita.volleymanagementtests.tools;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
/**
 * This class wraps selenium.support.ui.Select class.
 */
public class SelectWrapper {

    /**
     * Selenium Select object.
     */
    protected Select selectElement;

    /**
     * Class constructor.
     * @param element - WebElement for initialize Select object.
     */
    public SelectWrapper(final WebElement element) {
        selectElement = new Select(element);
    }

    /**
     * This method is access point to Select methods.
     * @return new Select object.
     */
    public final Select get() {
        return selectElement;
    }

    /**
     * Wrapper for Select method .deselectAll().
     */
    public final void deselectAll() {
        get().deselectAll();
    }

    /**
     * Wrapper for Select method .selectByValue().
     * @param value - Value locator for Select element.
     */
    public final void selectByValue(final String value) {
        get().selectByValue(value);
    }

    /**
     * Wrapper for Select method .selectByVisibleText().
     * @param text - Text locator for Select element.
     */
    public final void selectByVisibleText(final String text) {
        get().selectByVisibleText(text);
    }

    /**
     * Wrapper for Select method .selectByIndex().
     * @param index - Index locator for Select element.
     */
    public final void selectByIndex(final int index) {
        get().selectByIndex(index);
    }

    /**
     * Wrapper for Select.getOptions() method.
     * @return List of all WebElement from this Select object.
     */
    public final List<WebElement> getOptions() {
        return get().getOptions();
    }

    /**
     * Wrapper for Select.getAllSelectedOptions() method.
     * @return List of selected WebElement from this Select object.
     */
    public final List<WebElement> getAllSelectedOptions() {
        return get().getAllSelectedOptions();
    }
}
