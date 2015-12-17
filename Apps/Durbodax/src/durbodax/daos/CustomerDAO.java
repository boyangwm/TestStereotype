package durbodax.daos;

import durbodax.customers.*;

/**
 * CustomerDAO - Interface to be overridden by DB specific DAO
 * @author gbeckenbaugh
 */
public interface CustomerDAO {

    public Customer selectCustomer(int idIn);
    public int saveCustomer(Customer customer);
            
}
