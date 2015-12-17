package durbodax.customers.Enums;

import java.util.Map;
import java.util.HashMap;
import java.util.EnumSet;

/**
 *
 * @author Kirk Seddon
 */
public enum Farm {

    //See Farm Table
    FARM(2), 
    NON_FARM(1);
    
    private static final Map<Integer, Farm> lookup = new HashMap<Integer, Farm>();
    private int code;
    
    static {
        
        for(Farm farm : EnumSet.allOf(Farm.class)) {
            
            lookup.put(farm.getCode(), farm);
            
        }
        
    }
    
    private Farm(int code) {
        
        this.code = code;
        
    }
    
    public int getCode() {
        
        return code;
        
    }
    
    public static Farm getStatus(int code) {
     
        return lookup.get(code);
        
    }
    
}
