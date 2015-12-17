package durbodax.commands.customer;

import durbodax.customers.CustomerData;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kirk
 */
public class UpdateOccupationIncomeScoreTest {

    public UpdateOccupationIncomeScoreTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of execute method, of class UpdateOccupationIncomeScore.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] input = new String[] { "command", "4657" };
        UpdateOccupationIncomeScore instance = new UpdateOccupationIncomeScore();
        instance.execute(input);
        
        assertEquals(4657, CustomerData.CUSTOMER.getCustomer().getOccupationIncomeScore());
        
    }

}