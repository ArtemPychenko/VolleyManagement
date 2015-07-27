package com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces;
/**
 * This interface is a layer between controls level and higher levels.
 *
 * @author Dp-076 ATQC
 */
public interface ICheckbox {
    /**
     * Wrapper for the WebElement method "getAttribute".
     * @param attributeName
     *            String
     * @return checkbox attributeName
     */
    String getAttribute(String attributeName);
    /**
     * Wrapper for the WebElement method "isEnabled()".
     * @return enabled checkbox
     */
    boolean isEnabled();
    /**
     * Wrapper for the WebElement method "isSelected()".
     * @return selected checkbox
     */
    boolean isSelected();
    /**
     * Wrapper for the WebElement method "click()".
     */
    void click();
    /**
     * Wrapper for the WebElement method "isDisplayed()".
     * @return displayed checkbox
     */
    boolean isDisplayed();
    /**
     * @return true, if is present.
     */
    boolean isPresent();
}
