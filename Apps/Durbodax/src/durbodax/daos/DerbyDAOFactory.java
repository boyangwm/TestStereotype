package durbodax.daos;

import java.sql.*;

/**
 * DerbyDAOFactory concrete implementation of the DAOFactory
 * @author gbeckenbaugh
 */
public class DerbyDAOFactory extends DAOFactory {

    private static Connection _conn;

    /**
     * getConnection connects to the ipums DB
     * @return Connection
     */
    public static Connection getConnection(){

        if (_conn != null){
            return _conn;
        }
        // load driver
        String driver = "org.apache.derby.jdbc.ClientDriver";
        // set the database name
        String dbName = "ipums";

        // define the Derby connection URL to use
        String connectionURL = "jdbc:derby://localhost:1527/" + dbName + ";create=true";

        try{
            Class.forName(driver);}
        catch(java.lang.ClassNotFoundException e){
            System.err.println("Load Driver Failed: "+ e.toString());
        }
        try{
            _conn = DriverManager.getConnection(connectionURL);
        }
        catch (SQLException e){
            System.err.println("Get Connection Failed: " + e.toString());
        }
        return _conn;

    }
    /**
     * DerbyCustomerDAO concrete implementation of the CustomerDAO
     * @return DerbyCustomerDAO
     */
    public DerbyCustomerDAO getCustomerDAO() {
        
        return new DerbyCustomerDAO();
    }

    /**
     * DerbyMarketingDAO concrete implementation of the MarketingDAO
     * @return DerbyMarketingDAO
     */
    public DerbyMarketingDAO getMarketingDAO(){

        return new DerbyMarketingDAO();
    }

    /**
     * DerbyCutomerIncomeDAO concrete implementation of the CustomerIncomeDAO
     * @return DerbyCutomerIncomeDAO
     */
    public DerbyCustomerIncomeDAO getIncomeDAO() {
        return new DerbyCustomerIncomeDAO();
    }

        /**
     * DerbyCutomerIncomeDAO concrete implementation of the CustomerIncomeDAO
     * @return DerbyCutomerIncomeDAO
     */
    public DerbyCustomerCompareDAO getCompareDAO() {
        return new DerbyCustomerCompareDAO();
    }

}
