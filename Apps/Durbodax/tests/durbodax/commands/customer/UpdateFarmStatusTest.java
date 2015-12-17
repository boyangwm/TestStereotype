package durbodax.commands.customer;

import durbodax.customers.CustomerData;
import durbodax.customers.Enums.Farm;
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
public class UpdateFarmStatusTest {

    public UpdateFarmStatusTest() {
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
     * Test of execute method, of class UpdateFarmStatus.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] input = new String[] { "command", "2" };
        UpdateFarmStatus instance = new UpdateFarmStatus();
        instance.execute(input);

        assertEquals(2, CustomerData.CUSTOMER.getCustomer().getFarmStatus());
        assertEquals(Farm.FARM, CustomerData.CUSTOMER.getCustomer().getFarmStatusEnum());
        
    }

}