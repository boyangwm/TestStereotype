package durbodax.commands.customer;

import durbodax.customers.CustomerData;
import durbodax.customers.Enums.Ownership;
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
public class UpdateOwnershipTest {

    public UpdateOwnershipTest() {
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
     * Test of execute method, of class UpdateOwnership.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] input = new String[] { "command", "2" };
        UpdateOwnership instance = new UpdateOwnership();
        instance.execute(input);
        
        assertEquals(2, CustomerData.CUSTOMER.getCustomer().getOwnership());
        assertEquals(Ownership.RENTED, CustomerData.CUSTOMER.getCustomer().getOwnershipEnum());

        
    }

}