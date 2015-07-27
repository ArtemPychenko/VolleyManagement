package com.softserveinc.ita.volleymanagementtests.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.softserveinc.ita.volleymanagementtests.domain.repositories.DALTools;
import com.softserveinc.ita.volleymanagementtests.tools.WebDriverUtils;

/**
 * This class contain BeforeSuite and AfterSuite annotation.
 * 
 * @author Dp-076ATQC
 *
 */
public class TestSuitWrapper {
    /**
     * This method initialize global variables.
     */
    @BeforeSuite
    public final void setUpSuit() {
        DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
    }

    /**
     * Method for shutdown WebDriver thread and close DB connection.
     */
    @AfterSuite
    public final void tearDownSuite() {
        DALTools.closeConnection();
        WebDriverUtils.stop();
    }
}
