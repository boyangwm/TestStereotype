package durbodax.commands.customer;

import durbodax.commands.Command;
import durbodax.customers.CustomerData;

/**
 *
 * @author Kirk Seddon
 */
public class UpdateFathersLocation implements Command {

    public Object execute(String[] input) {

        CustomerData.CUSTOMER.getCustomer().setFathersLocationInHouse(Integer.parseInt(input[1]));
        return 0;

    }

}
