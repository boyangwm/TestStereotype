package durbodax.customers.Enums;

import java.util.Map;
import java.util.HashMap;
import java.util.EnumSet;

/**
 *
 * @author Kirk Seddon
 */
public enum Ownership { 
    
    //See OWNERSHIP Table
    NA(0), 
    OWNED(1), 
    RENTED(2);
    
    private static final Map<Integer, Ownership> lookup = new HashMap<Integer, Ownership>();
    private int code;
    
    static {
        
        for(Ownership owner : EnumSet.allOf(Ownership.class)) {
            
            lookup.put(owner.getCode(), owner);
            
        }
        
    }
    
    private Ownership(int code) {
        
        this.code = code;
        
    }
    
    public int getCode() {
        
        return code;
        
    }
    
    public static Ownership getOwnershipStatus(int code) {
     
        return lookup.get(code);
        
    }

}
