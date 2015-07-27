package com.softserveinc.ita.volleymanagementtests.criterias;

import com.softserveinc.ita.volleymanagementtests.pages.Page;
/**
 * @author Dp-076 ATQC
 * This class is criteria for all pages.
 */
public final class PageCriteria implements ISpecification {
    /**
     * identify page.
     */
    private Page page;
    /**
     * identify of specification field.
     */
    private Specification specification;
    /**
     * Constructor.
     * @param thePage - page object
     * @param theSpecification - Specification object
     */
    private PageCriteria(final Page thePage,
            final Specification theSpecification) {
        this.page = thePage;
        this.specification = theSpecification;
    }
    /**
     * @param page - page object
     * @param specification - Specification object
     * @return new criteria for page.
     */
    public static PageCriteria get(final Page page,
            final Specification specification) {
        return new PageCriteria(page, specification);
    }
    /**
     * Check is page matches with page.
     * @param expectedPage - expected object for comparison
     * @return specification.
     */
    public PageCriteria pageMatch(final Page expectedPage) {
        this.specification.add(this.page.equals(expectedPage),
                "Page doesn't match.");
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
