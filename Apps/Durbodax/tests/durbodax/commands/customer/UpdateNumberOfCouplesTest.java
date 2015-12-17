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
public class UpdateNumberOfCouplesTest {

    public UpdateNumberOfCouplesTest() {
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
     * Test of execute method, of class UpdateNumberOfCouples.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] input = new String[] { "command", "35" };
        UpdateNumberOfCouples instance = new UpdateNumberOfCouples();
        instance.execute(input);
        
        assertEquals(35, CustomerData.CUSTOMER.getCustomer().getNumCouplesInHouse());
        
    }

}