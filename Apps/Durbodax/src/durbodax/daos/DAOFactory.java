package durbodax.daos;

/**
 * DAOFactory is an abstraction of concrete DAO Factories, Derby is the only
 * DB supported at this time
 * @author gbeckenbaugh
 */
public abstract class DAOFactory {
    
    public static DAOFactory getDAOFactory(){
        // return Derby FActory by default since its the only DB used in this project
        return new DerbyDAOFactory();
    }
            
    public abstract DerbyCustomerDAO getCustomerDAO();
    public abstract DerbyMarketingDAO getMarketingDAO();
    public abstract DerbyCustomerIncomeDAO getIncomeDAO();
    public abstract DerbyCustomerCompareDAO getCompareDAO();
}
