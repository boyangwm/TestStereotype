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
public class UpdateEldestChildAgeTest {

    public UpdateEldestChildAgeTest() {
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
     * Test of execute method, of class UpdateEldestChildAge.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] input = new String[] { "command", "12" };
        UpdateEldestChildAge instance = new UpdateEldestChildAge();
        instance.execute(input);
        
        assertEquals(12, CustomerData.CUSTOMER.getCustomer().getAgeOfEldestChild());
        
    }

}