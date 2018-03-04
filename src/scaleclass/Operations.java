import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operations {

    public static Number sum(Number thisNumber, Number otherNumber){
        if (thisNumber.scaleName.equals(otherNumber.scaleName)){
            return new Number(thisNumber.amount + otherNumber.amount,
                    thisNumber.subamount + otherNumber.subamount, thisNumber.scaleName);
        } else{
            System.out.println("Operation's result is null, because you gave the wrong scaleName");
            return null;
        }
    }
    static Number subtraction(Number thisNumber, Number otherNumber){
        if (thisNumber.scaleName.equals(otherNumber.scaleName)){
            return new Number(thisNumber.amount - otherNumber.amount,
                    thisNumber.subamount - otherNumber.subamount, thisNumber.scaleName);
        } else{
            System.out.println("Operation's result is null, because you gave the wrong scaleName");
            return null;
        }
    }
    public static void multiplication(Number thisNumber, double multiplier){
        thisNumber.amount *= multiplier;
        thisNumber.subamount *= multiplier;
        Number.recalculate(thisNumber);
    }
    public static Boolean compareTo(Number thisNumber, Number otherNumber) {
        Number.recalculate(thisNumber);
        Number.recalculate(otherNumber);
        return (!(!thisNumber.scaleName.equals(otherNumber.scaleName) || (thisNumber.amount != otherNumber.amount)
                || (thisNumber.subamount != otherNumber.subamount)));
    }
    public static void divisionInto(Number thisNumber, double divider){
        thisNumber.amount /= divider;
        thisNumber.subamount /= divider;
        Number.recalculate(thisNumber);
    }
}
