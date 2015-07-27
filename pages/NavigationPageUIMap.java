package com.softserveinc.ita.volleymanagementtests.pages;

import com.softserveinc.ita.volleymanagementtests.tools.controls.Link;
import com.softserveinc.ita.volleymanagementtests.tools.controls.interfaces.ILink;

/**
 * @author Dp-076 ATQC This class describes UI elements on the left of the all
 *         pages.
 */
public class NavigationPageUIMap {
    /**
     * Tournaments link of the players list page.
     */
    private ILink tournamentsLink;
    /**
     * Users link of the players list page.
     */
    private ILink usersLink;
    /**
     * Teams link of the players list page.
     */
    private ILink teamsLink;
    /**
     * Players link of the players list page.
     */
    private ILink playersLink;
    /**
     * About link of the players list page.
     */
    private ILink aboutLink;

    public NavigationPageUIMap() {
    }

    /**
     * @return Link "Tournaments".
     */
    public final ILink getTournamentsLink() {
            this.tournamentsLink = Link.getByLinkText("Tournaments");
        return tournamentsLink;
    }

    /**
     * @return Link "Users".
     */
    public final ILink getUsersLink() {
        if (this.usersLink == null) {
            this.usersLink = Link.getByLinkText("Users");
        }
        return usersLink;
    }

    /**
     * @return Link "Teams".
     */
    public final ILink getTeamsLink() {
        if (this.teamsLink == null) {
            this.teamsLink = Link.getByLinkText("Teams");
        }
        return teamsLink;
    }

    /**
     * @return Link "Players".
     */
    public final ILink getPlayersLink() {
        if (this.playersLink == null) {
            this.playersLink = Link.getByLinkText("Players");
        }
        return playersLink;
    }

    /**
     * @return Link "About".
     */
    public final ILink getAboutLink() {
        if (this.aboutLink == null) {
            this.aboutLink = Link.getByLinkText("About");
        }
        return aboutLink;
    }
    /**
     * @return Label "Players List".
     */

}
