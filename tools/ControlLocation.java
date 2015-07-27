package com.softserveinc.ita.volleymanagementtests.tools;

import org.openqa.selenium.By;

/**
 * @author Dp-076 ATQC This is wrapper class for finding WebElements.
 */
public final class ControlLocation {
    /**
     * locator of searching element.
     */
    private By by;
    /**
     * value for using in methods.
     */
    private String value;

    /**
     * Constructor.
     * @param theBy
     *            - locator of searching element
     * @param theValue
     *            - locator of searching element
     */
    private ControlLocation(final By theBy, final String theValue) {
        value = theValue;
        by = theBy;
    }

    /**
     * Finding WebElement by class name.
     * @param className
     *            - locator of searching element
     * @return WebElement finding by class name
     */
    public static ControlLocation getByClassName(final String className) {
        return new ControlLocation(By.className(className), className);
    }

    /**
     * Finding WebElement by css.
     * @param selector
     *            - locator of searching element
     * @return WebElement finding by css
     */
    public static ControlLocation getByCss(final String selector) {
        return new ControlLocation(By.cssSelector(selector), selector);
    }

    /**
     * Finding WebElement by id.
     * @param id
     *            - locator of searching element
     * @return WebElement finding by id
     */
    public static ControlLocation getById(final String id) {
        return new ControlLocation(By.id(id), id);
    }

    /**
     * Finding WebElement by link text.
     * @param linkText
     *            - locator of searching element
     * @return WebElement finding by link text
     */
    public static ControlLocation getByLink(final String linkText) {
        return new ControlLocation(By.linkText(linkText), linkText);
    }

    /**
     * Finding WebElement by name.
     * @param searchName
     *            - locator of searching element
     * @return WebElement finding by name
     */
    public static ControlLocation getByName(final String searchName) {
        return new ControlLocation(By.name(searchName), searchName);
    }

    /**
     * Finding WebElement by part of link text.
     * @param partialLinkText
     *            - locator of searching element
     * @return WebElement finding by part of link text
     */
    public static ControlLocation getByPartialLink(final String
            partialLinkText) {
        return new ControlLocation(By.partialLinkText(partialLinkText),
                partialLinkText);
    }
    /**
     * Finding WebElement by tag name.
     * @param tagName - locator of searching element
     * @return WebElement finding by tag name
     */
    public static ControlLocation getByTagName(final String tagName) {
        return new ControlLocation(By.tagName(tagName), tagName);
    }
    /**
     * Finding WebElement by xpath.
     * @param xpathExpression - locator of searching element
     * @return WebElement finding by xpath
     */
    public static ControlLocation getByXPath(final String xpathExpression) {
        return new ControlLocation(By.xpath(xpathExpression), xpathExpression);
    }
    /**
     * Getter for value.
     * @return value
     */
    public String getValue() {
        return value;
    }
    /**
     * Getter for By.
     * @return By
     */
    public By getBy() {
        return by;
    }

}
