package com.softserveinc.ita.volleymanagementtests.pages;



import com.softserveinc.ita.volleymanagementtests.domain.models.Tournament;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IButton;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ICalendar;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IDropdown;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ILabel;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ITextInput;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IValidationLabel;
/**
 * @author Dp-076 ATQC
 * This class is a superclass for new tournament page and edit tournament page.
 * So kept the most of logic in it.
 */
public class TournamentDataPage {
    /**
     * @param controls encapsulates map of page.
     */
    private TournamentDataPageUIMap controls;
    /**
     * Initialization of tournament data page ui map to get the control of
     * page's element's location.
     */
    public TournamentDataPage() {
        controls = new TournamentDataPageUIMap();
    }
    /**
     * @return validation error message of name input.
     */
    public final IValidationLabel getErrorMessageLabelName() {
        return controls.getErrorMessageLabelTournamentName();
    }
    /**
     * @return validation error message of description input.
     */
    public final IValidationLabel getErrorMessageLabelDescription() {
        return controls.getErrorMessageLabelDescription();
    }
    /**
     * @return validation error message of regulations link input.
     */
    public final IValidationLabel getErrorMessageLabelRegulationsLink() {
        return controls.getErrorMessageLabelRegulationsLink();
    }
    /**
     * @return validation error message of applying start input.
     */
    public final IValidationLabel getErrorMessageLabelApplyingStart() {
        return controls.getErrorMessageLabelApplyingStart();
    }
    /**
     * @return validation error message of applying end input.
     */
    public final IValidationLabel getErrorMessageLabelApplyingEnd() {
        return controls.getErrorMessageLabelApplyingEnd();
    }
    /**
     * @return validation error message of tournament start input.
     */
    public final IValidationLabel getErrorMessageLabelTournamentStart() {
        return controls.getErrorMessageLabelTournamentStart();
    }
    /**
     * @return validation error message of tournament end input.
     */
    public final IValidationLabel getErrorMessageLabelTournamentEnd() {
        return controls.getErrorMessageLabelTournamentEnd();
    }
    /**
     * @return validation error message of transfer start input.
     */
    public final IValidationLabel getErrorMessageLabelTransferStart() {
        return controls.getErrorMessageLabelTransferStart();
    }
    /**
     * @return validation error message of transfer end input.
     */
    public final IValidationLabel getErrorMessageLabelTransferEnd() {
        return controls.getErrorMessageLabelTransferEnd();
    }

    /**
     * @return page title.
     */
    public final ILabel getTitleLabel() {
        return controls.getPageTitleLabel();
    }
    /**
     * @return name label.
     */
    public final ILabel getTournamentNameLabel() {
        return controls.getTornamentNameLabel();
    }
    /**
     * @return name input.
     */
    public final ITextInput getTournamentNameInput() {
        return controls.getTournamentNameInput();
    }
    /**
     * @return description label.
     */
    public final ILabel getDescriptionLabel() {
        return controls.getDescriptionLabel();
    }
    /**
     * @return description input.
     */
    public final ITextInput getDescriptionInput() {
        return controls.getDescriptionInput();
    }
    /**
     * @return season label.
     */
    public final ILabel getSeasonLabel() {
        return controls.getSeasonLabel();
    }
    /**
     * @return season dropdown.
     */
    public final IDropdown getSeasonDropdown() {
        return controls.getSeasonDropdown();
    }
    /**
     * @return applying terms label.
     */
    public final ILabel getApplyingTermsLabel() {
        return controls.getApplyingTermsLabel();
    }
    /**
     * @return calendar.
     */
    public final ICalendar getCalendar() {
        return controls.getCalendar();
    }
    /**
     * @return applying start button.
     */
    public final IButton getApplyingStartButton() {
        return controls.getApplyingStartButton();
    }
    /**
     * @return applying end button.
     */
    public final IButton getApplyingEndButton() {
        return controls.getApplyingEndButton();
    }
    /**
     * @return tournament start button.
     */
    public final IButton getTournamentStartButton() {
        return controls.getTournamentStartButton();
    }
    /**
     * @return applying end button.
     */
    public final IButton getTournamentEndButton() {
        return controls.getTournamentEndButton();
    }
    /**
     * @return transfer start button.
     */
    public final IButton getTransferStartButton() {
        return controls.getTransferStartButton();
    }
    /**
     * @return transfer end button.
     */
    public final IButton getTransferEndButton() {
        return controls.getTransferEndButton();
    }
    /**
     * @return applying start input.
     */
    public final ITextInput getApplyingStartInput() {
        return controls.getApplyingStartInput();
    }
    /**
     * @return applying end input.
     */
    public final ITextInput getApplyingEndInput() {
        return controls.getApplyingEndInput();
    }
    
    public final ILabel getTournamentTermsLabel() {
        return controls.getTournamentTermsLabel();
    }
    /**
     * @return tournament start input.
     */
    public final ITextInput getTournamentStartInput() {
        return controls.getTournamentStartInput();
    }
    /**
     * @return tournament end input.
     */
    public final ITextInput getTournamentEndInput() {
        return controls.getTournamentEndInput();
    }
    /**
     * @return transfer start label.
     */
    public final ILabel getTransferPeriodTermsLabel() {
        return controls.getTransferPeriodTermsLabel();
    }
    /**
     * @return transfer start input.
     */
    public final ITextInput getTransferStartInput() {
        return controls.getTransferStartInput();
    }
    /**
     * @return transfer end input.
     */
    public final ITextInput getTransferEndInput() {
        return controls.getTransferEndInput();
    }
    /**
     * @return scheme label.
     */
    public final ILabel getSchemeLabel() {
        return controls.getSchemeLabel();
    }
    /**
     * @return scheme dropdown.
     */
    public final IDropdown getSchemeDropdown() {
        return controls.getSchemeDropdown();
    }
    /**
     * @return scheme label.
     */
    public final ILabel getLinkToReglamentLabel() {
        return controls.getLinkToReglamentLabel();
    }
    /**
     * @return reglament input.
     */
    public final ITextInput getLinkToReglamentInput() {
        return controls.getLinkToReglamentInput();
    }
    /**
     * @param tournament is an instance of team model.
     * @return tournament data page.
     * typing the tournament name to the name input.
     */
    public final TournamentDataPage setTournamentName(
            final Tournament tournament) {
        controls.getTournamentNameInput().click();
        controls.getTournamentNameInput().clear();
        controls.getTournamentNameInput().type(tournament.getName());
        return this;
    }
    /**
     * @param tournament is an instance of team model.
     * @return tournament data page.
     * typing the tournament description to the description input.
     */
    public final TournamentDataPage setDescription(
            final Tournament tournament) {
        controls.getDescriptionInput().click();
        controls.getDescriptionInput().clear();
        controls.getDescriptionInput().type(tournament.getDescription());
        return this;
    }
    /**
     * @param description is a string to type to the description input.
     * @return tournament data page.
     * typing the tournament description to the description input.
     */
    public final TournamentDataPage setDescription(
            final String description) {
        controls.getDescriptionInput().click();
        controls.getDescriptionInput().clear();
        controls.getDescriptionInput().type(description);
        return this;
    }
    /**
     * @param tournament is an instance of team model.
     * @return tournament data page.
     * selects the season by visible text.
     */
    public final TournamentDataPage setSeason(final Tournament tournament) {
        controls.getSeasonDropdown().selectByVisibleText(tournament
                .getSeasonForUI());
        return this;
    }
    /**
     * @param tournament is an instance of team model.
     * @return tournament data page.
     * typing the tournament regulations link to the regulations input.
     */
    public final TournamentDataPage setLinkToReglamentInput(
            final Tournament tournament) {
        controls.getLinkToReglamentInput().click();
        controls.getLinkToReglamentInput().clear();
        controls.getLinkToReglamentInput().type(tournament
                .getRegulationsLink());
        return this;
    }
    /**
     * @param tournament is an instance of team model.
     * @return tournament data page.
     * typing the applying start date to the applying start input.
     */
    public final TournamentDataPage setApplyingStartInput(
            final Tournament tournament) {
        controls.getApplyingStartInput().click();
        controls.getApplyingStartInput().clear();
        controls.getApplyingStartInput().type(tournament
                .getApplyingPeriodStartForUI());
        return this;
    }
    /**
     * @param tournament is an instance of team model.
     * @return tournament data page.
     * typing the applying end date to the applying end input.
     */
    public final TournamentDataPage setApplyingEndInput(
            final Tournament tournament) {
        controls.getApplyingEndInput().click();
        controls.getApplyingEndInput().clear();
        controls.getApplyingEndInput().type(tournament
                .getApplyingPeriodEndForUI());
        return this;
    }
    /**
     * @param tournament is a string to type to the field.
     * @return tournament data page.
     * typing the start date to the applying start input.
     */
    public final TournamentDataPage setApplyingStartInput(
            final String tournament) {
        controls.getApplyingStartInput().click();
        controls.getApplyingStartInput().clear();
        controls.getApplyingStartInput().type(tournament);
        return this;
    }
    /**
     * @param tournament is a string to type to the field.
     * @return tournament data page.
     * typing the applying end date to the applying end input.
     */
    public final TournamentDataPage setApplyingEndInput(
            final String tournament) {
        controls.getApplyingEndInput().click();
        controls.getApplyingEndInput().clear();
        controls.getApplyingEndInput().type(tournament);
        return this;
    }
    /**
     * @param tournament is an instance of team model.
     * @return tournament data page.
     * typing the tournament start date to the tournament start input.
     */
    public final TournamentDataPage setTournamentStartInput(
            final Tournament tournament) {
        controls.getTournamentStartInput().click();
        controls.getTournamentStartInput().clear();
        controls.getTournamentStartInput().type(tournament
                .getGamesStartForUI());
        return this;
    }
    /**
     * @param tournament is an instance of team model.
     * @return tournament data page.
     * typing the tournament end date to the tournament end input.
     */
    public final TournamentDataPage setTournamentEndInput(
            final Tournament tournament) {
        controls.getTournamentEndInput().click();
        controls.getTournamentEndInput().clear();
        controls.getTournamentEndInput().type(tournament.getGamesEndForUI());
        return this;
    }
    /**
     * @param tournament is a string to type to the field.
     * @return tournament data page.
     * typing the tournament start date to the tournament start input.
     */
    public final TournamentDataPage setTournamentStartInput(
            final String tournament) {
        controls.getTournamentStartInput().click();
        controls.getTournamentStartInput().clear();
        controls.getTournamentStartInput().type(tournament);
        return this;
    }
    /**
     * @param tournament is a string to type to the field.
     * @return tournament data page.
     * typing the tournament end date to the tournament end input.
     */
    public final TournamentDataPage setTournamentEndInput(
            final String tournament) {
        controls.getTournamentEndInput().click();
        controls.getTournamentEndInput().clear();
        controls.getTournamentEndInput().type(tournament);
        return this;
    }
    /**
     * @param tournament is an instance of team model.
     * @return tournament data page.
     * typing the transfer start date to the transfer start input.
     */
    public final TournamentDataPage setTransferStartInput(
            final Tournament tournament) {
        controls.getTransferStartInput().click();
        controls.getTransferStartInput().clear();
        controls.getTransferStartInput().type(tournament
                .getTransferStartForUI());
        return this;
    }
    /**
     * @param tournament is a string to type to the field.
     * @return tournament data page.
     * typing the transfer start date to the transfer start input.
     */
    public final TournamentDataPage setTransferStartInput(final String tournament) {
        controls.getTransferStartInput().click();
        controls.getTransferStartInput().clear();
        controls.getTransferStartInput().type(tournament);
        return this;
    }
    /**
     * @param tournament is an instance of team model.
     * @return tournament data page.
     * typing the transfer end date to the transfer end input.
     */
    public final TournamentDataPage setTransferEndInput(
            final Tournament tournament) {
        controls.getTransferEndInput().click();
        controls.getTransferEndInput().clear();
        controls.getTransferEndInput().type(tournament.getTransferEndForUI());
        return this;
    }
    /**
     * @param tournament is a string to type to the field.
     * @return tournament data page.
     * typing the tournament end date to the tournament end input.
     */
    public final TournamentDataPage setTransferEndInput(
            final String tournament) {
        controls.getTransferEndInput().click();
        controls.getTransferEndInput().clear();
        controls.getTransferEndInput().type(tournament);
        return this;
    }
    /**
     * This method gets Tournament and makes chose dropdown
     * option according to scheme field in tournament object.
     * @param tournament is an instance of team model.
     * @return TournamentDataPage
     */
    public final TournamentDataPage setScheme(final Tournament tournament) {
        String scheme;
        if (tournament.getScheme() == 3) {
            scheme = "2.5";
        } else {
            scheme = String.valueOf(tournament.getScheme());
        }
        controls.getSchemeDropdown().selectByVisibleText(scheme);
        return this;
    }

    /**.
     * This method sets all fields from Tournament object to TournamentDatePage
     * @param tournament is an instance of team model.
     * @return TournamentDataPage
     */
    public final TournamentDataPage setAllFields(final Tournament tournament) {
        setTournamentName(tournament);
        setDescription(tournament);
        setSeason(tournament);
        setApplyingStartInput(tournament);
        setApplyingEndInput(tournament);
        setTournamentStartInput(tournament);
        setTournamentEndInput(tournament);
        setTransferStartInput(tournament);
        setTransferEndInput(tournament);
        setScheme(tournament);
        setLinkToReglamentInput(tournament);
        return this;
    }
}
