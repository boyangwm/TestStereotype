package durbodax.commands.customer;

import durbodax.customers.CustomerData;
import durbodax.customers.Enums.LinkToMother;
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
public class UpdateLinkToMotherTest {

    public UpdateLinkToMotherTest() {
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
     * Test of execute method, of class UpdateLinkToMother.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] input = new String[] { "command", "7" };
        UpdateLinkToMother instance = new UpdateLinkToMother();
        instance.execute(input);
        
        assertEquals(7, CustomerData.CUSTOMER.getCustomer().getLinkToMother());
        assertEquals(LinkToMother.STEP_MOTHER, CustomerData.CUSTOMER.getCustomer().getLinkToMotherEnum());
        
    }

}