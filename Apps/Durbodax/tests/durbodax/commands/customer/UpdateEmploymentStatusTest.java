package durbodax.commands.customer;

import durbodax.customers.CustomerData;
import durbodax.customers.Enums.EmploymentStatus;
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
public class UpdateEmploymentStatusTest {

    public UpdateEmploymentStatusTest() {
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
     * Test of execute method, of class UpdateEmploymentStatus.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] input = new String[] { "command", "2" };
        UpdateEmploymentStatus instance = new UpdateEmploymentStatus();
        instance.execute(input);
        
        assertEquals(2, CustomerData.CUSTOMER.getCustomer().getEmploymentStatus());
        assertEquals(EmploymentStatus.UNEMPLOYED, CustomerData.CUSTOMER.getCustomer().getEmploymentStatusEnum());
        
    }

}