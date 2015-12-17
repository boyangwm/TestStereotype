/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package durbodax.daos;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author SimmonsD
 */
public class DerbyCustomerIncomeDAO implements CustomerIncomeDAO {

    public int getTotalDistinctIncomes()
    {
        ResultSet rs;
        Statement s;
        Connection conn = DerbyDAOFactory.getConnection();
        int total = 0;
        String selectQuery = "SELECT COUNT(DISTINCT INCTOT) FROM MAIN ";

        try{
            s = conn.createStatement();
            rs = s.executeQuery(selectQuery);

            if (rs.next()) {
               total = rs.getInt(1);
            }
        }
        catch(SQLException e){
            System.err.println("Select Failed: " + e.toString());
        }
        return total;

    }

    public int getTotalIncomesBelowGivenIncome(int income)
    {
        ResultSet rs;
        Statement s;
        Connection conn = DerbyDAOFactory.getConnection();
        int total = 0;
        String selectQuery = "SELECT COUNT(DISTINCT INCTOT) FROM MAIN WHERE INCTOT <"+income;

        try{
            s = conn.createStatement();
            rs = s.executeQuery(selectQuery);

            if (rs.next()) {
               total = rs.getInt(1);
            }
        }
        catch(SQLException e){
            System.err.println("Select Failed: " + e.toString());
        }
        return total;


    }

}
