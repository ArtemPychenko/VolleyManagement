package com.softserveinc.ita.volleymanagementtests.pages;

public class NavigationPage extends Page {
	private NavigationPageUIMap controls;
	public NavigationPage(){
		controls = new NavigationPageUIMap();
	}
	public PlayerListPage getPlayersPage(){
		controls.getPlayersLink().click();
		return new PlayerListPage();
	}
	public TeamListPage getTeamListPage() {
	    controls.getTeamsLink().click();
	    return new TeamListPage();
	}
	public TournamentListPage getTournamentListPage() {
	    controls.getTournamentsLink().click();
	    return new TournamentListPage();
	}
}
