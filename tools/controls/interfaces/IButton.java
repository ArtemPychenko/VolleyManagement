package com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces;
/**
 * This interface is a layer between controls level and higher levels.
 *
 * @author Dp-076 ATQC
 */
public interface IButton {
    /**
     * Wrapper for the WebElement method "click()".
     */
    void click();
    /**
     * Wrapper for the WebElement method "getText()".
     * @return String text
     */
    String getText();
    /**
     * Wrapper for the WebElement method "isDisplayed()".
     * @return boolean isDisplayed
     */
    boolean isDisplayed();
    /**
     * @return true, if is present.
     */
    boolean isPresent();

}
