package com.softserveinc.ita.volleymanagementtests.pages;

import com.softserveinc.ita.volleymanagementtests.domain.models.Player;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IButton;

/**
 * @author Dp-076 ATQC
 * This class allows to control edit player page ui map.
 */
public class EditPlayerPage extends Page {
    /**
     * @param controls encapsulates map of page.
     */
    private NewPlayerPageUIMap controls;
    /**
     * @param dataPage is an instance of player data page.
     */
    private PlayerDataPage dataPage;
    /**
     * @param navigationMenu, navigation panel gives the navigation to walk by
     * the site.
     */
    private NavigationPage navigationMenu;
    /**
     * constructor is checking the correctness of the page address,
     * creating the instances of ui map, navigation panel and player data pages.
     */
    public EditPlayerPage() {
        verifyUrlStartAndContain("http://localhost:23801/Players", "edit",
                "This is not the edit player page");
        controls = new NewPlayerPageUIMap();
        dataPage = new PlayerDataPage();
        navigationMenu = new NavigationPage();
    }
    /**
     * @return navigation panel
     */
    public final NavigationPage getNavigation() {
        return this.navigationMenu;
    }
    /**
     * @return data page
     */
    public final PlayerDataPage getData(){
        return this.dataPage;
    }
    /**
     * method is pressing to the cancel button.
     * @return NewPlayerPage in the current state.
     */
    public final PlayerDetailPage pressCancel() {
        controls.getCancelButton().click();
        return new PlayerDetailPage();
    }
    /**
     * @return cancel button
     */
    public final IButton getCancelButton(){
        return controls.getCancelButton();
    }
    /**
     * method is pressing to the save button.
     * @return NewPlayerPage in the current state.
     */
    public final PlayerDetailPage pressSave() {
        controls.getSaveButton().click();
        return new PlayerDetailPage();
    }
    /**
     * @return save button
     */
    public final IButton getSaveButton() {
    return controls.getSaveButton();
    }
    /**
     * method is pressing to the save button.
     * @return NewPlayerPage in the current state.
     */
    public final EditPlayerPage unsuccessfulPressSave() {
        controls.getSaveButton().click();
        return new EditPlayerPage();
    }
    /**
     * @return player
     */
    public Player getPlayer() {
        Player player = new Player();
        player.setFirstName(this.getData().getFirstNameInput().getText());
        player.setLastName(this.getData().getSecondNameInput().getText());
        if(! this.getData().getBirthYearInput().getText().equals(new String())){
            player.setBirthYear(this.getData().getBirthYearInput().getText());
        }
        if(! this.getData().getHeightInput().getText().equals(new String())) {
            player.setHeight(this.getData().getHeightInput().getText());
        }
        if (! this.getData().getWeightInput().getText().equals(new String())){
            player.setWeight(this.getData().getWeightInput().getText());
        }
        return player;
    }
}
