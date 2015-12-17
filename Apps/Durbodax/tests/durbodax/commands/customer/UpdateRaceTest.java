package durbodax.commands.customer;

import durbodax.customers.CustomerData;
import durbodax.customers.Enums.Race;
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
public class UpdateRaceTest {

    public UpdateRaceTest() {
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
     * Test of execute method, of class UpdateRace.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] input = new String[] { "command", "3" };
        UpdateRace instance = new UpdateRace();
        instance.execute(input);

        assertEquals(3, CustomerData.CUSTOMER.getCustomer().getRace());
        assertEquals(Race.AMERICAN_INDIAN, CustomerData.CUSTOMER.getCustomer().getRaceEnum());

        
    }

}