/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package durbodax.daos;

/**
 *
 * @author SimmonsD
 */
public interface CustomerIncomeDAO {

    public int getTotalDistinctIncomes();

    public int getTotalIncomesBelowGivenIncome(int income);

}
