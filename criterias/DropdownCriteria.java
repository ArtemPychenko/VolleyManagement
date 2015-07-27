package com.softserveinc.ita.volleymanagementtests.criterias;

import java.util.List;

import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces
.IDropdown;
/**
 * @author Dp-076 ATQC
 * This class is criteria for all buttons.
 */
public final class DropdownCriteria implements ISpecification {
    /**
     * identify of button field throw IButton interface.
     */
    private IDropdown dropdown;
    /**
     * identify of specification field.
     */
    private Specification specification;
    /**
     * Constructor.
     * @param theDropdown
     *            - IDropdown object
     * @param theSpecification
     *            - Specification object
     */
    private DropdownCriteria(final IDropdown theDropdown,
            final Specification theSpecification) {
        dropdown = theDropdown;
        specification = theSpecification;
    }
    /**
     * @param dropdown
     *            - IDropdown object
     * @param specification
     *            - Specification object
     * @return new criteria for button.
     */
    public static DropdownCriteria get(final IDropdown dropdown,
            final Specification specification) {
        return new DropdownCriteria(dropdown, specification);
    }
    /**
     * Check is selected value matches with expected value.
     * @param expectedResult - expected value for comparison
     * @return specification.
     */
    public DropdownCriteria selectedValueMatch(final String expectedResult) {
        this.specification.add(this.dropdown.getSelectedValuesList().get(0)
                .equals(expectedResult),
                "Default dropdown value doesn't match.");
        return this;
    }
    /**
     * Check is dropdown values matches with expected values.
     * @param expectedValues - expected value for comparison.
     * @return specification.
     */
    public DropdownCriteria valuesMatch(
                                final List<String> expectedValues) {
        this.specification.add(
                this.dropdown.getValuesList().equals(expectedValues)
                , "Dropdown dont contain expect values");
        return this;
    }

    /**
     * Check are available values matches with expected available values.
     * @param expectedResult - expected values for comparison
     * @return specification.
     */
    public DropdownCriteria availableValuesMatch(
            final List<String> expectedResult) {
        this.specification.add(this.dropdown.getValuesList()
                .equals(expectedResult),
                "Avialeble dropdown values dont match.");
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
