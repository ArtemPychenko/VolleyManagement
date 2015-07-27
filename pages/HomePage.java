package com.softserveinc.ita.volleymanagementtests.pages;

public class HomePage extends Page {
	private NavigationPage navigationMenu;
	
	public HomePage() {
		verifyUrl("http://localhost:23801/WebApi/Home", "It's not home page");
		navigationMenu = new NavigationPage();
	}
	public NavigationPage getNavigationPage() {
		return this.navigationMenu;
	}

//its start page	
}
