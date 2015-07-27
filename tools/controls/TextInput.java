package com.softserveinc.ita.volleymanagementtests.tools.controls;

import com.softserveinc.ita.volleymanagementtests.tools.ContextVisible;
import com.softserveinc.ita.volleymanagementtests.tools.ControlLocation;
import com.softserveinc.ita.volleymanagementtests.tools.ControlWrapper;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces
.ITextInput;
/**
 * This class makes a wrapper for text input field.
 *
 * @author Dp-076 ATQC
 */
public final class TextInput implements ITextInput {
    /**
     * Object of SelectWrapper class.
     */
    private ControlWrapper control;
    /**
     * Constructor.
     *
     * @param theControl
     *            - object of ControlWrapper class.
     */
    private TextInput(final ControlWrapper theControl) {
        control = theControl;
    }
    /**
     * Wrapper for the WebElement method "clear()".
     */
    public void clear() {
        control.clear();
    }
    /**
     * Wrapper for the WebElement method "type()".
     * @param text of type
     *
     */
    public void type(final String text) {
        control.sendText(text);
    }
    /**
     * Wrapper for the WebElement method "submit()".
     */
    public void submit() {
        control.submit();
    }
    /**
     * Wrapper for the WebElement method "click()".
     */
    public void click() {
        control.click();
    }
    /**
     * Wrapper for the WebElement method "getText()".
     *
     * @return text
     */
    public String getText() {
        return control.getAttribute("value");
    }
    /**
     * Wrapper for the WebElement method "isDisplayed".
     *
     * @return true, if displayed or false if not
     */
    public boolean isDisplayed() {
        return control.isDisplayed();
    }
    /**
     * Wrapper for the WebElement method "get()".
     * @return true if .get() method return element.
     */
    public boolean isPresent() {
        return (control.get() != null);
       }
    /**
     * Wrapper for the WebElement method "getText()".
     *
     * @return class attributes
     */
    public String getAttributeClass() {
        return control.getAttribute("class");
    }
    /**
     * Wrapper for the WebElement method "getText()".
     * @return placeholder attributes
     */
    public String getAttributePlaceholder() {
        return control.getAttribute("placeholder");
    }
    /**
     * Method finds text input by xpath.
     *
     * @param xpathExpression
     *            locator
     * @return get text input by xpath
     */
    public static ITextInput getByXpath(final String xpathExpression) {
        return get(ControlLocation.getByXPath(xpathExpression));
    }
    /**
     * Method finds text input by id.
     *
     * @param id
     *            locator
     * @return get text input by id
     */
    public static ITextInput getById(final String id) {
        return get(ControlLocation.getById(id));
    }
    /**
     * Method finds text input by name.
     *
     * @param name
     *            locator
     * @return get text input by name
     */
    public static ITextInput getByName(final String name) {
        return get(ControlLocation.getByName(name));
    }
    /**
     * Method finds text input by css.
     *
     * @param labelSelector
     *            locator
     * @return get text input by css
     */
    public static ITextInput getByCss(final String labelSelector) {
        return get(ControlLocation.getByCss(labelSelector));
    }
    /**
     * Method finds text input by class name.
     *
     * @param className
     *            locator
     * @return get text input by class name
     */
    public static ITextInput getByClassName(final String className) {
        return get(ControlLocation.getByClassName(className));
    }
    /**
     * Method returns text input as WebElement.
     *
     * @param controlLocation
     *            locator
     * @return get text input as WebElement
     */
    private static ITextInput get(final ControlLocation controlLocation) {
        return new TextInput(new ControlWrapper(ContextVisible.get().get(
                controlLocation)));
    }
}
