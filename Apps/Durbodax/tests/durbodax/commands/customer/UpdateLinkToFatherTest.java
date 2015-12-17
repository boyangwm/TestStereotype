package durbodax.commands.customer;

import durbodax.customers.CustomerData;
import durbodax.customers.Enums.LinkToFather;
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
public class UpdateLinkToFatherTest {

    public UpdateLinkToFatherTest() {
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
     * Test of execute method, of class UpdateLinkToFather.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] input = new String[] { "command", "7" };
        UpdateLinkToFather instance = new UpdateLinkToFather();
        instance.execute(input);

        assertEquals(7, CustomerData.CUSTOMER.getCustomer().getLinkToFather());
        assertEquals(LinkToFather.STEP_FATHER, CustomerData.CUSTOMER.getCustomer().getLinkToFatherEnum());
        
    }

}