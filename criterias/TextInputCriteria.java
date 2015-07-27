package com.softserveinc.ita.volleymanagementtests.criterias;

import com.softserveinc.ita.volleymanagementtests.domain.repositories
.TournamentTestObjects;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces
.ITextInput;
/**
 * @author Dp-076 ATQC
 * This class is criteria for all text inputs.
 */
public final class TextInputCriteria implements ISpecification {
    /**
     * identify of fext input field throw ITextInput interface.
     */
    private ITextInput textInput;
    /**
     * identify of specification field.
     */
    private Specification specification;
    /**
     * Constructor.
     * @param theTextInput
     *            - ITextInput object
     * @param theSpecification
     *            - Specification object
     */
    private TextInputCriteria(final ITextInput theTextInput,
            final Specification theSpecification) {
        textInput = theTextInput;
        specification = theSpecification;
    }
    /**
     * @param textInput
     *            - ITextInput object
     * @param specification
     *            - Specification object
     * @return new criteria for text input.
     */
    public static TextInputCriteria get(
            final ITextInput textInput, final Specification specification) {
        return new TextInputCriteria(textInput, specification);
    }
    /**
     * Check is text input color matches with expected text input color.
     * @param expectedResult - expected color for comparison
     * @return specification.
     */
    public TextInputCriteria colorMatch(final String expectedResult) {
        String textInputColor = "";
        if (this.textInput.getAttributeClass()
                .equals("text-box single-line input-validation-error")) {
            textInputColor = "Highlighted";
        }
        this.specification.add(textInputColor.equals(expectedResult),
                "Color of text input is not valid. ");
        return this;
    }
    /**
     * Check is text input visible.
     * @return specification.
     */
    public TextInputCriteria isVisible() {
        this.specification.add(this.textInput.isDisplayed()
                , "Text input is not visible. ");
        return this;
    }
    /**
     * Check is the text input empty.
     * @return specification.
     */
    public TextInputCriteria isEmpty() {
        this.specification.add(this.textInput.getText().equals(new String())
                , " Text input is not empty. ");
        return this;
    }
    /**
     * Check is the text input contains string.
     * @param expectedResult - string for check.
     * @return check result.
     */
    public TextInputCriteria isContain(final String expectedResult) {
        this.specification.add(this.textInput.getText().equals(expectedResult),
                "Text input is not contain: "
        + expectedResult + " Text input contain:"
                        + this.textInput.getText());
        return this;
    }
    /**
     * Check is the text input placeholder contains string.
     * @param expectedResult - string for check.
     * @return check result.
     */
    public TextInputCriteria isContainPlaceholder(final String expectedResult) {
        this.specification.add(this.textInput.getAttributePlaceholder()
                .equals(expectedResult)
                , "Text input is not contain placeholder: " + expectedResult);
        return this;
    }
    /**
     * Check is data in inputs is in Date format "dd.MM.uuuu".
     * @return check result.
     */
    public TextInputCriteria isDataInDateFormat() {
        this.specification.add(TournamentTestObjects.isDateformatCorrect(
                this.textInput.getText()),
                "The date in text fiel is not correct, required: dd.mm.yyyy ");
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
