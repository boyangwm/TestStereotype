package durbodax.customers.Enums;

import java.util.Map;
import java.util.HashMap;
import java.util.EnumSet;

/**
 *
 * @author Kirk Seddon
 */
public enum SchoolType {

    //See SCHLTYPE Table
    NA(0),
    NOT_ENROLLED(1),
    PUBLIC_SCHOOL(2),
    PRIVATE_SCHOOL(3),
    CHURCH_RELATED(4),
    PAROCHIAL(5),
    OTHER_PRIVATE_1980(6),
    OTHER_PRIVATE_1970(7);
                                                                                     
    private static final Map<Integer, SchoolType> lookup = new HashMap<Integer, SchoolType>();
    private int code;
    
    static {
        
        for(SchoolType schoolType : EnumSet.allOf(SchoolType.class)) {
            
            lookup.put(schoolType.getCode(), schoolType);
            
        }
        
    }
    
    private SchoolType(int code) {
        
        this.code = code;
        
        
    }
    
    public int getCode() {
        
        return code;
        
    }
    
    public static SchoolType getSchoolType(int code) {
     
        return lookup.get(code);
        
    }

}
