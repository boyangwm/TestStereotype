/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package durbodax.daos;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SimmonsD
 */
public class DerbyCustomerIncomeDAOTest {

    public DerbyCustomerIncomeDAOTest() {
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
     * Test of getTotalDistinctIncomes method, of class CustomerIncomeDAO.
     */
    @Test
    public void testGetTotalDistinctIncomes() {
        System.out.println("getTotalDistinctIncomes");
        CustomerIncomeDAO instance = new DerbyCustomerIncomeDAO();
        int expResult = 13510;
        int result = instance.getTotalDistinctIncomes();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTotalIncomesBelowGivenIncome method, of class CustomerIncomeDAO.
     */
    @Test
    public void testGetTotalIncomesBelowGivenIncome() {
        System.out.println("getTotalIncomesBelowGivenIncome");
        int income = 100000;
        CustomerIncomeDAO instance = new DerbyCustomerIncomeDAO();
        int result = instance.getTotalIncomesBelowGivenIncome(income);
        assertEquals(13002, result);

        income = 60000;
        result = instance.getTotalIncomesBelowGivenIncome(income);
        assertEquals(12105, result);

        income = 45000;
        result = instance.getTotalIncomesBelowGivenIncome(income);
        assertEquals(11140, result);
    }

}