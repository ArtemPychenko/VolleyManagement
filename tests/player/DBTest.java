package com.softserveinc.ita.volleymanagementtests.tests.player;

import java.text.ParseException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.softserveinc.ita.volleymanagementtests.domain.repositories.DALTools;
import com.softserveinc.ita.volleymanagementtests.tests.TestsConstants;

/**
 * NOT FOR TEST SUIT.
 * This is Test-case class for DB functionality.
 * "http://localhost:23801/WebApi/Home" test.
 *
 * @author Artem Pozdeev
 */
public class DBTest {

    /**
     * This method initialize global variables.
     */
    @BeforeSuite
    public final void setUpSuit() {
        DALTools.startConnection(TestsConstants.CONNECT_DB_STRING);
    }

    @BeforeClass
    public final void setUp() {
    }

    /**
     * Test for DB.
     * @throws ParseException 
     */
    @Test
    public final void playerListPaging() throws ParseException {
    }

    /**
     * Method for roll back Transaction.
     */
    @AfterClass
    public final void tearDown() {
    }

    @AfterSuite
    public final void tearDownSuite() {
        DALTools.stopTransaction();
        DALTools.closeConnection();
    }
}
