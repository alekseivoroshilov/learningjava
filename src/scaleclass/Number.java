import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

class Number {
    double amount;
    public double subamount;
    String scaleName;
    private static Map<String,String> mapOfScales = new HashMap<String,String>();
    public static void init(){
        mapOfScales.put("m", "cm");
        mapOfScales.put("h", "min");
    }
    private Number() {
        amount = 0.0;
        subamount = 0.0;
        scaleName = "m";
    }
    Number(double amount , double subamount, String scaleName) {
        this.amount = amount;
        this.subamount = subamount;
        this.scaleName = scaleName;
    }

    static void recalculate(Number number){
        switch(number.scaleName){
            case "m":
                while (number.subamount >= 100){
                    ++number.amount;
                    number.subamount -= 100;
                }
                if (number.amount % 1.0 != 0.0){
                    number.subamount = (number.amount % 1.0) * 100;
                    number.amount -= number.amount % 1.0;
                }
            case "h":
                while (number.subamount >= 60){
                    ++number.amount;
                    number.subamount -= 60;
                }
                if (number.amount % 1.0 != 0.0){
                    number.subamount = (number.amount % 1.0) * 60;
                    number.amount -= number.amount % 1.0;
                }
        }
        if (number.scaleName.equals("m")){
            double sum = number.amount + (number.subamount / 100.0);
            if (sum % 1.0 != 0.0){
                number.subamount = (number.amount % 1.0) * 100.0;
                number.amount -= number.amount % 1.0;
            }
        } else if (number.scaleName.equals("h")){
            double sum = number.amount + (number.subamount / 60.0);
            if (sum % 1.0 != 0.0){
                number.subamount = (number.amount % 1.0) * 60.0;
                number.amount -= number.amount % 1.0;
            }
        }
        if ((number.amount == 0.0) && (Math.abs(number.subamount) > 0.0))
            number.scaleName = mapOfScales.get(number.scaleName);
    }
    static Number fromString (String string){
        ArrayList<String> form = new ArrayList<String>();
        Collections.addAll(form, string.split(" "));
        Number number = new Number();
        try {
            number.amount = Double.parseDouble(form.get(0));
        }
        catch (IllegalArgumentException e){
            System.out.println("Please follow the expected form: \"");
        }
        number.scaleName = form.get(1);
        if (form.size() == 4) {
            String subamountScaleName = form.get(3);
            if (!mapOfScales.containsKey(subamountScaleName) ||
                    !mapOfScales.get(form.get(1)).equals(subamountScaleName)) {
                System.out.println("Wrong number. It is saved as:" + number.amount + " " + number.scaleName);
                return number;
            }
            number.subamount = Double.parseDouble(form.get(2));
            recalculate(number);
        }

        if (mapOfScales.containsValue(number.scaleName)){
            if (number.scaleName.equals("cm")) {
                number.scaleName = "m";
                number.amount /= 100.0;
            } else
                if (number.scaleName.equals("min")) {
                    number.scaleName = "h";
                    number.amount /= 60.0;
                }
        }
        return number;
    }
    static String toString (Number number){
        if (number.scaleName.equals("m")){
            double sum = number.amount + (number.subamount / 100.0);
            if (Math.abs(sum) >= 1.0) return sum + " " + number.scaleName;
                else return sum * 100.0 + " " + mapOfScales.get(number.scaleName);
        } else if (number.scaleName.equals("h")){
            double sum = number.amount + (number.subamount / 60.0);
            if (Math.abs(sum) >= 1.0) return sum + " " + number.scaleName;
                else return sum * 60.0 + " " + mapOfScales.get(number.scaleName);
        } else if (number.subamount == 0.0)
            return number.amount + " " + number.scaleName;
          else return number.amount + " " + number.scaleName + " "
                    + number.subamount + " " + mapOfScales.get(number.scaleName);
    }
}
