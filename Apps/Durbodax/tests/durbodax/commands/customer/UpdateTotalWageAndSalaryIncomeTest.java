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
public class UpdateTotalWageAndSalaryIncomeTest {

    public UpdateTotalWageAndSalaryIncomeTest() {
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
     * Test of execute method, of class UpdateTotalWageAndSalaryIncome.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] input = new String[] { "command","3456" };
        UpdateTotalWageAndSalaryIncome instance = new UpdateTotalWageAndSalaryIncome();
        instance.execute(input);

        assertEquals(3456, CustomerData.CUSTOMER.getCustomer().getWageAndSalaryIncome());
        
    }

}