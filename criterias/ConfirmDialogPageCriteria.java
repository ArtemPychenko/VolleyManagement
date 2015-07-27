package com.softserveinc.ita.volleymanagementtests.criterias;

import com.softserveinc.ita.volleymanagementtests.pages.ConfirmDialogPage;

/**
 * @author Dp-076 ATQC
 * This class contain methods for
 * check Confirm dialog alert object on different criteria.
 */
public final class ConfirmDialogPageCriteria implements ISpecification {

    /**
     *This variable contain current ConfirmDialogCriteria.
     */
    private ConfirmDialogPage dialog;

    /**
     * Logger.
     */
    private Specification specification;

    /**
     * Class constructor.
     * @param dialogParam -aim ConfirmDialogCriteria.
     * @param specificationParam - logger.
     */
    private ConfirmDialogPageCriteria(final ConfirmDialogPage dialogParam,
            final Specification specificationParam) {
        this.dialog = dialogParam;
        this.specification = specificationParam;
    }

    /**
     * Static method for create new ConfirmDialogCriteria.
     * @param dialogParam -aim ErrorDialogPage.
     * @param specificationParam - logger.
     * @return new ConfirmDialogPage
     */
    public static ConfirmDialogPageCriteria get(
            final ConfirmDialogPage dialogParam,
            final Specification specificationParam) {
        return new ConfirmDialogPageCriteria(dialogParam, specificationParam);
    }

    /**
     * This method compare text label of Confirm dialog alert
     * and input string.
     * @param expectedResult - String for compare.
     * @return current ConfirmDialogCriteria.
     */
    public ConfirmDialogPageCriteria textDeletePlayerMatch(
            final String expectedResult) {
        this.specification.add(this.dialog.getMessage().equals(expectedResult),
                " Text of Confirm Player Delete message is not valid;");
        return this;
    }
    /**
     * This method compare text label of Confirm dialog alert
     * and input string.
     * @param expectedResult - String for compare.
     * @return current ConfirmDialogCriteria.
     */
    public ConfirmDialogPageCriteria textDeleteTournamentMatch(
            final String expectedResult) {
        this.specification.add(this.dialog.getMessage().equals(expectedResult),
                " Text of Confirm Tournament Delete message is not valid;");
        return this;
    }
    /**
     * This method compare text label of Confirm dialog alert
     * and input string.
     * @param expectedResult - String for compare.
     * @return current ConfirmDialogCriteria.
     */
    public ConfirmDialogPageCriteria textDeleteTeamMatch(
            final String expectedResult) {
        this.specification.add(this.dialog.getMessage().equals(expectedResult),
                " Text of Confirm Team Delete message is not valid;");
        return this;
    }

    /**
     * This method checks, is "Yes" button presents on Confirm dialog alert,
     * and then compare text label of this button with input string.
     * @param expectedResult - String for compare.
     * @return current ConfirmDialogCriteria.
     */
    public ConfirmDialogPageCriteria buttonYesMatch(
            final String expectedResult) {
        this.specification.add(this.dialog.getYesButton().isDisplayed(),
                " Button(Yes) on Confirm Delete message is not present;");
        if (this.dialog.getYesButton().isDisplayed()) {
            this.specification.add(
                    this.dialog.getYesButton().getText().equals(expectedResult),
                    " Text on the Confirm Delete message Button(Yes)"
                    + " is not valid;");
        }

        return this;
    }

    /**
     * This method checks, is "No" button presents on Confirm dialog alert,
     * and then compare text label of this button with input string.
     * @param expectedResult - String for compare.
     * @return current ConfirmDialogCriteria.
     */
    public ConfirmDialogPageCriteria buttonNoMatch(
            final String expectedResult) {
        this.specification.add(this.dialog.getNoButton().isDisplayed(),
                " Button(No) on Confirm Delete message is not present;");
        if (this.dialog.getNoButton().isDisplayed()) {
            this.specification.add(
                    this.dialog.getNoButton().getText().equals(expectedResult),
                    " Text on the Confirm Delete message Button(No)"
                    + " is not valid;");
        }
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
