package com.softserveinc.ita.volleymanagementtests.pages;

import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IButton;
/**
 * @author Dp-076 ATQC
 * This class allows to control new tournament page ui map.
 */
public class NewTournamentPage extends Page {
    /**
     * @param controls encapsulates map of page.
     */
    private NewTournamentPageUIMap controls;
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
    public NewTournamentPage() {
        super();
        verifyUrlStartWith("http://localhost:23801/Tournaments/new",
                "This is not the new tornament page");
        dataPage = new TournamentDataPage();
        navigationMenu = new NavigationPage();
        controls = new NewTournamentPageUIMap();
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
     * @return cancel button.
     */
    public final IButton getCancelButton() {
        return controls.getCancelButton();
    }
    /**
     * method is pressing to the cancel button.
     * @return TournamentsListPage in the current state.
     */
    public final TournamentListPage pressCancel() {
        controls.getCancelButton().click();
        return new TournamentListPage();
    }
    /**
     * @return save button.
     */
    public final IButton getSaveButton() {
        return controls.getSaveButton();
    }
    /**
     * method is pressing to the save button.
     * @return TournamentsListPage in the current state.
     */
    public final TournamentListPage pressSave() {
        controls.getSaveButton().click();
        return new TournamentListPage();
    }
    /**
     * method is pressing to the save button.
     */
    public final void pressSaveWithoutReturnPage() {
        controls.getSaveButton().click();
    }
    /**
     * method is pressing to the save button.
     * @return NewTournamentPage in the current state.
     */
    public final NewTournamentPage unsuccessfulPressSave() {
        controls.getSaveButton().click();
        return new NewTournamentPage();
    }
}
