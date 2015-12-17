package durbodax.commands;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import durbodax.dtos.MarketingResponseDTO;

/**
 * GetMarketingTopBottomTest
 * @author gbeckenbaugh
 */
public class GetMarketingTopBottomTest {

    ArrayList <MarketingResponseDTO> mArray;

    public GetMarketingTopBottomTest() {
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
     * testExecute1 tests execute method, of class GetMarketingTopBottom.
     * no 'ALL' parameters
     */
    @Test
    public void testExecute1() {
        System.out.println("execute");
        String[] params = new String[7];
        GetMarketingTopBottom instance = new GetMarketingTopBottom();
        //test1
        params[0] = "GetMarketingTopBottom";
        params[1] = "TOP";          //top or bottom
        params[2] = "5";            //x number to return
        params[3] = "10-50";        //age range
        params[4] = "10000-50000";  //income range
        params[5] = "1";            //birthplace code 1=Alabama
        params[6] = "1";            //marital status 1=Married Spouse Present
        Object testObj = instance.execute(params);
        mArray = (ArrayList)testObj;
        //test number of rows coming back
        int result = mArray.size();
        int expResult = 5;
        assertEquals(expResult, result);
        //test income in 1st row
        result = mArray.get(0).getIncome();
        expResult = 50000;
        assertEquals(expResult, result);
    }
        /**
     * testExecute2 tests execute method, of class GetMarketingTopBottom.
     * 'ALL' parameter for Birthplace
     */
    @Test
    public void testExecute2() {
        System.out.println("execute");
        String[] params = new String[7];
        GetMarketingTopBottom instance = new GetMarketingTopBottom();
        //test1
        params[0] = "GetMarketingTopBottom";
        params[1] = "TOP";          //top or bottom
        params[2] = "10";            //x number to return
        params[3] = "10-50";        //age range
        params[4] = "10000-50000";  //income range
        params[5] = "ALL";            //ALL birthplaces
        params[6] = "1";            //marital status 1=Married Spouse Present
        Object testObj = instance.execute(params);
        mArray = (ArrayList)testObj;
        //test number of rows coming back
        int result = mArray.size();
        int expResult = 10;
        assertEquals(expResult, result);
        //test ID of first row
        result = mArray.get(0).getId();
        expResult = 88415;
        assertEquals(expResult, result);
    }
    /**
     * testExecute3 tests execute method, of class GetMarketingTopBottom
     * when no results are found
     */
    @Test
    public void testExecute3() {
        System.out.println("execute3");
        String[] params = new String[7];
        GetMarketingTopBottom instance = new GetMarketingTopBottom();
        //test1
        params[0] = "GetMarketingCountsByGroup";
        params[1] = "TOP";          //top or bottom
        params[2] = "10";            //x number to return
        params[3] = "1-1";        //age range
        params[4] = "10000-50000";  //income range
        params[5] = "ALL";            //ALL birthplaces
        params[6] = "1";            //marital status 1=Married Spouse Present
        Object testObj = instance.execute(params);
        mArray = (ArrayList)testObj;
        //test number of rows coming back
        int result = mArray.size();
        int expResult = 0;
        assertEquals(expResult, result);

    }
}