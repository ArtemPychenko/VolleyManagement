package com.softserveinc.ita.volleymanagementtests.pages;

import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IButton;

/**
 * @author Dp-076 ATQC
 * This class allows to control edit tournament page ui map.
 */
public class EditTournamentPage extends Page {
    /**
     * @param controls encapsulates map of page.
     */
    private EditTournamentPageUIMap controls;
    /**
     *An instance of tournament data page.
     */
    private TournamentDataPage dataPage;
    /**
     * @param navigationMenu, navigation panel gives the navigation to walk by
     * the site.
     */
    private NavigationPage navigationMenu;
    /**
     * constructor is checking the correctness of the page address,
     * creating the instances of ui map, navigation panel and team data pages.
     */
    public EditTournamentPage() {
        verifyUrlStartAndContain("http://localhost:23801/Tournaments", "edit",
                "This is not the edit tournament page");
        controls = new EditTournamentPageUIMap();
        dataPage = new TournamentDataPage();
        navigationMenu = new NavigationPage();
    }
    /**
     * @return navigation panel.
     */
    public final NavigationPage getNavigation() {
        return this.navigationMenu;
    }
    /**
     * @return tournament data page.
     */
    public final TournamentDataPage getData() {
        return this.dataPage;
    }
    /**
     * method is pressing to the cancel button.
     * @return TournamentDetailPage in the current state.
     */
    public final TournamentDetailsPage pressCancel() {
        controls.getCancelButton().click();
        return new TournamentDetailsPage();
    }
    /**
     * @return cancel button.
     */
    public final IButton getCancelButton(){
        return controls.getCancelButton();
    }
    /**
     * method is pressing to the save button.
     * @return TournamentDetailPage in the current state.
     */
    public final TournamentDetailsPage pressSave() {
        controls.getSaveButton().click();
        return new TournamentDetailsPage();
    }
    /**
     * @return save button.
     */
    public final IButton getSaveButton() {
        return controls.getSaveButton();
    }
    /**
     * void method for clicking at save button.
     */
    public final void pressSaveWithoutReturnPage() {
        controls.getSaveButton().click();
    }
    /**
     * method is pressing to the save button.
     * @return EditTournamentPage in the current state.
     */
    public final EditTournamentPage unsuccessfulPressSave() {
        controls.getSaveButton().click();
        return new EditTournamentPage();
    }
}
