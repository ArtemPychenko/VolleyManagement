package com.softserveinc.ita.volleymanagementtests.tools.controls;

import com.softserveinc.ita.volleymanagementtests.tools.ContextVisible;
import com.softserveinc.ita.volleymanagementtests.tools.ControlLocation;
import com.softserveinc.ita.volleymanagementtests.tools.ControlWrapper;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces
.ILabel;

/**
 * This class makes a wrapper for Label.
 *
 * @author Dp-076 ATQC
 */
public final class Label implements ILabel {
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
    private Label(final ControlWrapper theControl) {
        control = theControl;
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
     * Wrapper for the WebElement method "get()".
     * @return true if .get() method return element.
     */
    public boolean isPresent() {
        return (control.get() != null);
       }
    /**
     * Method finds label by xpath.
     *
     * @param xpathExpression
     *            locator
     * @return get label by xpath
     */
    public static ILabel getByXpath(final String xpathExpression) {
        return get(ControlLocation.getByXPath(xpathExpression));
    }

    /**
     * Method finds label by id.
     *
     * @param id
     *            locator
     * @return get label by id
     */
    public static ILabel getById(final String id) {
        return get(ControlLocation.getById(id));
    }

    /**
     * Method finds label by name.
     *
     * @param name
     *            locator
     * @return get label by name
     */
    public static ILabel getByName(final String name) {
        return get(ControlLocation.getByName(name));
    }

    /**
     * Method finds labels by name.
     *
     * @param grid
     *            of comments
     * @param index
     *            of grid
     * @param name
     *            locator
     * @return list of labels
     */
    public static ILabel getByName(final Grid grid, final int index,
            final String name) {
        return get(grid, index, ControlLocation.getByName(name));
    }

    /**
     * Method finds label by css.
     *
     * @param labelSelector
     *            locator
     * @return get label by css
     */
    public static ILabel getByCss(final String labelSelector) {
        return get(ControlLocation.getByCss(labelSelector));
    }

    /**
     * Method finds label by class name.
     *
     * @param className
     *            locator
     * @return get label by class name
     */
    public static ILabel getByClassName(final String className) {
        return get(ControlLocation.getByClassName(className));
    }

    /**
     * Method finds labels by class name.
     *
     * @param grid
     *            of comments
     * @param index
     *            of grid
     * @param name
     *            locator
     * @return list of labels
     */
    public static ILabel getByClassName(final Grid grid, final int index,
            final String name) {
        return get(grid, index, ControlLocation.getByClassName(name));
    }

    /**
     * Method finds labels by tag name.
     *
     * @param grid
     *            of comments
     * @param index
     *            of grid
     * @param name
     *            locator
     * @return list of labels
     */
    public static ILabel getByTagName(final Grid grid, final int index,
            final String name) {
        return get(grid, index, ControlLocation.getByTagName(name));
    }

    /**
     * Method returns label as WebElement.
     *
     * @param controlLocation
     *            locator
     * @return get label as WebElement
     */
    private static ILabel get(final ControlLocation controlLocation) {
        return new Label(new ControlWrapper(ContextVisible.get().get(
                controlLocation)));
    }

    /**
     * Method returns labels as WebElements.
     *
     * @param grid
     *            of comments
     * @param index
     *            of grid
     * @param controlLocation
     *            locator
     * @return labels as WebElements
     */
    private static ILabel get(final Grid grid, final int index,
            final ControlLocation controlLocation) {
        ControlWrapper element = grid.get(index);
        return new Label(new ControlWrapper(ContextVisible.get().getInParent(
                element, controlLocation)));
    }
}
