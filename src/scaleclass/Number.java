import java.util.ArrayList;
import java.util.Collections;


class Number {
    private Double amount;
    private String dimensionName;

    public String getdimensionName() {
        return dimensionName;
    }
    public Double getAmount(){
        return amount;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }
    public void setdimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    Number() {
        amount = 0.0;
        dimensionName = "";
    }

    Number(Double amount, String dimensionName) {
        this.amount = amount;
        this.dimensionName = dimensionName;
    }

    /*private static void recalculate(Number number) { ///////////////////////IN PROCESS//////////////////////
        double sum = number.amount + (number / 100.0);
        number.dimensionName.equals();
        switch (number.dimensionName) {
            case MiasurementSystem.:
                if (sum % 1.0 != 0.0) {
                    number.subamount = (sum % 1.0) * 100.0;
                    number.amount = sum - sum % 1.0;
                }
            case "h":
                sum = number.amount + (number.subamount / 60.0);
                if (sum % 1.0 != 0.0) {
                    number.subamount = (sum % 1) * 60.0;
                    number.amount = sum - sum % 1.0;
                }
        }
        if ((number.amount == 0.0) && (Math.abs(number.subamount) > 0.0))
            number.dimensionName = mapOfScales.get(number.dimensionName);
    }*/

    static Number fromString(String string) {
        ArrayList<String> form = new ArrayList<String>();
        Collections.addAll(form, string.split(" "));
        Number number = new Number();
        number.amount = Double.parseDouble(form.get(0));
        number.dimensionName = form.get(1);
        /*if (form.size() == 4) {
            String subamountdimensionName = form.get(3);
            if (!mapOfScales.containsKey(subamountdimensionName) ||
                    !mapOfScales.get(form.get(1)).equals(subamountdimensionName)) {
                System.out.println("Wrong number. It is saved as:" + number.amount + " " + number.dimensionName);
                return number;
            }
            recalculate(number);
        }*/
        return number;
    }

    static String toString(Number number) {
        switch (number.dimensionName) {
            case "m": {
                double sum = number.amount + (number.subamount / 100.0);
                if (Math.abs(sum) >= 1.0) return sum + " " + number.dimensionName;
                else return sum * 100.0 + " " + mapOfScales.get(number.dimensionName);
            }
            case "h": {
                double sum = number.amount + (number.subamount / 60.0);
                if (Math.abs(sum) >= 1.0) return sum + " " + number.dimensionName;
                else return sum * 60.0 + " " + mapOfScales.get(number.dimensionName);
            }
        }
        if (number.subamount == 0.0)
            return number.amount + " " + number.dimensionName;
        else return number.amount + " " + number.dimensionName + " "
                + number.subamount + " " + mapOfScales.get(number.dimensionName);
    }

    public Number add(Number number) {
        if (this.dimensionName.equals(number.dimensionName)) {
            return new Number(this.amount + number.amount,
                    this.subamount + number.subamount, this.dimensionName);
        } else {
            System.out.println("Operation's result is null, because you gave the wrong dimensionName");
            return null;
        }
    }

    static Number subtraction(Number thisNumber, Number otherNumber) {
        if (thisNumber.dimensionName.equals(otherNumber.dimensionName)) {
            return new Number(thisNumber.amount - otherNumber.amount,
                    thisNumber.subamount - otherNumber.subamount, thisNumber.dimensionName);
        } else {
            System.out.println("Operation's result is null, because you gave the wrong dimensionName");
            return null;
        }
    }

    public static void multiplication(Number thisNumber, double multiplier) {
        thisNumber.amount *= multiplier;
        thisNumber.subamount *= multiplier;
        System.out.println(thisNumber.amount + " " + thisNumber.subamount + " " + thisNumber.dimensionName);
        Number.recalculate(thisNumber);
        System.out.println(thisNumber.amount + " " + thisNumber.subamount + " " + thisNumber.dimensionName);
    }

    public static Boolean compareTo(Number thisNumber, Number otherNumber) {
        Number.recalculate(thisNumber);
        Number.recalculate(otherNumber);
        return (!(!thisNumber.dimensionName.equals(otherNumber.dimensionName) || (thisNumber.amount != otherNumber.amount)
                || (thisNumber.subamount != otherNumber.subamount)));
    }

    public static void divisionInto(Number thisNumber, double divider) {
        thisNumber.amount /= divider;
        thisNumber.subamount /= divider;
        Number.recalculate(thisNumber);
    }
    //если рекомендуют переопределять, то я буду переопределять под свой класс
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Number otherNumber = (Number) obj;

        return amount == otherNumber.amount && subamount == otherNumber.subamount &&(dimensionName == otherNumber.dimensionName ||
                (dimensionName != null && dimensionName.equals(otherNumber.getdimensionName())));
    }

    /*@Override
    public int hashCode() {
        final int prime = 31; //быстро умножается процессором. да и все его используют.
        int result = 1;
        result = prime * result + ((dimensionName == null) ? 0 : dimensionName.hashCode());
        result = prime * result + amount;
        result = prime * result + subamount;
        return result;
    }А как лучше сравнивать в этом случае? Оставлю этот вопрос на потом.*/
}