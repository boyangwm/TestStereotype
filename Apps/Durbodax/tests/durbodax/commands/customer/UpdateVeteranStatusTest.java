package durbodax.commands.customer;

import durbodax.customers.CustomerData;
import durbodax.customers.Enums.VeteranStatus;
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
public class UpdateVeteranStatusTest {

    public UpdateVeteranStatusTest() {
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
     * Test of execute method, of class UpdateVeteranStatus.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] input = new String[] { "command", "2" };
        UpdateVeteranStatus instance = new UpdateVeteranStatus();
        instance.execute(input);
        
        assertEquals(2, CustomerData.CUSTOMER.getCustomer().getVeteranStatus());
        assertEquals(VeteranStatus.YES, CustomerData.CUSTOMER.getCustomer().getVeteranStatusEnum());
        
    }

}