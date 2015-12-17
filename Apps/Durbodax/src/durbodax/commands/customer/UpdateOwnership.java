package durbodax.commands.customer;

import durbodax.commands.Command;
import durbodax.customers.CustomerData;

/**
 *
 * @author Kirk
 */
public class UpdateOwnership implements Command {

    public Object execute(String[] input) {

        CustomerData.CUSTOMER.getCustomer().setOwnership(Integer.parseInt(input[1]));
        return 0;

    }

}
