import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operations {

    public static Number sum(Number thisNumber, Number otherNumber){
        if (thisNumber.scaleName.equals(otherNumber.scaleName)){
            return new Number(thisNumber.amount + otherNumber.amount,
                    thisNumber.scaleName);
        } else{
            System.out.println("Operation's result is null, because you gave the wrong scaleName");
            return null;
        }
    }
    static Number subtraction(Number thisNumber, Number otherNumber){
        if (thisNumber.scaleName.equals(otherNumber.scaleName)){
            return new Number(thisNumber.amount - otherNumber.amount,
                    thisNumber.scaleName);
        } else{
            System.out.println("Operation's result is null, because you gave the wrong scaleName");
            return null;
        }
    }
    public static Number multiplication(Number thisNumber, double multiplier){
        return Number.createNumber(thisNumber.amount * multiplier, thisNumber.scaleName);
    }
    public static Boolean compareTo(Number thisNumber, Number otherNumber)
    {
        return (!(!thisNumber.scaleName.equals(otherNumber.scaleName) || (thisNumber.amount != otherNumber.amount)));
    }
    public static Number divisionInto(Number thisNumber, double divider){
        return Number.createNumber(thisNumber.amount / divider, thisNumber.scaleName);
    }
}
