package durbodax.commands;

import durbodax.customers.Customer;
import durbodax.customers.Enums.Sex;
import durbodax.daos.CustomerDAO;
import durbodax.daos.DAOFactory;

/**
 *
 * @author Kirk Seddon
 */
public class ComputeEmployedMomCredit implements Command {

    public Object execute(String[] params) {
        
        int employedMotherCredit = 0;

        if(params.length >= 1) {
            
            int customerID = Integer.parseInt(params[1]);

            CustomerDAO customerDAO = DAOFactory.getDAOFactory().getCustomerDAO();
            Customer customer = customerDAO.selectCustomer(customerID);
            Sex gender = customer.getGenderEnum();

            switch(gender) {

                case Male:

                    System.out.println("Employed Mom Credit = 0");
                    return employedMotherCredit;

                case Female:

                    Command computeChildCredit = CommandUtil.getComputeChildCreditCommand();
                    Command computeInLawTax = CommandUtil.getComputeInLawTaxCommand();

                    int childCredit = (Integer)computeChildCredit.execute(new String[]{"command", params[1]});
                    int inLawTax = (Integer)computeInLawTax.execute(new String[]{"command", params[1]});

                    employedMotherCredit = (childCredit - inLawTax);
                    System.out.println("Employed Mom Credit = " + Integer.toString(employedMotherCredit));

            }

        }
        return employedMotherCredit;
    }
    
}
