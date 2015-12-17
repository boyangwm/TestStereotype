package durbodax.commands.customer;

import durbodax.customers.CustomerData;
import durbodax.customers.Enums.RelationWHeadHouse;
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
public class UpdateRelationshipWithHeadHouseTest {

    public UpdateRelationshipWithHeadHouseTest() {
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
     * Test of execute method, of class UpdateRelationshipWithHeadHouse.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] input = new String[] { "command", "13" };
        UpdateRelationshipWithHeadHouse instance = new UpdateRelationshipWithHeadHouse();
        instance.execute(input);
        
        assertEquals(13, CustomerData.CUSTOMER.getCustomer().getRelationshipWHouseholder());
        assertEquals(RelationWHeadHouse.INMATES, CustomerData.CUSTOMER.getCustomer().getRelationshipWHouseholderEnum());
        
    }

}