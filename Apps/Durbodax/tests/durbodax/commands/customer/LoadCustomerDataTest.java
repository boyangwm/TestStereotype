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
public class LoadCustomerDataTest {

    public LoadCustomerDataTest() {
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
     * Test of execute method, of class LoadCustomerData.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String[] params = { "command", "1" };
        LoadCustomerData instance = new LoadCustomerData();
        instance.execute(params);

        Customer customer = CustomerData.CUSTOMER.getCustomer();
        assertEquals(52, customer.getAge());
        assertEquals(99, customer.getAgeOfEldestChild());
        assertEquals(99, customer.getAgeOfYoungestChild());
        assertEquals(EducationLevel.SENIOR, customer.getEducationLevelEnum());
        assertEquals(EmploymentStatus.EMPLOYED, customer.getEmploymentStatusEnum());
        assertEquals(1, customer.getFamilyMembership());
        assertEquals(0, customer.getFarmIncome());
        assertEquals(Farm.NON_FARM, customer.getFarmStatusEnum());
        assertEquals(0, customer.getMothersLocationInHouse());
        assertEquals(0, customer.getFathersLocationInHouse());
        assertEquals(2, customer.getSpouseLocationInHouse());
        assertEquals(Sex.Male, customer.getGenderEnum());
        assertEquals(LaborForce.YES, customer.getLaborForceEnum());
        assertEquals(LastYearWorked.NA, customer.getLastYearWorkedEnum());
        assertEquals(LinkToMother.NO_LINK, customer.getLinkToMotherEnum());
        assertEquals(LinkToFather.NO_LINK, customer.getLinkToFatherEnum());
        assertEquals(LinkToSpouse.WIFE_FOLLOWS_HUSBAND, customer.getLinkToSpouseEnum());
        assertEquals(MaritalStatus.MARRIED_SPOUSE_PRESENT, customer.getMaritalStatusEnum());
        assertEquals(MovedIn._21_PLUS_YEARS_AGO, customer.getMovedInCodeEnum());
        assertEquals(0, customer.getNonFarmBusinessIncome());
        assertEquals(0, customer.getNumChildrenInHouse());
        assertEquals(ChildrenUnderFive.NO_CHILDREN, customer.getNumChildrenUnderFiveInHouseEnum());
        assertEquals(1, customer.getNumCouplesInHouse());
        assertEquals(1, customer.getNumFamiliesInHouse());
        assertEquals(2, customer.getNumFamilyMembersInHouse());
        assertEquals(0, customer.getNumFathersInHouse());
        assertEquals(0, customer.getNumMothersInHouse());
        assertEquals(0, customer.getNumSiblingsInHouse());
        assertEquals(23, customer.getOccupationIncomeScore());
        assertEquals(448, customer.getOtherIncome());
        assertEquals(Ownership.OWNED, customer.getOwnershipEnum());
        assertEquals(501, customer.getPovertyStatus());
        assertEquals(Race.White, customer.getRaceEnum());
        assertEquals(RelationWHeadHouse.HOUSEHOLDER, customer.getRelationshipWHouseholderEnum());
        assertEquals(0, customer.getRent());
        assertTrue("N/A".equalsIgnoreCase(customer.getResidence5YearsAgo().trim()));
        assertEquals(ResidentialStatus.SAME_HOUSE, customer.getResidentialStatusEnum());
        assertEquals(SchoolStatus.NO, customer.getSchoolStatusEnum());
        assertEquals(SchoolType.NOT_ENROLLED, customer.getSchoolTypeEnum());
        assertEquals(0, customer.getSocialSecurityIncome());
        assertEquals(18, customer.getSocioEconomicIndex());
        assertEquals(47648, customer.getTotalFamilyIncome());
        assertEquals(39448, customer.getTotalPersonalIncome());
        assertEquals(36000, customer.getWageAndSalaryIncome());
        assertEquals(0, customer.getWelfareIncome());
        assertEquals(162500, customer.getValue());
        assertEquals(VeteranStatus.NO_SERVICE, customer.getVeteranStatusEnum());
        assertEquals(WorkingClass.WAGE_OR_SALARY, customer.getWorkClassEnum());
        assertEquals(WorkHours._41_TO_48_HRS, customer.getWorkHoursEnum());
        assertEquals(WeeksWorkedPerYear._40_TO_47_WEEKS, customer.getWeeksWorkedPerYearEnum());
        assertTrue("Tennessee".equalsIgnoreCase(customer.getBirthplace().trim()));

    }

}