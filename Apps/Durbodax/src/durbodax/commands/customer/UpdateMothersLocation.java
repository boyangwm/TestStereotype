package durbodax.commands.customer;

import durbodax.commands.Command;
import durbodax.customers.CustomerData;

/**
 *
 * @author Kirk
 */
public class UpdateMothersLocation implements Command {

    public Object execute(String[] input) {

        CustomerData.CUSTOMER.getCustomer().setMothersLocationInHouse(Integer.parseInt(input[1]));
        return 0;

    }

}
