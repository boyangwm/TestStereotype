package durbodax.daos;

import java.sql.*;
import durbodax.customers.*;

/**
 * DerbyCustomerDAO Derby specific implemenation to retrieve customer data from ipums db
 * @author gbeckenbaugh
 */
public class DerbyCustomerDAO implements CustomerDAO {

    private Connection _conn = null;
    final static String queryStatement =
        "SELECT MAIN.ID,YEARR,GQ,GQTYPE,FARM,OWNERSHP,VALUE,RENT,FTOTINC,NFAMS,NCOUPLES,NMOTHERS," + //12 fields
        "NFATHERS,MOMLOC,STEPMOM,MOMRULE,POPLOC,STEPPOP,POPRULE,SPLOC,SPRULE,FAMSIZE,NCHILD,NCHLT5," + //24 fields
        "FAMUNIT,ELDCH,YNGCH,NSIBS,RELATE,AGE,SEX,RACE,MARST,CHBORN,BPL,SCHOOL,EDUCREC,SCHLTYPE,EMPSTAT," + //39 fields
        "LABFORCE,OCC1950,OCCSCORE,SEI,IND1950,CLASSWKR,WKSWORK2,HRSWORK2,YRLASTWK,WORKEDYR,INCTOT," + //50 fields
        "INCWAGE,INCBUS,INCFARM,INCSS,INCWELFR,INCOTHER,POVERTY,MIGRATE5,MIGPLAC5,MOVEDIN,VETSTAT,TRANWORK," + //62 fields
        "BPL.DESCRIPTION,CHBORN.DESCRIPTION,OCC1950.DESCRIPTION,MIGPLAC5.DESCRIPTION " + //66 fields
        "FROM MAIN " +
        "INNER JOIN BPL ON MAIN.BPL=BPL.ID " +
        "INNER JOIN CHBORN ON MAIN.CHBORN=CHBORN.ID " +
        "INNER JOIN OCC1950 ON MAIN.OCC1950=OCC1950.ID " +
        "INNER JOIN MIGPLAC5 ON MAIN.MIGPLAC5=MIGPLAC5.ID ";

    /**
     * fillCustomer populates the Customer Object from the Resultset
     * @param rsIn ResultSet
     * @return Customer
     */
    private Customer fillCustomer (ResultSet rsIn){

        Customer c = new Customer();

        try{
                   
            c.setAge(rsIn.getInt("AGE"));                        //AGE
            c.setAgeOfEldestChild(rsIn.getInt("ELDCH"));           //ELDCH
            c.setAgeOfYoungestChild(rsIn.getInt("YNGCH"));         //YNGCH
            c.setBirthplace(rsIn.getString(63));              //BPL.DESCRIPTION
            c.setChildBorn(rsIn.getString(64));               //CHBORN.DESCRIPTION
            c.setEducationLevel(rsIn.getInt("EDUCREC"));             //EDUCREC
            c.setEmploymentStatus(rsIn.getInt("EMPSTAT"));           //EMPSTAT
            c.setFamilyMembership(rsIn.getInt("FAMUNIT"));           //FAMUNIT
            c.setFarmIncome(rsIn.getInt("INCFARM"));                 //INCFARM
            c.setFarmStatus(rsIn.getInt("FARM"));                  //FARM
            c.setFathersLocationInHouse(rsIn.getInt("POPLOC"));     //POPLOC
            c.setGender(rsIn.getInt("SEX"));                     //SEX
            c.setId(rsIn.getInt(1));                          //MAIN.ID
            c.setIndustry1950Basis(rsIn.getInt("IND1950"));          //IND1950
            c.setLaborForce(rsIn.getInt("LABFORCE"));                 //LABFORCE
            c.setLastYearWorked(rsIn.getInt("YRLASTWK"));             //YRLASTWK
            c.setLinkToFather(rsIn.getInt("POPRULE"));               //POPRULE
            c.setLinkToMother(rsIn.getInt("MOMRULE"));               //MOMRULE
            c.setLinkToSpouse(rsIn.getInt("SPRULE"));               //SPRULE
            c.setMaritalStatus(rsIn.getInt("MARST"));              //MARST
            c.setMothersLocationInHouse(rsIn.getInt("MOMLOC"));     //MOMLOC
            c.setMovedInCode(rsIn.getInt("MOVEDIN"));                //MOVEDIN
            c.setNonFarmBusinessIncome(rsIn.getInt("INCBUS"));      //INCBUS
            c.setNumChildenUnderFiveInHouse(rsIn.getInt("NCHLT5")); //NCHLT5
            c.setNumChildrenInHouse(rsIn.getInt("NCHILD"));         //NCHILD
            c.setNumCouplesInHouse(rsIn.getInt("NCOUPLES"));          //NCOUPLES
            c.setNumFamiliesInHouse(rsIn.getInt("NFAMS"));         //NFAMS
            c.setNumFamilyMembersInHouse(rsIn.getInt("FAMSIZE"));    //FAMSIZE
            c.setNumFathersInHouse(rsIn.getInt("NFATHERS"));          //NFATHERS
            c.setNumMothersInHouse(rsIn.getInt("NMOTHERS"));          //NMOTHERS
            c.setNumSiblingsInHouse(rsIn.getInt("NSIBS"));         //NSIBS
            c.setOccupation(rsIn.getString(65));              //OCC1950.DESCRIPTION
            c.setOccupation1950Basis(rsIn.getInt("OCC1950"));        //OCC1950
            c.setOccupationIncomeScore(rsIn.getInt("OCCSCORE"));      //OCCSCORE
            c.setOtherIncome(rsIn.getInt("INCOTHER"));                //INCOTHER
            c.setOwnership(rsIn.getInt("OWNERSHP"));                   //OWNERSHP
            c.setPovertyStatus(rsIn.getInt("POVERTY"));              //POVERTY
            c.setProbableStepFather(rsIn.getInt("STEPPOP"));         //STEPPOP
            c.setProbableStepMother(rsIn.getInt("STEPMOM"));         //STEPMOM
            c.setRace(rsIn.getInt("RACE"));                       //RACE
            c.setRelationshipWHouseholder(rsIn.getInt("RELATE"));   //RELATE
            c.setRent(rsIn.getInt("RENT"));                        //RENT
            c.setResidence5YearsAgo(rsIn.getString(66));      //MIGPLAC5.DESCRIPTION
            c.setResidentialStatus(rsIn.getInt("MIGRATE5"));          //MIGRATE5
            c.setSchoolStatus(rsIn.getInt("SCHOOL"));               //SCHOOL
            c.setSchoolType(rsIn.getInt("SCHLTYPE"));                 //SCHLTYPE
            c.setSocialSecurityIncome(rsIn.getInt("INCSS"));       //INCSS
            c.setSocioEconomicIndex(rsIn.getInt("SEI"));         //SEI
            c.setSpouseLocationInHouse(rsIn.getInt("SPLOC"));      //SPLOC
            c.setTotalFamilyIncome(rsIn.getInt("FTOTINC"));           //FTOTINC
            c.setTotalPersonalIncome(rsIn.getInt("INCTOT"));        //INCTOT
            c.setValue(rsIn.getInt("VALUE"));                       //VALUE
            c.setVeteranStatus(rsIn.getInt("VETSTAT"));              //VETSTAT
            c.setWageAndSalaryIncome(rsIn.getInt("INCWAGE"));        //INCWAGE
            c.setWeeksWorkedPerYear(rsIn.getInt("WKSWORK2"));         //WKSWORK2
            c.setWelfareIncome(rsIn.getInt("INCWELFR"));              //INCWELFR
            c.setWorkClass(rsIn.getInt("CLASSWKR"));                  //CLASSWKR
            c.setWorkHours(rsIn.getInt("HRSWORK2"));                  //HRSWORK2
        }
        catch(SQLException e){
            System.out.println("fillCustomer Failed: " + e.toString());
        }

        return c;
    }
    /**
     * selectCustomer retrieves a customer from the main table
     * @param idIn integer
     * @return Customer
     */
    public Customer selectCustomer(int idIn){

        ResultSet rs;
        Statement s;
        Connection conn = DerbyDAOFactory.getConnection();
        Customer c = new Customer();
        String selectQuery = queryStatement + "WHERE MAIN.ID = " + Integer.toString(idIn);

        try{
            s = conn.createStatement();
            rs = s.executeQuery(selectQuery);
            boolean foundRec = rs.next();
            if (foundRec){
                c = fillCustomer(rs);
            } else {
             
                rs.close();
                s.close();
                return null;

            }

            rs.close();
            s.close();
        }
        catch(SQLException e){
            System.out.println("Select Customer Failed: " + e.toString());
        }
        return c;
    }


    /**
     * Persists customer data to the main table
     * @param idIn integer
     * @return Customer
     */
    public int saveCustomer(Customer customer){

        if(customer.getId() == -1) {

           return insertCustomer(customer);

        } else {

           return updateCustomer(customer);

        }
    }


    private int insertCustomer(Customer customer){

        Statement insert;

        Connection conn = DerbyDAOFactory.getConnection();
        int index;
        int birthplaceId;
        int residenceId;

        //get next sequential index number
        index = getIndex(conn, "MAIN");

        if(index != -1) {

            birthplaceId = getBirthPlaceId(conn, customer);
            residenceId = getResidenceId(conn, customer);

            String insertSQL = "INSERT INTO MAIN " +
                "(ID,YEARR,GQ,GQTYPE,FARM,OWNERSHP," + //6
                "VALUE,RENT,FTOTINC,NFAMS,NCOUPLES," + //11
                "NMOTHERS,NFATHERS,MOMLOC,STEPMOM,MOMRULE," + //16
                "POPLOC,STEPPOP,POPRULE,SPLOC,SPRULE," + //21
                "FAMSIZE,NCHILD,NCHLT5,FAMUNIT,ELDCH," + //26
                "YNGCH,NSIBS,RELATE,AGE,SEX," + //31
                "RACE,MARST,CHBORN,BPL,SCHOOL," + //36
                "EDUCREC,SCHLTYPE,EMPSTAT,LABFORCE,OCC1950," + //41
                "OCCSCORE,SEI,IND1950,CLASSWKR,WKSWORK2," + //46
                "HRSWORK2,YRLASTWK,INCTOT,INCWAGE," + //50
                "INCBUS,INCFARM,INCSS,INCWELFR,INCOTHER," + //55
                "POVERTY,MIGRATE5,MIGPLAC5,MOVEDIN,VETSTAT," + //60
                "TRANWORK)" + //61
                "VALUES" +
                "(" + Integer.toString(index) + ",99,1,0," +  //4
                Integer.toString(customer.getFarmStatus()) + "," +
                Integer.toString(customer.getOwnership()) + "," + //6

                Integer.toString(customer.getValue()) +  "," +
                Integer.toString(customer.getRent()) + "," +
                Integer.toString(customer.getTotalFamilyIncome()) + "," +
                Integer.toString(customer.getNumFamiliesInHouse()) + "," +
                Integer.toString(customer.getNumCouplesInHouse()) + "," + //11

                Integer.toString(customer.getNumMothersInHouse()) + "," +
                Integer.toString(customer.getNumFathersInHouse()) + "," +
                Integer.toString(customer.getMothersLocationInHouse()) +
                ",0," + Integer.toString(customer.getLinkToMother()) + "," + //16

                Integer.toString(customer.getFathersLocationInHouse()) + ",0," +
                Integer.toString(customer.getLinkToFather()) + "," +
                Integer.toString(customer.getSpouseLocationInHouse()) + "," +
                Integer.toString(customer.getLinkToSpouse()) + "," + //21

                Integer.toString(customer.getNumFamilyMembersInHouse()) + "," +
                Integer.toString(customer.getNumChildrenInHouse()) + "," +
                Integer.toString(customer.getNumChildenUnderFiveInHouse()) + "," +
                Integer.toString(customer.getFamilyMembership()) + "," +
                Integer.toString(customer.getAgeOfEldestChild()) + "," + //26

                Integer.toString(customer.getAgeOfYoungestChild()) + "," +
                Integer.toString(customer.getNumSiblingsInHouse()) + "," +
                Integer.toString(customer.getRelationshipWHouseholder()) + "," +
                Integer.toString(customer.getAge()) + "," +
                Integer.toString(customer.getGender()) + "," + //31

                Integer.toString(customer.getRace()) + "," +
                Integer.toString(customer.getMaritalStatus()) + ",0," +
                Integer.toString(birthplaceId) + "," +
                Integer.toString(customer.getSchoolStatus()) + "," + //36

                Integer.toString(customer.getEducationLevel()) + "," +
                Integer.toString(customer.getSchoolType()) + "," +
                Integer.toString(customer.getEmploymentStatus()) + "," +
                Integer.toString(customer.getLaborForce()) + ",0," + //41

                Integer.toString(customer.getOccupationIncomeScore()) + "," +
                Integer.toString(customer.getSocioEconomicIndex()) + ",999," +
                Integer.toString(customer.getWorkClass()) + "," +
                Integer.toString(customer.getWeeksWorkedPerYear()) + "," + //46

                Integer.toString(customer.getWorkHours()) + "," +
                Integer.toString(customer.getLastYearWorked()) + "," +
                Integer.toString(customer.getTotalPersonalIncome()) + "," +
                Integer.toString(customer.getWageAndSalaryIncome()) + "," +
                Integer.toString(customer.getNonFarmBusinessIncome()) + "," +
                Integer.toString(customer.getFarmIncome()) + "," + //52

                Integer.toString(customer.getSocialSecurityIncome()) + "," +
                Integer.toString(customer.getWelfareIncome()) + "," +
                Integer.toString(customer.getOtherIncome())+ "," + //55

                Integer.toString(customer.getPovertyStatus()) + "," +
                Integer.toString(customer.getResidentialStatus()) + "," +
                Integer.toString(residenceId) + "," +
                Integer.toString(customer.getMovedInCode()) + "," +
                Integer.toString(customer.getVeteranStatus()) + ",0)"; //61
            
            try {

                insert = conn.createStatement();
                insert.execute(insertSQL);

                insert.close();
        }
        catch(SQLException e){
            System.out.println("Insert Customer Failed: " + e.toString());
        }

        }

        return index;


    }

    private int updateCustomer(Customer customer){

         Statement update;

        Connection conn = DerbyDAOFactory.getConnection();
        int index;
        int birthplaceId;
        int residenceId;

        //get next sequential index number
        index = customer.getId();

        if(index != -1) {

            birthplaceId = getBirthPlaceId(conn, customer);
            residenceId = getResidenceId(conn, customer);

                
            String updateSQL = "UPDATE MAIN " +
                "SET FARM = " + Integer.toString(customer.getFarmStatus()) + "," +
                "OWNERSHP =" + Integer.toString(customer.getOwnership()) + "," +
                
                "VALUE =" + Integer.toString(customer.getValue()) +  "," +
                "rent =" + Integer.toString(customer.getRent()) + "," +
                "FTOTINC =" + Integer.toString(customer.getTotalFamilyIncome()) + "," +
                "NFAMS =" + Integer.toString(customer.getNumFamiliesInHouse()) + "," +
                "NCOUPLES =" + Integer.toString(customer.getNumCouplesInHouse()) + "," +

                "NMOTHERS =" + Integer.toString(customer.getNumMothersInHouse()) + "," +
                "NFATHERS =" + Integer.toString(customer.getNumFathersInHouse()) + "," +
                "MOMLOC =" + Integer.toString(customer.getMothersLocationInHouse()) + "," +
                "MOMRULE =" + Integer.toString(customer.getLinkToMother()) + "," +

                "POPLOC =" + Integer.toString(customer.getFathersLocationInHouse()) + "," +
                "POPRULE =" + Integer.toString(customer.getLinkToFather()) + "," +
                "SPLOC =" + Integer.toString(customer.getSpouseLocationInHouse()) + "," +
                "SPRULE =" + Integer.toString(customer.getLinkToSpouse()) + "," +

                "FAMSIZE =" + Integer.toString(customer.getNumFamilyMembersInHouse()) + "," +
                "NCHILD =" + Integer.toString(customer.getNumChildrenInHouse()) + "," +
                "NCHLT5 =" + Integer.toString(customer.getNumChildenUnderFiveInHouse()) + "," +
                "FAMUNIT =" + Integer.toString(customer.getFamilyMembership()) + "," +
                "ELDCH =" + Integer.toString(customer.getAgeOfEldestChild()) + "," +

                "YNGCH =" + Integer.toString(customer.getAgeOfYoungestChild()) + "," +
                "NSIBS =" + Integer.toString(customer.getNumSiblingsInHouse()) + "," +
                "RELATE =" + Integer.toString(customer.getRelationshipWHouseholder()) + "," +
                "AGE =" + Integer.toString(customer.getAge()) + "," +
                "SEX =" + Integer.toString(customer.getGender()) + "," +

                "RACE =" + Integer.toString(customer.getRace()) + "," +
                "MARST =" + Integer.toString(customer.getMaritalStatus()) + "," +
                "BPL =" + Integer.toString(birthplaceId) + "," +
                "SCHOOL =" + Integer.toString(customer.getSchoolStatus()) + "," +

                "EDUCREC =" + Integer.toString(customer.getEducationLevel()) + "," +
                "SCHLTYPE =" + Integer.toString(customer.getSchoolType()) + "," +
                "EMPSTAT =" + Integer.toString(customer.getEmploymentStatus()) + "," +
                "LABFORCE =" + Integer.toString(customer.getLaborForce()) + "," +

                "OCCSCORE =" + Integer.toString(customer.getOccupationIncomeScore()) + "," +
                "SEI =" + Integer.toString(customer.getSocioEconomicIndex()) + "," +
                "CLASSWKR =" + Integer.toString(customer.getWorkClass()) + "," +
                "WKSWORK2 =" + Integer.toString(customer.getWeeksWorkedPerYear()) + "," +

                "HRSWORK2 =" + Integer.toString(customer.getWorkHours()) + "," +
                "YRLASTWK =" + Integer.toString(customer.getLastYearWorked()) + "," +
                "INCTOT =" + Integer.toString(customer.getTotalPersonalIncome()) + "," +
                "INCWAGE =" + Integer.toString(customer.getWageAndSalaryIncome()) + "," +
                "INCBUS =" + Integer.toString(customer.getNonFarmBusinessIncome()) + "," +
                "INCFARM =" + Integer.toString(customer.getFarmIncome()) + "," +

                "INCSS =" + Integer.toString(customer.getSocialSecurityIncome()) + "," +
                "INCWELFR =" + Integer.toString(customer.getWelfareIncome()) + "," +
                "INCOTHER =" + Integer.toString(customer.getOtherIncome())+ "," +

                "POVERTY =" + Integer.toString(customer.getPovertyStatus()) + "," +
                "MIGRATE5 =" + Integer.toString(customer.getResidentialStatus()) + "," +
                "MIGPLAC5 =" + Integer.toString(residenceId) + "," +
                "MOVEDIN =" + Integer.toString(customer.getMovedInCode()) + "," +
                "VETSTAT =" + Integer.toString(customer.getVeteranStatus()) +
                "WHERE ID =" + Integer.toString(index);
            
            try {

                update = conn.createStatement();
                update.execute(updateSQL);

                update.close();
        }
        catch(SQLException e){
            System.out.println("Update Customer Failed: " + e.toString());
        }

        }

        return index;

    }


    private int getIndex(Connection conn, String table) {

        ResultSet result;
        Statement getIndex;

        int index = -1;

        String selectSQL = "select COUNT(*) from " + table;

        try {

            getIndex = conn.createStatement();
            result = getIndex.executeQuery(selectSQL);

            if(result.next()) {

                index = result.getInt(1)+1;
                //System.out.println("New index for table " + table + " is " + Integer.toString(index));
            }

            result.close();
            getIndex.close();

        }
        catch(SQLException e){
            System.out.println("Unable to retrieve next sequential index value: " + e.toString());
        }

        return index;

    }


    private int getBirthPlaceId(Connection conn, Customer customer) {

        ResultSet result;
        Statement findBirthplace;

        int birthplaceId = -1;

        try {


                //see if birthplace exists, if not add it
                String birthplaceLookupSQL = "select * from BPL WHERE UPPER(DESCRIPTION) ='" + customer.getBirthplace().trim().toUpperCase() + "'";

                findBirthplace = conn.createStatement();
                result = findBirthplace.executeQuery(birthplaceLookupSQL);

                if(result.next()) {

                    //birthplace found
                    birthplaceId = result.getInt("ID");

                } else {

                    //need to add it to table
                    birthplaceId = getNewBirthplaceId(conn,customer);

                }

                result.close();
                findBirthplace.close();

            } catch(SQLException e){

                System.out.println("Error during birthplace lookup: " + e.toString());
                
             } catch(Exception e) {

                 System.out.println(e.getMessage());
                 System.out.println(e.getStackTrace());

             }

        return birthplaceId;
    }


    private int getResidenceId(Connection conn, Customer customer) {

        ResultSet result;
        Statement findResidence;

        int residenceId = -1;

        try {

            String residenceLookupSQL = "select * from MIGPLAC5 WHERE UPPER(DESCRIPTION) ='" + customer.getResidence5YearsAgo().trim().toUpperCase() + "'";

                findResidence = conn.createStatement();
                result = findResidence.executeQuery(residenceLookupSQL);

                if(result.next()) {

                    //birthplace found
                    residenceId = result.getInt("ID");

                } else {

                    residenceId = getNewResidenceId(conn,customer);

                }

                result.close();
                findResidence.close();

            } catch(SQLException e){

                System.out.println("Error during residence lookup: " + e.toString());
                
             } catch(Exception e) {

                 System.out.println(e.getMessage());
                 System.out.println(e.getStackTrace());

             }

         return residenceId;

    }

    private int getNewResidenceId(Connection conn, Customer customer) {

        Statement insertResidence;

        int index = 999 + getIndex(conn, "MIGPLAC5");

        String residenceInsertSQL = "INSERT INTO MIGPLAC5 (ID,DESCRIPTION) VALUES (" + Integer.toString(index) + ",'" + customer.getResidence5YearsAgo() + "')";

            try {

                insertResidence = conn.createStatement();
                insertResidence.execute(residenceInsertSQL);

                insertResidence.close();

            } catch(SQLException e){

                System.out.println("Error during residence lookup: " + e.toString());
             }

         return index;

    }

    private int getNewBirthplaceId(Connection conn, Customer customer) {

        Statement insertBirthplace;

        int index = 999 + getIndex(conn, "BPL");

        String birthplaceInsertSQL = "INSERT INTO BPL (ID,DESCRIPTION) VALUES (" + Integer.toString(index) + ",'" + customer.getBirthplace() + "')";

            try {

                insertBirthplace = conn.createStatement();
                insertBirthplace.execute(birthplaceInsertSQL);

                insertBirthplace.close();

            } catch(SQLException e){

                System.out.println("Error during residence lookup: " + e.toString());
             }

         return index;

    }

}

