package durbodax.commands.customer;

import durbodax.customers.Customer;
import durbodax.customers.CustomerData;
import durbodax.customers.Enums.ChildrenUnderFive;
import durbodax.customers.Enums.EducationLevel;
import durbodax.customers.Enums.EmploymentStatus;
import durbodax.customers.Enums.Farm;
import durbodax.customers.Enums.LaborForce;
import durbodax.customers.Enums.LastYearWorked;
import durbodax.customers.Enums.LinkToFather;
import durbodax.customers.Enums.LinkToMother;
import durbodax.customers.Enums.LinkToSpouse;
import durbodax.customers.Enums.MaritalStatus;
import durbodax.customers.Enums.MovedIn;
import durbodax.customers.Enums.Ownership;
import durbodax.customers.Enums.Race;
import durbodax.customers.Enums.RelationWHeadHouse;
import durbodax.customers.Enums.ResidentialStatus;
import durbodax.customers.Enums.SchoolStatus;
import durbodax.customers.Enums.SchoolType;
import durbodax.customers.Enums.Sex;
import durbodax.customers.Enums.VeteranStatus;
import durbodax.customers.Enums.WeeksWorkedPerYear;
import durbodax.customers.Enums.WorkHours;
import durbodax.customers.Enums.WorkingClass;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kirk Seddon
 */
public class SaveCustomerDataTest {

    public SaveCustomerDataTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of execute method, of class SaveCustomerData.
     */
    @Test
    public void testExecute() {

        String[] input = {
            "command",
            "35", //customer age
            "8", //eldest child age
            "2", //youngest child age

            "9", //education level - (graduate)
            "1", //employment status - (employed)
            "456", //family unit number

            "0", //farm income
            "1", //farm status - (Non-farm)
            "99", //mother's location in house

            "98", //father's location in house
            "45", //spouse's location in house
            "1", //gender - Male

            "2", //Labor Force Participation - (Yes)
            "10", //last year worked - (CURRENT_YEAR)
            "1", //link to father - (Unambiguous)

            "1", //link to mother -  (Unambiguous)
            "2", //link to spouse - (Wife_Precedes_Husband)
            "1", //marital status - (Married_Spouse_Present)

            "2", //moved in status - (TWo_Years_ago)
            "100000", //non-farm business income
            "2", //number of children in the house

            "5", //number of children under five - (ONE_CHILD)
            "4", //number of couples in the house
            "654", //number of families in the house

            "13", //number of family members in the house
            "6", //number of fathers in the house
            "10", //number of mothers in the house

            "13", //number of siblings in the house
            "3", //occupation income score
            "25000", //other income

            "2", //Ownership - (rented)
            "0", //poverty status
            "1", //Race - (White)

            "1", //Relatiohship - (HOUSEHOLDER)
            "12000", //rent
            "new york", //residence 5 yrs ago

            "7", //residential status - (same_state_not_reported)
            "2", //school status - (yes)
            "3", //school type - private

            "0", //social security income
            "100", //socio-economic index
            "200000", //total family income

            "125000", //total personal income
            "125000", //total wage and salary income
            "0", //total welfare income

            "300000", //total house value
            "1", //veteran status - no service
            "2", //working class- wage or salary

            "6", //hours worked per week - 41-48
            "6", //weeks worked per year - 50-52
            "state of california" //birthplace
        };

        InitCustomerData clearCustomerData = new InitCustomerData();
        CreateCustomer createCustomer = new CreateCustomer();
        SaveCustomerData saveCustomerData = new SaveCustomerData();
        LoadCustomerData loadCustomerData = new LoadCustomerData();

        clearCustomerData.execute(new String[] { "command" } );
        createCustomer.execute(input);
        int customerID = (Integer)saveCustomerData.execute(new String[] { "command" });
        clearCustomerData.execute(new String[] { "command" } );
        loadCustomerData.execute(new String[] { "command", Integer.toString(customerID) });


       Customer customer = CustomerData.CUSTOMER.getCustomer();
        assertEquals(35, customer.getAge());
        assertEquals(8, customer.getAgeOfEldestChild());
        assertEquals(2, customer.getAgeOfYoungestChild());
        assertEquals(EducationLevel.GRADUATE, customer.getEducationLevelEnum());
        assertEquals(EmploymentStatus.EMPLOYED, customer.getEmploymentStatusEnum());
        assertEquals(456, customer.getFamilyMembership());
        assertEquals(0, customer.getFarmIncome());
        assertEquals(Farm.NON_FARM, customer.getFarmStatusEnum());
        assertEquals(99, customer.getMothersLocationInHouse());
        assertEquals(98, customer.getFathersLocationInHouse());
        assertEquals(45, customer.getSpouseLocationInHouse());
        assertEquals(Sex.Male, customer.getGenderEnum());
        assertEquals(LaborForce.YES, customer.getLaborForceEnum());
        assertEquals(LastYearWorked.CURRENT_YEAR, customer.getLastYearWorkedEnum());
        assertEquals(LinkToMother.UNAMBIGUOUS_MOTHER_LINK, customer.getLinkToMotherEnum());
        assertEquals(LinkToFather.UNAMBIGUOUS_FATHER_LINK, customer.getLinkToFatherEnum());
        assertEquals(LinkToSpouse.WIFE_PRECEDES_HUSBAND, customer.getLinkToSpouseEnum());
        assertEquals(MaritalStatus.MARRIED_SPOUSE_PRESENT, customer.getMaritalStatusEnum());
        assertEquals(MovedIn.TWO_YEAR_AGO, customer.getMovedInCodeEnum());
        assertEquals(100000, customer.getNonFarmBusinessIncome());
        assertEquals(2, customer.getNumChildrenInHouse());
        assertEquals(ChildrenUnderFive.FIVE_CHILDREN, customer.getNumChildrenUnderFiveInHouseEnum());
        assertEquals(4, customer.getNumCouplesInHouse());
        assertEquals(654, customer.getNumFamiliesInHouse());
        assertEquals(13, customer.getNumFamilyMembersInHouse());
        assertEquals(6, customer.getNumFathersInHouse());
        assertEquals(10, customer.getNumMothersInHouse());
        assertEquals(13, customer.getNumSiblingsInHouse());
        assertEquals(3, customer.getOccupationIncomeScore());
        assertEquals(25000, customer.getOtherIncome());
        assertEquals(Ownership.RENTED, customer.getOwnershipEnum());
        assertEquals(0, customer.getPovertyStatus());
        assertEquals(Race.White, customer.getRaceEnum());
        assertEquals(RelationWHeadHouse.HOUSEHOLDER, customer.getRelationshipWHouseholderEnum());
        assertEquals(12000, customer.getRent());
        assertTrue("new york".equalsIgnoreCase(customer.getResidence5YearsAgo().trim()));
        assertEquals(ResidentialStatus.SAME_STATE_NOT_REPORTED, customer.getResidentialStatusEnum());
        assertEquals(SchoolStatus.YES, customer.getSchoolStatusEnum());
        assertEquals(SchoolType.PRIVATE_SCHOOL, customer.getSchoolTypeEnum());
        assertEquals(0, customer.getSocialSecurityIncome());
        assertEquals(100, customer.getSocioEconomicIndex());
        assertEquals(200000, customer.getTotalFamilyIncome());
        assertEquals(125000, customer.getTotalPersonalIncome());
        assertEquals(125000, customer.getWageAndSalaryIncome());
        assertEquals(0, customer.getWelfareIncome());
        assertEquals(300000, customer.getValue());
        assertEquals(VeteranStatus.NO_SERVICE, customer.getVeteranStatusEnum());
        assertEquals(WorkingClass.WAGE_OR_SALARY, customer.getWorkClassEnum());
        assertEquals(WorkHours._41_TO_48_HRS, customer.getWorkHoursEnum());
        assertEquals(WeeksWorkedPerYear._50_TO_52_WEEKS, customer.getWeeksWorkedPerYearEnum());
        assertTrue("state of california".equalsIgnoreCase(customer.getBirthplace().trim()));
    }

}