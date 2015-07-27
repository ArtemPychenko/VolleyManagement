package com.softserveinc.ita.volleymanagementtests.tools.controls;

import com.softserveinc.ita.volleymanagementtests.tools.ContextVisible;
import com.softserveinc.ita.volleymanagementtests.tools.ControlLocation;
import com.softserveinc.ita.volleymanagementtests.tools.ControlWrapper;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces
.IValidationLabel;
/**
 * This class makes a wrapper for valid label.
 *
 * @author Dp-076 ATQC
 */
public final class ValidationLabel implements IValidationLabel {
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
    private ValidationLabel(final ControlWrapper theControl) {
        control = theControl;
        if (control.get() == null) {
            control = null;
        }
    }
    /**
     * Wrapper for the WebElement method "get()".
     * @return true if .get() method return element.
     */
    public boolean isPresent() {
        return (control != null);
       }
    /**
     * Wrapper for the WebElement method "getText()".
     *
     * @return text
     */
    public String getText() {
        return control.getText();
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
     * Method finds valid label by xpath.
     *
     * @param xpathExpression
     *            locator
     * @return get valid label by xpath
     */
    public static IValidationLabel getByXpath(final String xpathExpression) {
        return get(ControlLocation.getByXPath(xpathExpression));
    }
    /**
     * Method finds valid label by id.
     *
     * @param id
     *            locator
     * @return get valid label by id
     */
    public static IValidationLabel getById(final String id) {
        return get(ControlLocation.getById(id));
    }
    /**
     * Method finds label by tag name.
     *
     * @param tagName
     *            locator
     * @return get label by tag name
     */
    public static IValidationLabel getTagName(final String tagName) {
        return get(ControlLocation.getByTagName(tagName));
    }
    /**
     * Method finds valid label by name.
     *
     * @param name
     *            locator
     * @return get valid label by name
     */
    public static IValidationLabel getByName(final String name) {
        return get(ControlLocation.getByName(name));
    }
    /**
     * Method finds valid label by css.
     *
     * @param labelSelector
     *            locator
     * @return get valid label by css
     */
    public static IValidationLabel getByCss(final String labelSelector) {
        return get(ControlLocation.getByCss(labelSelector));
    }
    /**
     * Method finds valid label by class name.
     *
     * @param className
     *            locator
     * @return get valid label by class name
     */
    public static IValidationLabel getByClassName(final String className) {
        return get(ControlLocation.getByClassName(className));
    }
    /**
     * Method returns valid label as WebElement.
     *
     * @param controlLocation
     *            locator
     * @return get valid label as WebElement
     */
    private static IValidationLabel get(final ControlLocation controlLocation) {
        return new ValidationLabel(new ControlWrapper(ContextVisible.get().get(
                controlLocation), true));
    }
}
