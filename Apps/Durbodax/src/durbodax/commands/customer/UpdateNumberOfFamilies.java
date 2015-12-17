package durbodax.commands.customer;

import durbodax.commands.Command;
import durbodax.customers.CustomerData;

/**
 *
 * @author Kirk
 */
public class UpdateNumberOfFamilies implements Command {

    public Object execute(String[] input) {

        CustomerData.CUSTOMER.getCustomer().setNumFamiliesInHouse(Integer.parseInt(input[1]));
        return 0;

    }

}
