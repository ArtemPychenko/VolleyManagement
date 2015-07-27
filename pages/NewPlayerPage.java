package com.softserveinc.ita.volleymanagementtests.pages;

import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IButton;
/**
 * @author Dp-076 ATQC
 * This class allows to control new player page ui map.
 */
public class NewPlayerPage extends Page {
    /**
     * @param controls encapsulates map of page.
     */
   private NewPlayerPageUIMap controls;
   /**
    *An instance of player data page.
    */
   private PlayerDataPage dataPage;
   /**
    * @param navigationMenu, navigation panel gives the navigation to walk by
    * the site.
    */
   private NavigationPage navigationMenu;
   /**
    * constructor is checking the correctness of the page address,
    * creating the instances of ui map, navigation panel and team data pages.
    */
    public NewPlayerPage() {
        super();
        verifyUrlStartWith("http://localhost:23801/Players/new",
                "This is not the new player page");
        dataPage = new PlayerDataPage();
        navigationMenu = new NavigationPage();
        controls = new NewPlayerPageUIMap();
    }
    /**
     * @return navigation panel.
     */
    public final NavigationPage getNavigation() {
        return this.navigationMenu;
    }
    /**
     * @return data player page.
     */
    public final PlayerDataPage getData() {
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
     * @return PlayersListPage in the current state.
     */
    public final PlayerListPage pressCancel() {
        controls.getCancelButton().click();
        return new PlayerListPage();
    }
    /**
     * @return save button.
     */
    public final IButton getSaveButton() {
        return controls.getSaveButton();
    }
    /**
     * method is pressing to the save button.
     * @return PlayersListPage in the current state.
     */
    public final PlayerListPage pressSave() {
        controls.getSaveButton().click();
        return new PlayerListPage();
    }
    /**
     * method is pressing to the save button.
     * @return NewPlayerPage in the current state.
     */
    public final NewPlayerPage unsuccessfulPressSave() {
        controls.getSaveButton().click();
        return new NewPlayerPage();
    }
}
