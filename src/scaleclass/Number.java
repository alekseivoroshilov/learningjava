import java.util.ArrayList;
import java.util.Collections;

class Number {
    double amount;
    //public double subamount;
    String scaleName;

    Number() {
        amount = 0.0;
        //subamount = 0;
        scaleName = "meters";
    }
    Number(double amount, String scaleName) {
        this.amount = amount;
        //this.subamount = subamount;
        this.scaleName = scaleName;
    }

    static Number createNumber(double amount, String scaleName) {
        Number creation = new Number();
        creation.amount = amount;
        //creation.subamount = subamount;
        creation.scaleName = scaleName;
        return creation;
    }
    static Number fromString (String string){
        ArrayList<String> form = new ArrayList<String>();
        Collections.addAll(form, string.split(" "));
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
    static String toString (Number number){
        return number.amount + " " + number.scaleName;
    }
}
