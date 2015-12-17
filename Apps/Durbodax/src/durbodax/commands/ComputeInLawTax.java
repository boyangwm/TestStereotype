package durbodax.commands;

import durbodax.daos.*;
import durbodax.customers.*;
import durbodax.customers.Enums.*;


/**
 *This class computes the income tax
 * of inlaws.
 * This class implements the Command interface
 */
public class ComputeInLawTax implements Command {

    private DerbyDAOFactory derbyDAO;
    private CustomerDAO customerDAO;
    private int InlawTax;
    private Customer customer;
    private RelationWHeadHouse relation;
    private Integer i;
    private boolean suppressOutput = false;
/**
 * Creates a ComputeIncomeTaxSingle object
 * and creates a DAO to connect with the database.
 */
    public ComputeInLawTax() {

        InlawTax = 0;
        derbyDAO = new DerbyDAOFactory();
        customerDAO = derbyDAO.getCustomerDAO();
    }
/**
 * The execute method computes the income tax of an inlaw.
 * @param params A String array argument that contains
 * the command name and customer ID number.
 * @return Returns the income tax of the individual.
 * @throws IllegalArgumentException.
 */
    public Object execute(String[] params) {
        int id = Integer.parseInt(params[1]);
        if (id <= 0 || id > 88443) {
            throw new IllegalArgumentException("Invalid customer ID");
        }

        customer = customerDAO.selectCustomer(id);

        if (customer.getRelationshipWHouseholderEnum() == relation.PARENT_IN_LAW) {
            //setInlawTax(500);
            i = new Integer(500);
            displayOutput(i);
            return i;
        } else {
            i = new Integer(0);
            displayOutput(i);
            return i;
        }

    }

    private void displayOutput(Integer output) {

        if(!suppressOutput) {
            
            System.out.println("In-Law Tax: " + output.toString());

        }

    }

    public void suppressOutput() {

        suppressOutput = true;
    }

}
