package durbodax.commands.customer;

import durbodax.commands.Command;
import durbodax.customers.CustomerData;

/**
 *
 * @author Kirk Seddon
 */
public class SaveCustomerData implements Command {

    public Object execute(String[] input) {

        if(CustomerData.CUSTOMER.getCustomer() != null) {

         return CustomerData.CUSTOMER.saveCustomerData();

        }

        return -1;

    }

}
