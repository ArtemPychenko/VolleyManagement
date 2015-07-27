package com.softserveinc.ita.volleymanagementtests.tools.controls;

import com.softserveinc.ita.volleymanagementtests.tools.ContextVisible;
import com.softserveinc.ita.volleymanagementtests.tools.ControlLocation;
import com.softserveinc.ita.volleymanagementtests.tools.ControlWrapper;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces
.IButton;
/**
 * This class makes a wrapper for button.
 *
 * @author Dp-076 ATQC
 */
public final class Button implements IButton {
    /**
     * Object of ControlWrapper class.
     */
    private ControlWrapper control;
    /**
     * Constructor.
     * @param theControl is object of ControlWrapper class.
     */
    private Button(final ControlWrapper theControl) {
        control = theControl;
    }
    /**
     * Wrapper for the WebElement method "click()".
     */
    public void click() {
        control.click();
    }
    /**
     * Wrapper for the WebElement method "isDisplayed()".
     * @return boolean isDisplayed
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
     * @return String text
     */
    public String getText() {
        return control.getText();
    }
    /**
     * Method finds button by id.
     * @param id locator
     * @return location by id
     */
    public static IButton getById(final String id) {
        return get(ControlLocation.getById(id));
    }
    /**
     * Method finds button by name.
     * @param searchedName locator
     * @return location by name
     */
    public static IButton getByName(final String searchedName) {
        return get(ControlLocation.getByName(searchedName));
    }
    /**
     * Method finds button by xpath.
     * @param xpathExpression locator
     * @return location by xpath
     */
    public static IButton getByXpath(final String xpathExpression) {
        return get(ControlLocation.getByXPath(xpathExpression));
    }
    /**
     * Method finds button by css.
     * @param cssExpression locator
     * @return location by css
     */
    public static IButton getByCss(final String cssExpression) {
        return get(ControlLocation.getByCss(cssExpression));
    }
    /**
     * Method finds button by class name.
     * @param className locator
     * @return location by class name
     */
    public static IButton getByClassName(final String className) {
        return get(ControlLocation.getByClassName(className));
    }
    /**
     * Method finds button by tag name.
     * @param tagName locator
     * @return location by tag name
     */
    public static IButton getByTagName(final String tagName) {
        return get(ControlLocation.getByTagName(tagName));
    }
    /**
     * Method returns button as WebElement.
     * @param controlLocation is object of ControlLocation class
     * @return button location
     */
    public static IButton get(final ControlLocation controlLocation) {
        return new Button(new ControlWrapper(ContextVisible.get().get(
                controlLocation)));
    }
}
