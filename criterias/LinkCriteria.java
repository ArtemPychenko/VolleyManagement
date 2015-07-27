package com.softserveinc.ita.volleymanagementtests.criterias;

import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces
.ILink;
/**
 * @author Dp-076 ATQC
 * This class is criteria for all links.
 */
public final class LinkCriteria implements ISpecification {
    /**
     * identify of link field throw ILink interface.
     */
    private ILink link;
    /**
     * identify of specification field.
     */
    private Specification specification;
    /**
     * Constructor.
     * @param theLink
     *            - ILink object
     * @param theSpecification
     *            - Specification object
     */
    private LinkCriteria(final ILink theLink,
            final Specification theSpecification) {
        link = theLink;
        specification = theSpecification;
    }
    /**
     * @param link
     *            - ILink object
     * @param specification
     *            - Specification object
     * @return new criteria for link.
     */
    public static LinkCriteria get(final ILink link,
            final Specification specification) {
        return new LinkCriteria(link, specification);
    }
    /**
     * Check is link value matches with value of link.
     * @param expectedResult - expected text for comparison
     * @return specification.
     */
    public LinkCriteria valueMatch(final String expectedResult) {
        this.specification.add(this.link.getText().equals(expectedResult),
                "Link values doesn't match. Expect:" + expectedResult
                + "Actual:" + this.link.getText());
        return this;
    }
    /**
     * Check is link visible.
     * @return specification.
     */
    public LinkCriteria isVisible() {
        this.specification.add(this.link.isDisplayed(), "It's not visible.");
        return this;
    }
    /**
     * Next specification.
     * @return specification.
     */
    public Specification next() {
        return this.specification;
    }
}
