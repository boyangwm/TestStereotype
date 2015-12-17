package durbodax.customers.Enums;

import java.util.Map;
import java.util.HashMap;
import java.util.EnumSet;

/**
 *
 * @author Kirk Seddon
 */
public enum StepFatherStatus {

    NO_STEP_FATHER(0),
    IMPROBABLE_AGE_DIFF(1),
    SPOUSE_OF_MOTHER(2),
    IDENTIFIED_STEP_FATHER(3),
    ADOPTED(5),
    MISMATCH(6),
    SURNAME_DIFF(7);


    private static final Map<Integer, StepFatherStatus> lookup = new HashMap<Integer, StepFatherStatus>();
    private int code;

    static {

        for(StepFatherStatus father : EnumSet.allOf(StepFatherStatus.class)) {

            lookup.put(father.getCode(), father);

        }

    }

    private StepFatherStatus(int code) {

        this.code = code;


    }

    public int getCode() {

        return code;

    }

    public static StepFatherStatus getStatus(int code) {

        return lookup.get(code);

    }

}