package com.softserveinc.ita.volleymanagementtests.pages;

import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IButton;

/**
 * @author Dp-076 ATQC
 * This class allows to control edit team page ui map.
 */
public class EditTeamPage extends Page {
    /**
     * @param controls encapsulates map of page.
     */
   private EditTeamPageUIMap controls;
   /**
    *An instance of team data page.
    */
   private TeamDataPage dataPage;
    /**
     * @param navigationMenu, navigation panel gives the navigation to walk by
     * the site.
     */
   private NavigationPage navigationMenu;
    /**
     * constructor is checking the correctness of the page address,
     * creating the instances of ui map, navigation panel and team data pages.
     */
    public EditTeamPage() {
        super();
        verifyUrlStartAndContain("http://localhost:23801/Teams", "edit",
                "This is not the edit player page");
        dataPage = new TeamDataPage();
        navigationMenu = new NavigationPage();
        controls = new EditTeamPageUIMap();
    }
    /**
     * @return navigation panel.
     */
    public final NavigationPage getNavigation() {
        return this.navigationMenu;
    }
    /**
     * @return team data page.
     */
    public final TeamDataPage getData() {
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
     * @return TeamsListPage in the current state.
     */
    public final TeamDetailPage pressCancel() {
        controls.getCancelButton().click();
        return new TeamDetailPage();
    }
    /**
     * @return save button.
     */
    public final IButton getSaveButton() {
        return controls.getSaveButton();
    }
    /**
     * method is pressing to the save button.
     * @return TeamListPage in the current state.
     */
    public final TeamDetailPage pressSave() {
        controls.getSaveButton().click();
        return new TeamDetailPage();
    }
    /**
     * method is pressing to the save button.
     * @return EditTeamPage in the current state.
     */
    public final EditTeamPage unsuccessfulPressSave() {
        controls.getSaveButton().click();
        return new EditTeamPage();
    }
}
