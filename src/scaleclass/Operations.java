import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operations {
    public Number fromString (String string){
        //for (String element: string.split(" ")){
        ArrayList<String> form = new ArrayList<String>();
        Collections.addAll(form, string.split(" "));
        //for (String str:string.split(" ")) form.add(str);
        Number number = new Number();
        try {
            number.amount = Integer.parseInt(form.get(0));
        }
        catch (IllegalArgumentException e){
            System.out.println("Please follow the expected form: \"");
        }
        number.scaleName = form.get(1);
        return number;
    }
    public Number sum(Number thisNumber, Number otherNumber){
        if (thisNumber.scaleName == otherNumber.scaleName){
            return Number.createNumber(thisNumber.amount + otherNumber.amount,
                    thisNumber.scaleName);
        } else{
            System.out.println("Operation's result is null, because you gave the wrong scaleName");
            return null;
        }
    }
    public Number multiplication(Number thisNumber, double multiplier){
        return Number.createNumber(thisNumber.amount * multiplier, thisNumber.scaleName);
    }
    public Boolean compareTo(Number thisNumber, Number otherNumber)
    {
        if ((thisNumber.scaleName != otherNumber.scaleName) || (thisNumber.amount != otherNumber.amount))
            return false;
        else
            return true;
    }
    public Number divisionInto(Number thisNumber, double divider){
        return Number.createNumber(thisNumber.amount / divider, thisNumber.scaleName);
    }

}
