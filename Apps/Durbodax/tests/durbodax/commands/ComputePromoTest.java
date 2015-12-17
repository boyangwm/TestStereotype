/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package durbodax.commands;

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
public class ComputePromoTest {

    public ComputePromoTest() {
    }

    /**
     * Test of execute method, of class ComputePromo.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] params = {"test", "test"};
        ComputePromo instance = new ComputePromo();
        instance.suppressOutput();
        params[1] = "215"; //Customer with inctot = 100000
        Object result = instance.execute(params);
        assertEquals(100, result);
        params[1] = "672";//Customer with inctot = 60000
        result = instance.execute(params);
        assertEquals(50,result);
        params[1] = "1005";//Customer with inctot = 45000
        result = instance.execute(params);
        assertEquals(10, result);
    }
}