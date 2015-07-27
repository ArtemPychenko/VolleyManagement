package com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces;
/**
 * This interface is a layer between controls level and higher levels.
 *
 * @author Dp-076 ATQC
 */
public interface IValidationLabel {
    /**
     * Wrapper for the WebElement method "isDisplayed".
     *
     * @return true, if displayed or false if not
     */
    boolean isDisplayed();
    /**
     * The opposite method of "isDisplayed".
     * @return text.
     */
    String getText();
    /**
     * @return true, if is present.
     */
    boolean isPresent();
}
