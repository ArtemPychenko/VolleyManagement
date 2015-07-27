package com.softserveinc.ita.volleymanagementtests.criterias;

import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces
.ICheckbox;
/**
 * @author Dp-076 ATQC
 * This class is criteria for all checkboxes.
 */
public final class CheckboxCriteria implements ISpecification {
    /**
     * identify of checkbox field throw ICheckbox interface.
     */
    private ICheckbox checkbox;
    /**
     * identify of specification field.
     */
    private Specification specification;
    /**
     * Constructor.
     * @param theCheckbox
     *            - IButton object
     * @param theSpecification
     *            - Specification object
     */
    private CheckboxCriteria(final ICheckbox theCheckbox,
            final Specification theSpecification) {
        checkbox = theCheckbox;
        specification = theSpecification;
    }
    /**
     * @param checkbox
     *            - ICheckbox object
     * @param specification
     *            - Specification object
     * @return new criteria for checkbox.
     */
    public static CheckboxCriteria get(final ICheckbox checkbox,
            final Specification specification) {
        return new CheckboxCriteria(checkbox, specification);
    }
    /**
     * Check is checkbox enabled.
     * @return specification.
     */
    public CheckboxCriteria isEnabled() {
        this.specification
                .add(this.checkbox.isEnabled(), "It's not visible.");
        return this;
    }
    /**
     * Check is checkbox selected.
     * @return specification.
     */
    public CheckboxCriteria isSelected() {
        this.specification
                .add(this.checkbox.isSelected(), "It's not visible.");
        return this;
    }
    /**
     * Check is checkbox visible.
     * @return specification.
     */
    public CheckboxCriteria isVisible() {
        this.specification
                .add(this.checkbox.isDisplayed(), "It's not visible.");
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
