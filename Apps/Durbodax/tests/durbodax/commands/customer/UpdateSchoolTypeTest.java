package durbodax.commands.customer;

import durbodax.customers.CustomerData;
import durbodax.customers.Enums.SchoolType;
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
public class UpdateSchoolTypeTest {

    public UpdateSchoolTypeTest() {
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
     * Test of execute method, of class UpdateSchoolType.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] input = new String[] { "command", "4" };
        UpdateSchoolType instance = new UpdateSchoolType();
        instance.execute(input);

        assertEquals(4, CustomerData.CUSTOMER.getCustomer().getSchoolType());
        assertEquals(SchoolType.CHURCH_RELATED, CustomerData.CUSTOMER.getCustomer().getSchoolTypeEnum());
        
    }

}