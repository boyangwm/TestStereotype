package durbodax.commands.customer;

import durbodax.commands.Command;
import durbodax.customers.CustomerData;

/**
 *
 * @author Kirk Seddon
 */
public class UpdateLinkToMother implements Command {

    public Object execute(String[] input) {

        CustomerData.CUSTOMER.getCustomer().setLinkToMother(Integer.parseInt(input[1]));
        return 0;

    }
    
}
