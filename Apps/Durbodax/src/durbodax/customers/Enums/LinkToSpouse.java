package durbodax.customers.Enums;

import java.util.Map;
import java.util.HashMap;
import java.util.EnumSet;

/**
 *
 * @author Kirk Seddon
 */
public enum LinkToSpouse {

    NO_LINK(0),
    WIFE_FOLLOWS_HUSBAND(1),
    WIFE_PRECEDES_HUSBAND(2),
    CONSISTENT(3),
    WIFE_FOLLOWS_HUSBAND_ADJ(4),
    WIFE_PRECEDES_HUSBAND_ADJ(5),
    NON_ADJ(6),
    PREVIOUS_MARITAL_STATUS(7);

    private static final Map<Integer, LinkToSpouse> lookup = new HashMap<Integer, LinkToSpouse>();
    private int code;

    static {

        for(LinkToSpouse link : EnumSet.allOf(LinkToSpouse.class)) {

            lookup.put(link.getCode(), link);

        }

    }

    private LinkToSpouse(int code) {

        this.code = code;

    }

    public int getCode() {

        return code;

    }

    public static LinkToSpouse getLink(int code) {

        return lookup.get(code);

    }

}