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
 * @author Kirk Seddon
 */
public class UpdateFarmIncomeTest {

    public UpdateFarmIncomeTest() {
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
     * Test of execute method, of class UpdateFarmIncome.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] input = new String[] { "command", "234567" };
        UpdateFarmIncome instance = new UpdateFarmIncome();
        instance.execute(input);

        assertEquals(234567, CustomerData.CUSTOMER.getCustomer().getFarmIncome());
        
    }

}