package durbodax.commands.customer;

import durbodax.customers.CustomerData;
import durbodax.customers.Enums.LinkToSpouse;
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
public class UpdateLinkToSpouseTest {

    public UpdateLinkToSpouseTest() {
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
     * Test of execute method, of class UpdateLinkToSpouse.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] input = new String[] { "command", "6" };
        UpdateLinkToSpouse instance = new UpdateLinkToSpouse();
        instance.execute(input);
        
        assertEquals(6, CustomerData.CUSTOMER.getCustomer().getLinkToSpouse());
        assertEquals(LinkToSpouse.NON_ADJ, CustomerData.CUSTOMER.getCustomer().getLinkToSpouseEnum());
        
    }

}