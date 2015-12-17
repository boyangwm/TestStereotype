
package durbodax.daos;

import durbodax.dtos.MarketingRequestDTO;
import durbodax.dtos.MarketingResponseDTO;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * DerbyMarketingDAOTest
 * @author gbeckenbaugh
 */
public class DerbyMarketingDAOTest {

    public DerbyMarketingDAOTest() {
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
     * Test of selectMarketingStats method, of class DerbyMarketingDAO.
     */
    @Test
    public void testSelectMarketingStatsTopBottom() {
        System.out.println("selectMarketingStatsTopBottom - TOP");

        MarketingDAO instance = DAOFactory.getDAOFactory().getMarketingDAO();

        MarketingRequestDTO mReqIn = new MarketingRequestDTO();
        MarketingResponseDTO mRes = new MarketingResponseDTO();

        mReqIn.setTopOrBottom("top");
        mReqIn.setAgeFrom(50);
        mReqIn.setAgeTo(80);
        mReqIn.setBirthPlace(1);
        mReqIn.setGroupSize(10);
        mReqIn.setIncomeFrom(40000);
        mReqIn.setIncomeTo(90000);
        mReqIn.setMaritalStatus(1);

        ArrayList<MarketingResponseDTO> mresult = instance.selectMarketingStatsTopBottom(mReqIn);
        mRes = mresult.get(0);

        // check result set size
        int expResult = 10;
        int result = mresult.size();
        assertEquals(expResult, result);

        // check income of first record in result set
        expResult = 86398;
        result = mRes.getIncome();
        assertEquals(expResult, result);

        System.out.println("selectMarketingStatsTopBottom - BOTOM");

        mReqIn.setTopOrBottom("bottom");

        ArrayList<MarketingResponseDTO> mresult2 = instance.selectMarketingStatsTopBottom(mReqIn);
        mRes = mresult2.get(0);

        // check result set size
        expResult = 10;
        result = mresult2.size();
        assertEquals(expResult, result);

        // check income of first record in result set
        expResult = 42000;
        result = mRes.getIncome();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSelectMarketingStatsByGroup() {
        System.out.println("selectMarketingStatsByGroup - TOP");

        MarketingDAO instance = DAOFactory.getDAOFactory().getMarketingDAO();

        MarketingRequestDTO mReqIn = new MarketingRequestDTO();
        MarketingResponseDTO mRes = new MarketingResponseDTO();

        mReqIn.setTopOrBottom("top");
        mReqIn.setAgeFrom(1);
        mReqIn.setAgeTo(50);
        mReqIn.setBirthPlace(0);
        mReqIn.setGroupSize(5);
        mReqIn.setIncomeFrom(10000);
        mReqIn.setIncomeTo(50000);
        mReqIn.setMaritalStatus(0);

        ArrayList<MarketingResponseDTO> mresult = instance.selectMarketingStatsByGroup(mReqIn);
        mRes = mresult.get(0);
        
        // check result set size
        int expResult = 5;
        int result = mresult.size();
        assertEquals(expResult, result);

        // check count of first record in result set
        expResult = 13218;
        result = mRes.getCount();
        assertEquals(expResult, result);


        System.out.println("selectMarketingStatsByGroup - BOTOM");

        mReqIn.setTopOrBottom("bottom");

        ArrayList<MarketingResponseDTO> mresult2 = instance.selectMarketingStatsByGroup(mReqIn);
        mRes = mresult2.get(0);

        // check result set size
        expResult = 5;
        result = mresult.size();
        assertEquals(expResult, result);

        // check count of first record in result set
        expResult = 1;
        result = mRes.getCount();
        assertEquals(expResult, result);
    }
}