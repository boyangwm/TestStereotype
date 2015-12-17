/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package durbodax.commands.customer;

import durbodax.commands.Command;
import durbodax.customers.CustomerData;

/**
 *
 * @author Kirk
 */
public class UpdateNumberOfSiblings implements Command {

    public Object execute(String[] input) {

        CustomerData.CUSTOMER.getCustomer().setNumSiblingsInHouse(Integer.parseInt(input[1]));
        return 0;

    }

}
