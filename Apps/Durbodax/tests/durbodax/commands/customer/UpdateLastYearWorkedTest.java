package durbodax.commands.customer;

import durbodax.customers.CustomerData;
import durbodax.customers.Enums.LastYearWorked;
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
public class UpdateLastYearWorkedTest {

    public UpdateLastYearWorkedTest() {
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
     * Test of execute method, of class UpdateLastYearWorked.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] input = new String[] { "command", "20" };
        UpdateLastYearWorked instance = new UpdateLastYearWorked();
        instance.execute(input);
        
        assertEquals(20, CustomerData.CUSTOMER.getCustomer().getLastYearWorked());
        assertEquals(LastYearWorked.PREVIOUS_YEAR, CustomerData.CUSTOMER.getCustomer().getLastYearWorkedEnum());
        
    }

}