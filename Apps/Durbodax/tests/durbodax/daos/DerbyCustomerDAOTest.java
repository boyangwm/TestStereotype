package durbodax.daos;

import durbodax.customers.Customer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * DerbyCustomerDAOTest
 * @author gbeckenbaugh
 */
public class DerbyCustomerDAOTest {

    public DerbyCustomerDAOTest() {
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
     * testSelectCustomerCount tests selectCustomerCount method of class
     * DerbyCustomerDAO.
     */
    @Test
    public void testSelectCustomerValues() {
        System.out.println("selectCustomerValues");

        CustomerDAO instance = DAOFactory.getDAOFactory().getCustomerDAO();

        Customer expResult = null;
        Customer result = instance.selectCustomer(1);
        //check values and total family income from record #1
        assertEquals(162500,result.getValue());
        assertEquals(47648,result.getTotalFamilyIncome());
        //check Birthplace from record #1
        String expBp = "Tennessee";
        String bp = result.getBirthplace().trim();
        assertEquals(expBp,bp);
    }
}