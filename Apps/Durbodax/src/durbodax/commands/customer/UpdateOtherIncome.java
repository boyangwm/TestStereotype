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
public class UpdateOtherIncome implements Command {

    public Object execute(String[] input) {
        
        CustomerData.CUSTOMER.getCustomer().setOtherIncome(Integer.parseInt(input[1]));
        return 0;
        
    }

}
