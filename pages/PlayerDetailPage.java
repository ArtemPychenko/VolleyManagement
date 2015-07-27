package com.softserveinc.ita.volleymanagementtests.pages;

import com.softserveinc.ita.volleymanagementtests.domain.models.Player;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.IButton;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ILabel;

public class PlayerDetailPage extends Page {
    private PlayerDetailPageUIMap controls;
    private NavigationPage navigationPage;

    public PlayerDetailPage() {
        verifyUrlStartWith("http://localhost:23801/Players",
                "It's not detail player page");
        controls = new PlayerDetailPageUIMap();
        navigationPage = new NavigationPage();
    }

    public NavigationPage getNavigation() {
        return this.navigationPage;
    }
    
    public final ILabel getTitleLabel() {
        return controls.getPageTitleLabel();
    }

    public final ILabel getFirstNameLabel() {
        return controls.getFirstNameLabel();
    }
    public final ILabel getFirstNameInputLabel() {
        return controls.getFirstNameInputLabel();
    }

    public final ILabel getSecondNameLabel() {
        return controls.getLastNameLabel();
    }
    public final ILabel getSecondNameInputLabel() {
        return controls.getLastNameInputLabel();
    }

    public final ILabel getBirthYearLabel() {
        return controls.getBirthYearLabel();
    }
    public final ILabel getBirthYearInputLabel() {
        return controls.getBirthYearInputLabel();
    }
    
    public final ILabel getHeightLabel() {
        return controls.getHeightLabel();
    }
    public final ILabel getHeightInputLabel() {
        return controls.getHeightInputLabel();
    }
    
    public final ILabel getWeightLabel() {
        return controls.getWeightLabel();
    }
    public final ILabel getWeightInputLabel() {
        return controls.getWeightInputLabel();
    }

    public EditPlayerPage pressEditButton() {
        controls.getEditButton().click();
        return new EditPlayerPage();
    }

    public PlayerListPage pressReturnButton() {
        controls.getReturnButton().click();
        return new PlayerListPage();
    }

    public ConfirmDialogPage pressDeleteButton() {
        controls.getDeleteButton().click();
        return new ConfirmDialogPage();
    }

    public IButton getEditButton() {
        return controls.getEditButton();
    }

    public IButton getReturnButton() {
        return controls.getReturnButton();
    }

    public IButton getDeleteButton() {
        return controls.getDeleteButton();
    }

    public Player getPlayer() {
        Player player = new Player();
        player.setFirstName(this.getFirstNameInputLabel().getText());
        player.setLastName(this.getSecondNameInputLabel().getText());
        if(! this.getBirthYearInputLabel().getText().equals(new String())){
        	player.setBirthYear(this.getBirthYearInputLabel().getText());
        }
        if(! this.getHeightInputLabel().getText().equals(new String())) {
        	player.setHeight(this.getHeightInputLabel().getText());
        }
        if (! this.getWeightInputLabel().getText().equals(new String())){
        	player.setWeight(this.getWeightInputLabel().getText());
        }
        
        return player;
    }

}
