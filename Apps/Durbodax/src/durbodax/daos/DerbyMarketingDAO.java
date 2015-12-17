package durbodax.daos;

import durbodax.dtos.*;
import java.util.*;
import java.sql.*;

/**
 * DerbyMarketingDAO concrete implementation of the MarketingDAO
 * @author gbeckenbaugh
 */
public class DerbyMarketingDAO implements MarketingDAO {

    private Connection _conn = null;

    /**
     * fillMarketingStats populates the MarketingResponseDTO from the ResultSet
     * @param rsIn ResultSet
     * @return MarketingResponseDTO
     */
    private MarketingResponseDTO fillMarketingStats (ResultSet rsIn){

        MarketingResponseDTO m = new MarketingResponseDTO();

        try{
            m.setAge(rsIn.getInt(4));
            m.setAgeDesc(rsIn.getString(5));
            m.setBirthPlace(rsIn.getString(6));
            m.setCount(rsIn.getInt(8));
            m.setId(rsIn.getInt(1));
            m.setIncome(rsIn.getInt(2));
            m.setIncomeDesc(rsIn.getString(3));
            m.setMaritalStatus(rsIn.getInt(7));
        }
        catch(SQLException e){
            System.err.println("fillMarketingStats Failed: " + e.toString());
        }
        return m;
    }

    /**
     * selectMarketingStatsTopBottom retrieves customers from the ipums DB based
     * on the parmaeters in MarketingRequestDTO
     * @param mReqIn MarketingRequestDTO
     * @return ArrayList
     */
    public ArrayList<MarketingResponseDTO> selectMarketingStatsTopBottom(MarketingRequestDTO mReqIn){

            ArrayList<MarketingResponseDTO> mArray  = new ArrayList<MarketingResponseDTO>();

            ResultSet rs;
            Statement s;
            Connection conn = DerbyDAOFactory.getConnection();
            MarketingResponseDTO m = new MarketingResponseDTO();

            String selectQuery =
                "SELECT MAIN.ID, FTOTINC, ' ', AGE, ' ', BPL.DESCRIPTION, MARST, 1 " +
                  "FROM MAIN " +
                  "INNER JOIN MARST on MAIN.MARST = MARST.ID " +
                  "INNER JOIN BPL ON MAIN.BPL = BPL.ID " +
                "WHERE AGE BETWEEN " + Integer.toString(mReqIn.getAgeFrom()) + " AND " +
                  Integer.toString(mReqIn.getAgeTo()) + " AND " +
                  "FTOTINC BETWEEN " + Integer.toString(mReqIn.getIncomeFrom()) + " AND " +
                  Integer.toString(mReqIn.getIncomeTo());

            if (mReqIn.getBirthPlace() != 0 ){
                  selectQuery = selectQuery +
                    " AND MAIN.BPL = " + mReqIn.getBirthPlace();
            }
            if (mReqIn.getMaritalStatus()!=0 ){
                  selectQuery = selectQuery +
                    " AND MARST = " + Integer.toString(mReqIn.getMaritalStatus());
            }

            if (mReqIn.getTopOrBottom().equalsIgnoreCase("bottom")){
                selectQuery = selectQuery + " ORDER BY FTOTINC FETCH FIRST " +
                   Integer.toString(mReqIn.getGroupSize()) + " ROWS ONLY";
            }
            else{
                selectQuery = selectQuery + " ORDER BY FTOTINC DESC FETCH FIRST " +
                   Integer.toString(mReqIn.getGroupSize()) + " ROWS ONLY";
            }

        try{
            s = conn.createStatement();
            rs = s.executeQuery(selectQuery);

            while (rs.next()) {
               m = fillMarketingStats(rs);
               mArray.add(m);
            }
        }
        catch(SQLException e){
            System.err.println("Select Failed: " + e.toString());
        }

        return mArray;
    }
    
    /**
     * selectMarketingStatsByGropu retrieves customer counts from the ipums DB 
     * based on the parmaeters in MarketingRequestDTO
     * @param mReqIn MarketingRequestDTO
     * @return ArrayList
     */
    public ArrayList<MarketingResponseDTO> selectMarketingStatsByGroup(MarketingRequestDTO mReqIn){

            ArrayList<MarketingResponseDTO> mArray  = new ArrayList<MarketingResponseDTO>();

            ResultSet rs;
            Statement s;
            Connection conn = DerbyDAOFactory.getConnection();
            MarketingResponseDTO m = new MarketingResponseDTO();
            String incomeDesc = Integer.toString(mReqIn.getIncomeFrom()) + "-" +
                Integer.toString(mReqIn.getIncomeTo());
            String ageDesc = Integer.toString(mReqIn.getAgeFrom()) + "-" +
                Integer.toString(mReqIn.getAgeTo());
            
            String selectQuery =
            "SELECT 1, 0, '" + incomeDesc + "',0,'" + ageDesc + "', BPL.DESCRIPTION, MARST, COUNT(*) " +
                  "FROM MAIN " +
                  "INNER JOIN MARST on MAIN.MARST = MARST.ID " +
                  "INNER JOIN BPL ON MAIN.BPL = BPL.ID " +
            "WHERE AGE BETWEEN " + Integer.toString(mReqIn.getAgeFrom()) + " AND " +
                  Integer.toString(mReqIn.getAgeTo()) + " AND " +
                  "FTOTINC BETWEEN " + Integer.toString(mReqIn.getIncomeFrom()) + " AND " +
                  Integer.toString(mReqIn.getIncomeTo());

            if (mReqIn.getBirthPlace() != 0 ){
                  selectQuery = selectQuery +
                    " AND MAIN.BPL = " + mReqIn.getBirthPlace();
            }
            if (mReqIn.getMaritalStatus()!=0 ){
                  selectQuery = selectQuery +
                    " AND MARST = " + Integer.toString(mReqIn.getMaritalStatus());
            }
            selectQuery = selectQuery +
               " GROUP BY MARST, BPL.DESCRIPTION";

            if (mReqIn.getTopOrBottom().equalsIgnoreCase("bottom")){
                selectQuery = selectQuery + " ORDER BY COUNT(*) FETCH FIRST " +
                   Integer.toString(mReqIn.getGroupSize()) + " ROWS ONLY";
            }
            else{
                selectQuery = selectQuery + " ORDER BY COUNT(*) DESC FETCH FIRST " +
                   Integer.toString(mReqIn.getGroupSize()) + " ROWS ONLY";
            }

        try{
            s = conn.createStatement();
            rs = s.executeQuery(selectQuery);

            while (rs.next()) {
               m = fillMarketingStats(rs);
               mArray.add(m);
            }
        }
        catch(SQLException e){
            System.err.println("Select Failed: " + e.toString());
        }

        return mArray;
    }

}
