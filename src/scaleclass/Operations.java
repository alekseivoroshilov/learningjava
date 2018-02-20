public class Operations {
    public Number sum(Number thisNumber, Number otherNumber){
        if (thisNumber.scaleName == otherNumber.scaleName){
            return Number.createNumber(thisNumber.amount + otherNumber.amount, thisNumber.subamount + otherNumber.subamount,
                    thisNumber.scaleName);
        } else{
            System.out.println("Operation's result is null, because you gave the wrong scaleName");
            return null;
        }
    }
    public Number multiplication(Number thisNumber, double multiplier){
        return Number.createNumber(thisNumber.amount * multiplier,thisNumber.subamount * multiplier, thisNumber.scaleName);
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
