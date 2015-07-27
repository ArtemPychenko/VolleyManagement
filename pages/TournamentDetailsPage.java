package com.softserveinc.ita.volleymanagementtests.pages;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IButton;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ILabel;

/**
 * Describe tournament detail page.
 * @author DP-076 ATQC
 *
 */
public class TournamentDetailsPage {
    /** @param controls encapsulates map of web site. */
    private TournamentDetailPageUIMap control;
    /** @param navigationPage encapsulates map of navigation element
     *            in page. */
    private NavigationPage navigationPage;

    /** Constructor. */
    public TournamentDetailsPage() {
        control = new TournamentDetailPageUIMap();
        navigationPage = new NavigationPage();
    }
    /**
     * @return navigation page.
     */
    public final NavigationPage getNavigation() {
        return this.navigationPage;
    }
    /**
     * @return page title label.
     */
    public final ILabel getPageTitleLabel() {
        return this.control.getpageTitleLabel();
    }
    /**
     * @return tournament name label.
     */
    public final ILabel getTournamentNameLabel() {
        return this.control.getTournamentNameLabel();
    }
    /**
     * @return text in name field.
     */
    public final ILabel getTournamentNameInput() {
        return this.control.getTournamentNameInput();
    }
    /**
     * @return DescriptionLabel.
     */
    public final ILabel getDescriptionLabel() {
        return this.control.getDescriptionLabel();
    }
    /**
     * @return text in description field.
     */
    public final ILabel getDescriptionInput() {
        return this.control.getDescriptionInput();
    }
    /**
     * @return season label.
     */
    public final ILabel getSeasonLabel() {
        return this.control.getSeasonLabel();
    }
    /**
     * @return text in season field.
     */
    public final ILabel getSeasonInput() {
        return this.control.getSeasonInput();
    }
    /**
     * 
     * @return
     */
    public final String getSeasonYear() {
        String[] years = control.getSeasonInput().getText().split("/");
        return years[0];
    }
    /**
     * @return StartAndEndOfRegistrationLabel.
     */
    public final ILabel getStartAndEndOfRegistrationLabel() {
        return this.control.getStartAndEndOfRegistrationLabel();
    }
    /**
     * @return text in StartAndEndOfRegistration field.
     */
    public final ILabel getStartAndEndOfRegistrationInput() {
        return this.control.getStartAndEndOfRegistrationInput();
    }
    /**
     * @return StartAndEndOfTournamentLabel.
     */
    public final ILabel getStartAndEndOfTournamentLabel() {
        return this.control.getStartAndEndOfTournamentLabel();
    }
    /**
     * @return text in StartAndEndOfTournament field.
     */
    public final ILabel getStartAndEndOfTournamentInput() {
        return this.control.getStartAndEndOfTournamentInput();
    }
    /**
     * @return StartAndEndOfTransferLabel.
     */
    public final ILabel getStartAndEndOfTransferLabel() {
        return this.control.getStartAndEndOfTransferLabel();
    }
    /**
     * @return text in StartAndEndOfTransfer field.
     */
    public final ILabel getStartAndEndOfTransferInput() {
        return this.control.getStartAndEndOfTransferInput();
    }
    /**
     * @return SchemeLabel.
     */
    public final ILabel getSchemeLabel() {
        return this.control.getSchemeLabel();
    }
    /**
     * @return text in SchemeLabel field.
     */
    public final ILabel getSchemeInput() {
        return this.control.getSchemeInput();
    }
    /**
     * @return edit button.
     */
    public final IButton getEditButton() {
        return this.control.getEditButton();
    }
    /**
     * This method click on edit button.
     * @return edit Tournament Page
     */
    public final EditTournamentPage pressEditButton() {
        this.control.getEditButton().click();
        return new EditTournamentPage();
    }
    /**
     * @return return button.
     */
    public final IButton getReturnButton() {
        return this.control.getReturnButton();
    }
    /**
     * This method click on return button.
     * @return Tournament list Page
     */
    public final TournamentListPage pressReturnButton() {
        this.control.getReturnButton().click();
        return new TournamentListPage();
    }
    /**
     * @return delete button.
     */
    public final IButton getDeleteButton() {
        return this.control.getDeleteButton();
    }
    /**
     * This method click on delete button.
     * @return Confirm Dialog Page
     */
    public final ConfirmDialogPage pressDeleteButton() {
        this.control.getDeleteButton().click();
        return new ConfirmDialogPage();
    }

}
