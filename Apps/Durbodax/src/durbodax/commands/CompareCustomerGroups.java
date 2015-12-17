/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package durbodax.commands;

import durbodax.customers.Enums.Sex;
import durbodax.daos.CustomerCompareDAO;
import durbodax.daos.DAOFactory;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SimmonsD
 */
public class CompareCustomerGroups implements Command {
    /**
     * parseInput - parses an array from command line
     * @param paramsIn - String[]
     * @return CompareGroupDTO (Data Transfer Object)
     */
    private CustomerCompareDAO parseInput(String[] paramsIn) {
        CustomerCompareDAO compDAO = DAOFactory.getDAOFactory().getCompareDAO();
        int conditions=0;
        for(int i=1; i<paramsIn.length; i++)
        {
            if(paramsIn[i].equals("-a")) {
               conditions++;
               String[] ageComparison =  paramsIn[i+1].split("-");
               if(ageComparison[0].equalsIgnoreCase("Under"))
                   compDAO.addCondition("AGE < "+ ageComparison[1]);
               else
                   compDAO.addCondition("AGE > "+ageComparison[1]);
            }
            if(paramsIn[i].equals("-i")) {
                conditions++;
                String[] incomeComparison = paramsIn[i+1].split("-");
               if(incomeComparison[0].equalsIgnoreCase("Under"))
                   compDAO.addCondition("INCTOT < "+ incomeComparison[1]);
               else
                   compDAO.addCondition("INCTOT > "+incomeComparison[1]);
            }
            if(paramsIn[i].equals("-m")) {
                conditions++;
               String maritalStatus = paramsIn[i+1];
               compDAO.addCondition("MARST = "+maritalStatus);
            }
        }
        if(conditions == 0) {
            printOutput(new HashMap());
            return null;
        }
        return compDAO;
    }
    /**
     * printOutput - prints results
     * @param mresultIn - ArrayList of MarketingResponseDTO objects
     */
    private void printOutput(Map<Sex, Integer> mresultIn) {

        if (mresultIn.size() == 0) {
            System.out.println("No Records found:  Please change parameters and retry.\n Must Enter at least one comparison.");
            return;
        }
        System.out.println("Males: " + mresultIn.get(Sex.Male) +"%");
        System.out.println("Females: "+ mresultIn.get(Sex.Female) +"%");
    }

    /**
     * execute - main processing for the Command
     * @param params - String[] from command line
     * @return retObj - Object containing the results
     */
    public Object execute(String[] params) {

        Object retObj = new Object();
        CustomerCompareDAO dao = parseInput(params);

        if (dao != null) {
            Map<Sex, Integer> mresult = dao.runQuery();
            retObj = mresult;
            printOutput(mresult);
        } else {
            System.out.println("Usage: -a Under||Over-Age  -i Under||Over-Income -m MaritalStatus");
            System.out.println("EX:  CompareCustomerGroup Male Over-50 Under-40000 1");
        }
        return retObj;
    }
}
