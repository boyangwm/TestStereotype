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
public class UpdateTotalPersonalIncomeTest {

    public UpdateTotalPersonalIncomeTest() {
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
     * Test of execute method, of class UpdateTotalPersonalIncome.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] input = new String[] { "command", "9876" };
        UpdateTotalPersonalIncome instance = new UpdateTotalPersonalIncome();
        Object expResult = null;
        Object result = instance.execute(input);

        assertEquals(9876, CustomerData.CUSTOMER.getCustomer().getTotalPersonalIncome());
        
    }

}