/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package durbodax.commands.customer;

import durbodax.customers.CustomerData;
import durbodax.customers.Enums.ChildrenUnderFive;
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
public class UpdateChildrenUnderFiveTest {

    public UpdateChildrenUnderFiveTest() {
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
     * Test of execute method, of class UpdateChildrenUnderFive.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] input = new String[] { "command", "4" };
        UpdateChildrenUnderFive instance = new UpdateChildrenUnderFive();
        
        instance.execute(input);
        
        assertEquals(4, CustomerData.CUSTOMER.getCustomer().getNumChildenUnderFiveInHouse());
        assertEquals(ChildrenUnderFive.FOUR_CHILDREN, CustomerData.CUSTOMER.getCustomer().getNumChildrenUnderFiveInHouseEnum());
       
    }

}