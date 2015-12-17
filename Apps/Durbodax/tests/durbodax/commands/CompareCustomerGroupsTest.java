/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package durbodax.commands;

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
public class CompareCustomerGroupsTest {

    public CompareCustomerGroupsTest() {
    }

    /**
     * Test of execute method, of class CompareCustomerGroups.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] params = {"test", "-a", "Under-Age", "-i", "Over-Income", "-m", "MaritalStatus"};
        CompareCustomerGroups instance = new CompareCustomerGroups();
        params[2] = "Under-80";
        params[4] = "Over-35000";
        params[6] = "1";
        Map result = (Map)instance.execute(params);
        int malePercent = (Integer)result.get(Sex.Male);
        int femalePercent = (Integer)result.get(Sex.Female);
        assertEquals(13, malePercent);
        assertEquals(3,femalePercent);

        params[2] = "Over-20";
        params[4] = "Under-35000";
        params[6] = "6";
        result = (Map)instance.execute(params);
        malePercent = (Integer)result.get(Sex.Male);
        femalePercent = (Integer)result.get(Sex.Female);
        assertEquals(17, malePercent);
        assertEquals(13,femalePercent);
    }

}