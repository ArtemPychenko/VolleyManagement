package com.softserveinc.ita.volleymanagementtests.criterias;

import org.testng.Assert;

import com.softserveinc.ita.volleymanagementtests.domain.models.Player;
import com.softserveinc.ita.volleymanagementtests.domain.models.Team;
import com.softserveinc.ita.volleymanagementtests.domain.models.Tournament;
import com.softserveinc.ita.volleymanagementtests.domain
                                                .repositories.PlayerRepository;
import com.softserveinc.ita.volleymanagementtests.domain.repositories
                                                .TournamentRepository;
import com.softserveinc.ita.volleymanagementtests.pages.ConfirmDialogPage;
import com.softserveinc.ita.volleymanagementtests.pages.Page;
import com.softserveinc.ita.volleymanagementtests.pages.PlayerListPage;
import com.softserveinc.ita.volleymanagementtests.pages.SearchString;
import com.softserveinc.ita.volleymanagementtests.pages.TeamListPage;
import com.softserveinc.ita.volleymanagementtests.pages.TournamentListPage;
import com.softserveinc.ita.volleymanagementtests.domain
                                                .repositories.TeamRepository;
import com.softserveinc.ita.volleymanagementtests.tools.controls
                                                .interfaces.IButton;
import com.softserveinc.ita.volleymanagementtests.tools.controls
                                                .interfaces.IDropdown;
import com.softserveinc.ita.volleymanagementtests.tools.controls
                                                .interfaces.ILabel;
import com.softserveinc.ita.volleymanagementtests.tools.controls
                                                .interfaces.ITextInput;
import com.softserveinc.ita.volleymanagementtests.tools.controls
                                                .interfaces.IValidationLabel;

/**
 * @author Dp-076 ATQC
 *         This class realizes soft assert.
 */
public final class Specification {

    /**
     * Is test at summary passed?
     * This variable contain answer.
     */
    private boolean summaryResult;

    /**
     * This variable contain failed tests report.
     */
    private StringBuilder summaryDescription;

    /**
     * Class constructor.
     */
    private Specification() {
        this.summaryResult = true;
        this.summaryDescription = new StringBuilder();
    }

    /**
     * Static method for create new Specification.
     *
     * @return new Specification.
     */
    public static Specification get() {
        return new Specification();
    }

    /**
     * Getter for summaryResult.
     *
     * @return boolean answer. Is test at summary passed?
     */
    public boolean getPassed() {
        return summaryResult;
    }

    /**
     * Getter for summaryDescription.
     *
     * @return failed tests report.
     */
    public String getDescription() {
        return summaryDescription.toString();
    }

    /**
     * This method add new log in failed tests report.
     *
     * @param pass - is current test passed?
     * @param errorText - text for error log.
     */
    public void add(final boolean pass, final String errorText) {
        summaryResult = summaryResult && pass;
        if (!pass) {
            summaryDescription.append(errorText + "\n");
        }
    }

    /**
     * Assert method for finish check test results.
     */
    public void check() {
        Assert.assertTrue(summaryResult, summaryDescription.toString());
    }

    /**
     * Fluent API method for resume test with new test criteria.
     *
     * @param label - test criteria input object.
     * @return new LabelCriteria for input object.
     */
    public LabelCriteria For(final ILabel label) {
        return LabelCriteria.get(label, this);
    }
    /**
     * Fluent API method for resume test with new test criteria.
     *
     * @param button - test criteria input object.
     * @return new ButtonCriteria for input object.
     */
    public ButtonCriteria For(final IButton button) {
        return ButtonCriteria.get(button, this);
    }

    /**
     * Fluent API method for resume test with new test criteria.
     *
     * @param validationLabel - test criteria input object.
     * @return new ValidationLabelCriteria for input object.
     */
    public ValidationLabelCriteria For(final IValidationLabel validationLabel) {
        return ValidationLabelCriteria.get(validationLabel, this);
    }

    /**
     * Fluent API method for resume test with new test criteria.
     *
     * @param textInput - test criteria input object.
     * @return new TextInputCriteria for input object.
     */
    public TextInputCriteria For(final ITextInput textInput) {
        return TextInputCriteria.get(textInput, this);
    }

    /**
     * Fluent API method for resume test with new test criteria.
     *
     * @param dropdown - test criteria input object.
     * @return new DropdownCriteria for input object.
     */
    public DropdownCriteria For(final IDropdown dropdown) {
        return DropdownCriteria.get(dropdown, this);
    }
    /**
     * Fluent API method for resume test with new test criteria.
     * @param repository - test criteria input object.
     * @return new PlayerRepositoryCriteria for input object.
     */
    public PlayerRepositoryCriteria For(final PlayerRepository repository) {
        return PlayerRepositoryCriteria.get(repository, this);
    }

    /**
     * Fluent API method for resume test with new test criteria.
     * @param repository - test criteria input object.
     * @return new TeamRepositoryCriteria for input object.
     */
    public TeamRepositoryCriteria For(final TeamRepository repository) {
        return TeamRepositoryCriteria.get(repository, this);
    }
    /**
     * Fluent API method for resume test with new test criteria.
     * @param repository - test criteria input object.
     * @return new TournamentRepositoryCriteria for input object.
     */
    public TournamentRepositoryCriteria For(
            final TournamentRepository repository) {
        return TournamentRepositoryCriteria.get(repository, this);
    }
    /**
     * Fluent API method for resume test with new test criteria.
     *
     * @param player - test criteria input object.
     * @return new PlayerCriteria for input object.
     */
    public PlayerCriteria For(final Player player) {
        return PlayerCriteria.get(player, this);
    }

    /**
     * Fluent API method for resume test with new test criteria.
     *
     * @param team - test criteria input object.
     * @return new TeamCriteria for input object.
     */
    public TeamCriteria For(final Team team) {
        return TeamCriteria.get(team, this);
    }
    /**
     * Fluent API method for resume test with new test criteria.
     *
     * @param tournament - test criteria input object.
     * @return new TournamentCriteria for input object.
     */
    public TournamentCriteria For(final Tournament tournament) {
        return TournamentCriteria.get(tournament, this);
    }

    /**
     * Fluent API method for resume test with new test criteria.
     *
     * @param searchString - test criteria input object.
     * @return new PlayerSearchCriteria for input object.
     */
    public PlayerSearchCriteria ForPlayers(final SearchString searchString) {
        return PlayerSearchCriteria.get(searchString, this);
    }

    /**
     * Fluent API method for resume test with new test criteria.
     *
     * @param searchString - test criteria input object.
     * @return new TeamSearchCriteria for input object.
     */
    public TeamSearchCriteria ForTeams(final SearchString searchString) {
        return TeamSearchCriteria.get(searchString, this);
    }
    /**
     * Fluent API method for resume test with new test criteria.
     *
     * @param searchString - test criteria input object.
     * @return new TournamentSearchCriteria for input object.
     */
    public TournamentSearchCriteria ForTournaments(
            final SearchString searchString) {
        return TournamentSearchCriteria.get(searchString, this);
    }

    /**
     * Fluent API method for resume test with new test criteria.
     *
     * @param playerPage - test criteria input object.
     * @return new PlayerListCriteria for input object.
     */
    public PlayerListCriteria For(final PlayerListPage playerPage) {
        return PlayerListCriteria.get(playerPage, this);
    }

    /**
     * Fluent API method for resume test with new test criteria.
     *
     * @param teamPage - test criteria input object.
     * @return new TeamListCriteria for input object.
     */
    public TeamListCriteria For(final TeamListPage teamPage) {
        return TeamListCriteria.get(teamPage, this);
    }

    /**
     * Fluent API method for resume test with new test criteria.
     *
     * @param tournamentPage - test criteria input object.
     * @return new TournamentListCriteria for input object.
     */
    public TournamentListCriteria For(final TournamentListPage tournamentPage) {
        return TournamentListCriteria.get(tournamentPage, this);
    }

    /**
     * Fluent API method for resume test with new test criteria.
     *
     * @param confirmDialogPageCriteria - test criteria input object.
     * @return new ConfirmDialogPageCriteria for input object.
     */
    public ConfirmDialogPageCriteria For(
            final ConfirmDialogPage confirmDialogPageCriteria) {
        return ConfirmDialogPageCriteria.get(confirmDialogPageCriteria, this);
    }

    /**
     * Fluent API method for resume test with new test criteria.
     *
     * @param page - test criteria input object.
     * @return new PageCriteria for input object.
     */
    public PageCriteria For(final Page page) {
        return PageCriteria.get(page, this);
    }
}
