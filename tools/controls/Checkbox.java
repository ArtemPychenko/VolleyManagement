package com.softserveinc.ita.volleymanagementtests.tools.controls;

import com.softserveinc.ita.volleymanagementtests.tools.ContextVisible;
import com.softserveinc.ita.volleymanagementtests.tools.ControlLocation;
import com.softserveinc.ita.volleymanagementtests.tools.ControlWrapper;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces
.ICheckbox;

/**
 * This class makes a wrapper for checkbox.
 *
 * @author Dp-076 ATQC
 */
public final class Checkbox implements ICheckbox {
    /**
     * Object of ControlWrapper class.
     */
    private ControlWrapper control;

    /**
     * Constructor.
     *
     * @param theControl
     *            - object of ControlWrapper class.
     */
    private Checkbox(final ControlWrapper theControl) {
        control = theControl;
    }

    /**
     * Wrapper for the WebElement method "isEnabled()".
     * @return enabled checkbox
     */
    public boolean isEnabled() {
        return control.isEnabled();
    }

    /**
     * Wrapper for the WebElement method "isSelected()".
     * @return selected checkbox
     */
    public boolean isSelected() {
        return control.isSelected();
    }

    /**
     * Wrapper for the WebElement method "isDisplayed()".
     * @return displayed checkbox
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
     * Wrapper for the WebElement method "click()".
     */
    public void click() {
        control.click();
    }

    /**
     * Wrapper for the WebElement method "getAttribute".
     * @param attributeName
     *            String
     * @return checkbox attributeName
     */
    public String getAttribute(final String attributeName) {
        return control.getAttribute(attributeName);
    }

    /**
     * Method finds checkbox by xpath.
     * @param xpathExpression
     *            locator
     * @return get checkbox by xpath
     */
    public static ICheckbox getByXpath(final String xpathExpression) {
        return get(ControlLocation.getByXPath(xpathExpression));
    }

    /**
     * Method finds checkbox by id.
     * @param id locator
     * @return get checkbox by id
     */
    public static ICheckbox getById(final String id) {
        return get(ControlLocation.getById(id));
    }

    /**
     * Method finds checkboxes by id.
     * @param grid of comments
     * @param index of grid
     * @param id locator
     * @return list of checkboxes
     */
    public static ICheckbox getById(final Grid grid, final int index,
            final String id) {
        return get(grid, index, ControlLocation.getById(id));
    }

    /**
     * Method finds checkbox by name.
     * @param name locator
     * @return get checkbox by name
     */
    public static ICheckbox getByName(final String name) {
        return get(ControlLocation.getByName(name));
    }

    /**
     * Method finds checkboxes by name.
     * @param grid of comments
     * @param index of grid
     * @param name locator
     * @return list of checkboxes
     */
    public static ICheckbox getByName(final Grid grid, final int index,
            final String name) {
        return get(grid, index, ControlLocation.getByName(name));
    }

    /**
     * Method finds checkbox by css.
     * @param cssExpression locator
     * @return get checkbox by css
     */
    public static ICheckbox getByCss(final String cssExpression) {
        return get(ControlLocation.getByCss(cssExpression));
    }

    /**
     * Method finds checkbox by class name.
     * @param className locator
     * @return get checkbox by className
     */
    public static ICheckbox getByClassName(final String className) {
        return get(ControlLocation.getByClassName(className));
    }

    /**
     * Method finds checkboxes by class name.
     * @param grid of comments
     * @param index of grid
     * @param name locator
     * @return list of checkboxes
     */
    public static ICheckbox getByClassName(final Grid grid, final int index,
            final String name) {
        return get(grid, index, ControlLocation.getByClassName(name));
    }

    /**
     * Method finds checkbox by tag name.
     * @param tagName locator
     * @return get checkbox by tagName
     */
    public static ICheckbox getByTagName(final String tagName) {
        return get(ControlLocation.getByTagName(tagName));
    }

    /**
     * Method returns checkbox as WebElement.
     * @param controlLocation locator
     * @return get checkbox as WebElement
     */
    private static ICheckbox get(final ControlLocation controlLocation) {
        return new Checkbox(new ControlWrapper(ContextVisible.get().get(
                controlLocation)));
    }

    /**
     * Method returns checkboxes as WebElements.
     * @param grid of comments
     * @param index of grid
     * @param controlLocation locator
     * @return checkboxes as WebElements
     */
    private static ICheckbox get(final Grid grid, final int index,
            final ControlLocation controlLocation) {
        ControlWrapper element = grid.get(index);
        return new Checkbox(new ControlWrapper(ContextVisible.get()
                .getInParent(element, controlLocation)));
    }
}
