/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package durbodax.commands;

import durbodax.customers.Customer;
import durbodax.customers.Enums.Sex;
import durbodax.daos.CustomerDAO;
import durbodax.daos.DAOFactory;

/**
 *
 * @author SimmonsD
 */
public class ComputeChildCredit implements Command {


    private boolean suppressOutput = false;


    CustomerDAO customerDAO;

    public ComputeChildCredit()
    {
        customerDAO = DAOFactory.getDAOFactory().getCustomerDAO();
    }


    public Object execute(String[] params){

        Customer result = customerDAO.selectCustomer(Integer.parseInt(params[1]));
        if(result ==  null) {
            
            if(!suppressOutput) System.out.println("Invalid Customer");
            return -1;

        }

        Sex gender = result.getGenderEnum();
        switch(gender) {
            case Female:
                if(result.getAge() >=16){
                    int num = result.getNumChildrenInHouse();

                    if(num <= 5 || num == 7) {
                        int val = (Math.max(num,4) * 100);
                        displayOutput(val);
                        return val;
                    }
                    if(num == 6) {

                        displayOutput(550);
                        return 550;
                    }
                    if(num >= 8) {

                        displayOutput(750);
                        return 750;
                    }
                } else {

                    displayOutput(0);
                    return 0;
                }

            default:
                displayOutput(0);
                return 0;
        }
    }

    private void displayOutput(Integer output) {

        if(!suppressOutput) {

            System.out.println("Child credit: " + output.toString());

        }

    }

    public void suppressOutput() {

        suppressOutput = true;
    }
}
