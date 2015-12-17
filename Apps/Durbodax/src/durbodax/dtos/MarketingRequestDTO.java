/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package durbodax.dtos;

/**
 *
 * @author e0040230
 */
public class MarketingRequestDTO {

    private String topOrBottom;
    private int groupSize;
    private int incomeFrom;
    private int incomeTo;
    private int ageFrom;
    private int ageTo;
    private int maritalStatus;
    private int birthPlace;

    /**
     * @return the groupSize
     */
    public int getGroupSize() {
        return groupSize;
    }

    /**
     * @return the incomeFrom
     */
    public int getIncomeFrom() {
        return incomeFrom;
    }

    /**
     * @return the incomeTo
     */
    public int getIncomeTo() {
        return incomeTo;
    }

    /**
     * @return the ageFrom
     */
    public int getAgeFrom() {
        return ageFrom;
    }

    /**
     * @return the ageTo
     */
    public int getAgeTo() {
        return ageTo;
    }

    /**
     * @return the maritalStatus
     */
    public int getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * @return the birthPlace
     */
    public int getBirthPlace() {
        return birthPlace;
    }

    /**
     * @param groupSize the groupSize to set
     */
    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    /**
     * @param incomeFrom the incomeFrom to set
     */
    public void setIncomeFrom(int incomeFrom) {
        this.incomeFrom = incomeFrom;
    }

    /**
     * @param incomeTo the incomeTo to set
     */
    public void setIncomeTo(int incomeTo) {
        this.incomeTo = incomeTo;
    }

    /**
     * @param ageFrom the ageFrom to set
     */
    public void setAgeFrom(int ageFrom) {
        this.ageFrom = ageFrom;
    }

    /**
     * @param ageTo the ageTo to set
     */
    public void setAgeTo(int ageTo) {
        this.ageTo = ageTo;
    }

    /**
     * @param maritalStatus the maritalStatus to set
     */
    public void setMaritalStatus(int maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * @param birthPlace the birthPlace to set
     */
    public void setBirthPlace(int birthPlace) {
        this.birthPlace = birthPlace;
    }

    /**
     * @return the topOrBottom
     */
    public String getTopOrBottom() {
        return topOrBottom;
    }

    /**
     * @param topOrBottom the topOrBottom to set
     */
    public void setTopOrBottom(String topOrBottom) {
        this.topOrBottom = topOrBottom;
    }
}
