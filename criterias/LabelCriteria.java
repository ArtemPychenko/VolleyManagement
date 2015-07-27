package com.softserveinc.ita.volleymanagementtests.criterias;

import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces
.ILabel;

/**
 * @author Dp-076 ATQC
 * This class contains criteria for all labels.
 *
 */
public final class LabelCriteria implements ISpecification {
    /**
     * This variable contain current LabelCriteria.
     */
    private ILabel label;
    /**
     * Logger.
     */
    private Specification specification;

    /**
     * The constructor of the class.
     * @param theLabel - aim Label.
     * @param theSpecification - logger.
     */
    private LabelCriteria(final ILabel theLabel,
            final Specification theSpecification) {
        this.label = theLabel;
        this.specification = theSpecification;
    }

    /**
     * Static method for create new LabelCriteria.
     * @param label - aim Label.
     * @param specification - logger.
     * @return new LabelCriteria.
     */
    public static LabelCriteria get(final ILabel label,
            final Specification specification) {
        return new LabelCriteria(label, specification);
    }

    /**
     * This method compare label and input string.
     * @param expectedResult - String for compare.
     * @return current LabelCriteria.
     */
    public LabelCriteria valueMatch(final String expectedResult) {
        this.specification.add(this.label.getText().equals(expectedResult),
                "Label values doesn't match. Expect: " + expectedResult
                + " Actual: " + this.label.getText());
        return this;
    }

    /**
     * @return result, is label is visible.
     */
    public LabelCriteria isVisible() {
        this.specification.add(this.label.isDisplayed(),
                "Label is not visible.");
        return this;
    }

    /**
     * @see com.softserveinc.ita.commentstests.criterias.ISpecification#next().
     * @return specification
     */
    public Specification next() {
        return this.specification;
    }
}
