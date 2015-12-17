/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package durbodax.daos;

import durbodax.customers.Enums.Sex;
import java.util.Map;
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
public class DerbyCustomerCompareDAOTest {

    public DerbyCustomerCompareDAOTest() {
    }

    /**
     * Test of runQuery method, of class CustomerCompareDAO.
     */
    @Test
    public void testRunQuery() {
        System.out.println("addCondition");
        String condition = "AGE > 50";
        CustomerCompareDAO instance = DAOFactory.getDAOFactory().getCompareDAO();
        instance.addCondition(condition);
        System.out.println("runQuery");
        Map<Sex, Integer> result = instance.runQuery();
        assertEquals(19, result.get(Sex.Male).intValue());
        assertEquals(23, result.get(Sex.Female).intValue());
    }
}