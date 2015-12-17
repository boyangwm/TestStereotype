package durbodax.commands.customer;

import durbodax.customers.CustomerData;
import durbodax.customers.Enums.EducationLevel;
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
public class UpdateEducationLevelTest {

    public UpdateEducationLevelTest() {
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
     * Test of execute method, of class UpdateEducationLevel.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] input = new String[] { "command", "9" };
        UpdateEducationLevel instance = new UpdateEducationLevel();
        instance.execute(input);
        
        assertEquals(9, CustomerData.CUSTOMER.getCustomer().getEducationLevel());
        assertEquals(EducationLevel.GRADUATE, CustomerData.CUSTOMER.getCustomer().getEducationLevelEnum());
        
    }

}