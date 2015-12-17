/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package durbodax.commands;

import durbodax.daos.*;
import durbodax.dtos.*;
import java.util.*;

/**
 * GetMarketingTopBottom returns Top x customers by Age,Income,BirthPlace
 * and Marital Status
 * @author gbeckenbaugh
 */
public class GetMarketingTopBottom implements Command {

    /**
     * default constructor
     */
    public GetMarketingTopBottom() {}

    /**
     * parseInput - parses an array from command line
     * @param paramsIn - String[]
     * @return MarketingRequestDTO (Data Transfer Object)
     */
    private MarketingRequestDTO parseInput (String[] paramsIn){

        String[] sAge = null;
        sAge = paramsIn[3].split("-");
        String[] sIncome = null;
        sIncome = paramsIn[4].split("-");

        MarketingRequestDTO m = new MarketingRequestDTO();

        try{
            m.setTopOrBottom(paramsIn[1]);
            m.setGroupSize(Integer.parseInt(paramsIn[2]));
            m.setAgeFrom(Integer.parseInt(sAge[0]));
            //set end of range = beginning if only 1 number provided
            if (sAge.length == 2){
                m.setAgeTo(Integer.parseInt(sAge[1]));
            } else {
                m.setAgeTo(m.getAgeFrom());
            }
            m.setIncomeFrom(Integer.parseInt(sIncome[0]));
            //set end of range = beginning if only 1 number provided
            if (sIncome.length == 2){
                m.setIncomeTo(Integer.parseInt(sIncome[1]));
            } else {
                m.setIncomeTo(m.getIncomeFrom());
            }
            if (paramsIn[5].toLowerCase().equals("all")){
                m.setBirthPlace(0);
            } else {
                m.setBirthPlace(Integer.parseInt(paramsIn[5]));
                if (m.getBirthPlace() == 0) {
                    throw new Exception ("Invalid input parameters");
                }
            }
            if (paramsIn[6].toLowerCase().equals("all")){
                m.setMaritalStatus(0);
            } else {
                m.setMaritalStatus(Integer.parseInt(paramsIn[6]));
                if (m.getMaritalStatus() == 0) {
                    throw new Exception ("Invalid input parameters");
                }
            }
        }
        catch(Exception e) {
            return null;
        }

        return m;
    }
    /**
     * printOutput - prints results
     * @param mresultIn - ArrayList of MarketingResponseDTO objects
     */
    private void printOutput(ArrayList<MarketingResponseDTO> mresultIn){


        if (mresultIn.size() == 0){
            System.out.println("No Records found:  Please change parameters and retry.");
        }

        for (int i=0;i<mresultIn.size();i++){
          MarketingResponseDTO m = mresultIn.get(i);
          String sId = Integer.toString(m.getId()) + "      ";
          System.out.println("ID: " + sId.substring(1, 8) + " Age: " + m.getAge() +
            " Income: " + m.getIncome()+ " BPL: " + m.getBirthPlace().substring(0, 15) + " " +
            "Marital Status: " + m.getMaritalStatusEnum());
        }
    }
    /**
     * execute - main processing for the Command
     * @param params - String[] from command line
     * @return retObj - Object containing the results
     */
    public Object execute(String[] params) {

        Object retObj = new Object();
        MarketingRequestDTO mReq = parseInput(params);

        if (mReq != null){
           MarketingDAO instance = DAOFactory.getDAOFactory().getMarketingDAO();
           ArrayList<MarketingResponseDTO> mresult = instance.selectMarketingStatsTopBottom(mReq);
           retObj = mresult;
           printOutput(mresult);
        }
        else{
           System.out.println ("Usage:Top or Bottom GroupSize FromAge-ToAge FromIncome-toIncome Birthplace MaritalStatus");
           System.out.println ("EX:  GetMarketingTopBottom TOP 10 50-90 40000-75000 1 1");
        }
        return retObj;
    }
}
