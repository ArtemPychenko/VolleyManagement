package com.softserveinc.ita.volleymanagementtests.tests;

/**
 * This class contain utility constants for tests.
 * @author Dp-076 ATQC
 *
 */
public final class TestsConstants {

    /**
     * Not called constructor for utility class.
     */
    private TestsConstants() {
    }

    /**
     * Project Home page.
     */
    public static final String HOME_PAGE = "http://localhost:23801/WebApi/Home";
    public static final String DB_NAME = "VolleyManagement";
    public static final String DB_SCHEMA = "dbo";
            public static final String CONNECT_DB_STRING = "jdbc:sqlserver://"
            + "localhost;IntegratedSecurity=True;"
            + "databaseName=" + DB_NAME + ";";
    public static final int ITEMS_PER_PAGE = 10;
    
    //Player
    public static final String ADD_NEW_PLAYER_LABEL = "Регистрация игрока";
    public static final String EDIT_PLAYER_PAGE_LABEL = "Редактирование информации";
    public static final String PLAYER_NAME_LABEL = "Имя:";
    public static final String PLAYER_SECOND_NAME_LABEL = "Фамилия:";
    public static final String PLAYER_BIRTH_YEAR_LABEL = "Год рождения:";
    public static final String PLAYER_HEIGHT_LABEL = "Рост, см:";
    public static final String PLAYER_WEIGHT_LABEL = "Вес, кг:";
    public static final String PLAYER_INFORMATION_LABEL = "Информация об игроке";
    public static final String PLAYER_TABLE = "Players";
    public static final String TOURNAMENT_TABLE = "Tournaments";
    public static final String VALIDATION_ERROR_TEXT_WRONG_LENGTH_HEIGHT
    = "Рост должен быть в пределах 10 - 250";
    public static final String VALIDATION_ERROR_TEXT_WRONG_LENGTH_WEIGHT
    = "Вес должен быть в предеах 10 - 500";
    public static final String VALIDATION_ERROR_TEXT_WRONG_YEAR 
    = "Год рождения должен быть в пределах 1900 - 2100";
    public static final String VASILIY = "Vasiliy";
    public static final String SOLOVIOV = "Soloviov";
    public static final String DELETE_PLAYER_LABEL
    = "Вы действительно хотите удалить профиль этого игрока?";
    
    //Team
    public static final String TEAM_NAME_LABEL = "Имя:";
    public static final String TEAM_CAPTAIN_LABEL = "Капитан:";
    public static final String TEAM_COACH_LABEL = "Тренер:";
    public static final String TEAM_ACHIEVEMENTS_LABEL = "Достижения:";
    public static final String TEAM_INFORMATION_LABEL = "Информация о команде";
    public static final String TEAM_TABLE = "Teams";
    public static final String CREATE_NEW_TEAM_TITLE_LABEL = "Регистрация команды";
    public static final String EDIT_TEAM_TITLE_LABEL = "Редактирование информации";
    public static final String TEAM_LIST_PAGE_LABEL = "Зарегистированные команды";
    public static final String DELETE_TEAM_LABEL
    = "Вы действительно хотите удалить профиль команды?";
    public static final String VALIDATION_ERROR_TEXT_WRONG__ACHIEVEMENTS_SIZE
    = "Поле не может содержать более 4000 символов";
    
    
    //Tournament
    public static final String CREATE_NEW_TOURNAMENT_TITLE_LABEL = "Создать турнир";
    public static final String EDIT_TOURNAMENT_TITLE_LABEL = "Редактировать турнир";
    public static final String DELETE_TOURNAMENT_LABEL
    = "Вы действительно хотите удалить турнир?";
    public static final String TOURNAMENT_LIST_PAGE_LABEL = "Список турниров";
    public static final String TOURNAMENT_NAME_LABEL = "Название турнира:";
    public static final String TOURNAMENT_DESCRIPTION_LABEL = "Описание:";
    public static final String TOURNAMENT_SEASON_LABEL = "Сезон:";
    public static final String DETAIL_TOURNAMENT_PAGE_TITLE_LABEL = "Домашняя страница турнира";
    public static final String TOURNAMENT_TERMS_APPLYING_LABEL = "Начало / конец регистрации:";
    public static final String TOURNAMENT_START_END_LABEL = "Начало / конец турнира:";
    public static final String TOURNAMENT_START_END_TRANSFER_PERIOD_LABEL = "Начало / конец трансферного периода:";
    public static final String TOURNAMENT_SCHEME_LABEL = "Схема:";
    public static final String TOURNAMENT_REGULATIONS_LINK_LABEL = "Ссылка на регламент:";
    public static final String VALIDATION_ERROR_TEXT_WRONG_TYPE_DATE
    = "Поле должно содержать дату в формате дд.мм.гггг";
    
    //Common
    public static final String ADD_BUTTON_LABEL = "Добавить";
    public static final String SAVE_BUTTON_LABEL = "Сохранить";
    public static final String CANCEL_BUTTON_LABEL = "Отмена";
    public static final String EDIT_BUTTON = "Редактировать";
    public static final String RETURN_BUTTON = "Назад";
    public static final String DELETE_BUTTON = "Удалить";
    public static final String SEARCH_TEXT_INPUT = "Ищу...";
    
    public static final String DELETE_BUTTON_YES_LABEL
    = "Удалить";
    public static final String DELETE_BUTTON_NO_LABEL
    = "Отмена";
    public static final String VALIDATION_ERROR_TEXT_WRONG_TYPE_NAME 
    = "Поле должно содержать только буквы";
    public static final String VALIDATION_ERROR_TEXT_WRONG_TYPE_NUMBER
    = "Поле должно содержать только цифры";
    public static final String VALIDATION_ERROR_TEXT_EMPTY_NAME_FIELD 
    = "Поле не может быть пустым";
    public static final String VALIDATION_ERROR_TEXT_TOO_BIG_TEAM_NAME 
    = "Поле не может содержать более 30 символов";
    public static final String VALIDATION_ERROR_TEXT_WRONG__DESCRIPTION_SIZE
    = "Поле не может содержать более 300 символов";
    public static final String VALIDATION_ERROR_TEXT_TOO_BIG_NAME
    = "Поле не может содержать более 60 символов";
    public static final String VALIDATION_ERROR_TEXT_TOO_BIG_LINK
    = "Поле не может содержать более 255 символов";
}
