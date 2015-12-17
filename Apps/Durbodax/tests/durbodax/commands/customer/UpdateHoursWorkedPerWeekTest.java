package durbodax.commands.customer;

import durbodax.customers.CustomerData;
import durbodax.customers.Enums.WorkHours;
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
public class UpdateHoursWorkedPerWeekTest {

    public UpdateHoursWorkedPerWeekTest() {
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
     * Test of execute method, of class UpdateHoursWorkedPerWeek.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] input = new String[] { "command", "8" };
        UpdateHoursWorkedPerWeek instance = new UpdateHoursWorkedPerWeek();
        instance.execute(input);

        assertEquals(8, CustomerData.CUSTOMER.getCustomer().getWorkHours());
        assertEquals(WorkHours._60_PLUS_HRS, CustomerData.CUSTOMER.getCustomer().getWorkHoursEnum());
        
    }

}