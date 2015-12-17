/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package durbodax.commands;

import durbodax.customers.Customer;
import durbodax.daos.CustomerDAO;
import durbodax.daos.CustomerIncomeDAO;
import durbodax.daos.DAOFactory;

/**
 *
 * @author SimmonsD
 */
public class ComputePromo implements Command {

    private boolean suppressOutput = false;
    CustomerDAO customerDAO;
    CustomerIncomeDAO incomeDAO;

    public ComputePromo()
    {
        customerDAO = DAOFactory.getDAOFactory().getCustomerDAO();
        incomeDAO = DAOFactory.getDAOFactory().getIncomeDAO();
    }

    public Object execute(String[] params){
        Customer result = customerDAO.selectCustomer(Integer.parseInt(params[1]));
        if(result ==  null) {

            if(!suppressOutput) System.out.println("Invalid Customer");
            return -1;

        }

        int income = result.getTotalPersonalIncome();
        int totalIncomes = incomeDAO.getTotalDistinctIncomes();

        int incomesLessThan = incomeDAO.getTotalIncomesBelowGivenIncome(income);
        
        //if 90% of incomes are less than customer income (Customer in top 10%). Then 
        //customer promotion is 100 USD
        if(incomesLessThan >= totalIncomes*.9) {
            displayOutput(100);
            return 100;
        }
        //if 85% of incomes are less than customer income (Customer in top 15%). Then 
        //customer promotion is 50 USD
        if(incomesLessThan >= totalIncomes*.85) {
            displayOutput(50);
            return 50;
        }
        //if 80% of incomes are less than customer income (Customer in top 20%). Then
        //customer promotion is 10 USD
        if(incomesLessThan >= totalIncomes*.8) {
            displayOutput(10);
            return 10;
        }

        return 0;
    }

   private void displayOutput(Integer output) {

        if(!suppressOutput) {
            System.out.println("Customer promo is $" + output.toString());
        }
    }

    public void suppressOutput() {
        suppressOutput = true;
    }
}
