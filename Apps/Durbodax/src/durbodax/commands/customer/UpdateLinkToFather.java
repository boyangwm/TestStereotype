package durbodax.commands.customer;

import durbodax.commands.Command;
import durbodax.customers.CustomerData;

/**
 *
 * @author Kirk Seddon
 */
public class UpdateLinkToFather implements Command {

    public Object execute(String[] input) {

        CustomerData.CUSTOMER.getCustomer().setLinkToFather(Integer.parseInt(input[1]));
        return 0;

    }
}
