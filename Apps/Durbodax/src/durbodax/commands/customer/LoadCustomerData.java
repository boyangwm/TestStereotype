package durbodax.commands.customer;

import durbodax.commands.Command;
import durbodax.customers.CustomerData;

/**
 *
 * @author Kirk Seddon
 */
public class LoadCustomerData implements Command {

    public Object execute(String[] params) {

        CustomerData.CUSTOMER.loadCustomerData(Integer.parseInt(params[1]));

        return 0;
    }

}
