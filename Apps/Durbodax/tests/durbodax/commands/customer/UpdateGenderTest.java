package durbodax.commands.customer;

import durbodax.customers.CustomerData;
import durbodax.customers.Enums.Sex;
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
public class UpdateGenderTest {

    public UpdateGenderTest() {
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
     * Test of execute method, of class UpdateGender.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] input = new String[] { "command", "1" };
        UpdateGender instance = new UpdateGender();
        instance.execute(input);
        
        assertEquals(1, CustomerData.CUSTOMER.getCustomer().getGender());
        assertEquals(Sex.Male, CustomerData.CUSTOMER.getCustomer().getGenderEnum());
        
    }

}