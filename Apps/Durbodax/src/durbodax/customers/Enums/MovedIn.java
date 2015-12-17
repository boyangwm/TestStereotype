package durbodax.customers.Enums;

import java.util.Map;
import java.util.HashMap;
import java.util.EnumSet;

/**
 *
 * @author Kirk Seddon
 */
public enum MovedIn {

    NA(0),
    THIS_OR_LAST_YEAR(1),
    TWO_YEAR_AGO(2),
    THREE_YEARS_AGO(3),
    _4_TO_6_YEARS_AGO(4),
    _7_TO_10_YEARS_AGO(5),
    _11_20_YEARS_AGO(6),
    _21_PLUS_YEARS_AGO(7),
    BLANK(8),
    ALWAYS_LIVED_HERE(9);

    private static final Map<Integer, MovedIn> lookup = new HashMap<Integer, MovedIn>();
    private int code;

    static {

        for(MovedIn moved : EnumSet.allOf(MovedIn.class)) {

            lookup.put(moved.getCode(), moved);

        }

    }

    private MovedIn(int code) {

        this.code = code;

    }

    public int getCode() {

        return code;

    }

    public static MovedIn getStatus(int code) {

        return lookup.get(code);

    }

}
