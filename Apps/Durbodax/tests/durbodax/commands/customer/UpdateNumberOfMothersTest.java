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
public class UpdateNumberOfMothersTest {

    public UpdateNumberOfMothersTest() {
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
     * Test of execute method, of class UpdateNumberOfMothers.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] input = new String[] { "comand", "27" };
        UpdateNumberOfMothers instance = new UpdateNumberOfMothers();
        instance.execute(input);
        
        assertEquals(27, CustomerData.CUSTOMER.getCustomer().getNumMothersInHouse());
        
    }

}