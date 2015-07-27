package com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces;
/**
 * This interface is a layer between controls level and higher levels.
 *
 * @author Dp-076 ATQC
 */
public interface ITextInput {
    /**
     * Wrapper for the WebElement method "clear()".
     */
    void clear();
    /**
     * Wrapper for the WebElement method "type()".
     * @param text of type
     *
     */
    void type(String text);
    /**
     * Wrapper for the WebElement method "submit()".
     */
    void submit();
    /**
     * Wrapper for the WebElement method "click()".
     */
    void click();
    /**
     * Wrapper for the WebElement method "getText()".
     *
     * @return text
     */
    String getText();
    /**
     * Wrapper for the WebElement method "isDisplayed".
     *
     * @return true, if displayed or false if not
     */
    boolean isDisplayed();
    /**
     * Wrapper for the WebElement method "getText()".
     *
     * @return class attributes
     */
    String getAttributeClass();
    /**
     * Wrapper for the WebElement method "getText()".
     * @return placeholder attributes
     */
    String getAttributePlaceholder();
    /**
     * @return true, if is present.
     */
    boolean isPresent();
}
