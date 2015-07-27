package com.softserveinc.ita.volleymanagementtests.criterias;

import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces
.IButton;

/**
 * @author Dp-076 ATQC
 * This class is criteria for all buttons.
 */
public final class ButtonCriteria implements ISpecification {
    /**
     * identify of button field throw IButton interface.
     */
    private IButton button;
    /**
     * identify of specification field.
     */
    private Specification specification;

    /**
     * Constructor.
     * @param theButton
     *            - IButton object
     * @param theSpecification
     *            - Specification object
     */
    private ButtonCriteria(final IButton theButton,
            final Specification theSpecification) {
        button = theButton;
        specification = theSpecification;
    }

    /**
     * @param button
     *            - IButton object
     * @param specification
     *            - Specification object
     * @return new criteria for button.
     */
    public static ButtonCriteria get(final IButton button,
            final Specification specification) {
        return new ButtonCriteria(button, specification);
    }
    /**
     * Check is button text matches with text on button.
     * @param expectedResult - expected text for comparison
     * @return specification.
     */
    public ButtonCriteria textMatch(final String expectedResult) {
        this.specification.add(this.button.getText().equals(expectedResult),
                "Button values doesn't match. Expect: " + this.button.getText()
                + " Actual: " + expectedResult);
        return this;
    }
    /**
     * Check is button visible.
     * @return specification.
     */
    public ButtonCriteria isVisible() {
        this.specification.add(this.button.isDisplayed(), "It's not visible.");
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
