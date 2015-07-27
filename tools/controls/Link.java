package com.softserveinc.ita.volleymanagementtests.tools.controls;

import com.softserveinc.ita.volleymanagementtests.tools.ContextVisible;
import com.softserveinc.ita.volleymanagementtests.tools.ControlLocation;
import com.softserveinc.ita.volleymanagementtests.tools.ControlWrapper;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces
.ILink;
/**
 * This class makes a wrapper for Link.
 *
 * @author Dp-076 ATQC
 */
public final class Link implements ILink {
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
    private Link(final ControlWrapper theControl) {
        control = theControl;
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
     * Method finds link by id.
     *
     * @param id
     *            locator
     * @return get link by id
     */
    public static ILink getById(final String id) {
        return get(ControlLocation.getById(id));
    }
    /**
     * Method finds link by name.
     *
     * @param searchedName
     *            locator
     * @return get link by name
     */
    public static ILink getByName(final String searchedName) {
        return get(ControlLocation.getByName(searchedName));
    }
    /**
     * Method finds link by xpath.
     *
     * @param xpathExpression
     *            locator
     * @return get link by xpath
     */
    public static ILink getByXpath(final String xpathExpression) {
        return get(ControlLocation.getByXPath(xpathExpression));
    }
    /**
     * Method finds link by link text.
     *
     * @param linkText
     *            locator
     * @return get link by link text
     */
    public static ILink getByLinkText(final String linkText) {
        return get(ControlLocation.getByLink(linkText));
    }
    /**
     * Method finds link by css.
     *
     * @param linkSelector
     *            locator
     * @return get link by css
     */
    public static ILink getByCss(final String linkSelector) {
        return get(ControlLocation.getByCss(linkSelector));
    }
    /**
     * Method finds link by class name.
     *
     * @param className
     *            locator
     * @return get link by class name
     */
    public static ILink getByClassName(final String className) {
        return get(ControlLocation.getByClassName(className));
    }
    /**
     * Method returns link as WebElement.
     *
     * @param controlLocation
     *            locator
     * @return get link as WebElement
     */
    public static ILink get(final ControlLocation controlLocation) {
        return new Link(new ControlWrapper(ContextVisible.get().get(
                controlLocation)));
    }
    /**
     * Wrapper for the WebElement method "get()".
     * @return true if .get() method return element.
     */
    public boolean isPresent() {
        return (control.get() != null);
       }
}
