/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package durbodax.daos;

import durbodax.customers.Enums.Sex;
import java.util.Map;

/**
 *
 * @author SimmonsD
 */
public interface CustomerCompareDAO {
    public void addCondition(String condition);
    public Map<Sex,Integer> runQuery();
}
