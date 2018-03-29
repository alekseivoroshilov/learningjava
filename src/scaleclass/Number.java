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

    Number fromString(String string) {
        ArrayList<String> form = new ArrayList<String>();
        Collections.addAll(form, string.split(" "));
        Number number = new Number();
        number.amount = Double.parseDouble(form.get(0));
        number.dimensionName = form.get(1);
        return number;
    }

    String toString(Number number) {
        return (number.amount + " " + number.dimensionName);
    }

    void add(Number number) {
        MeasurementSystem measurement = new MeasurementSystem();
        measurement.init();
        amount += number.amount;
        measurement.recalculate(this);
    }

    void subtraction(Number number) {
        MeasurementSystem measurement = new MeasurementSystem();
        measurement.init();
        amount -= number.amount;
        measurement.recalculate(this);
    }

    void multiplication(double multiplier) {
        MeasurementSystem measurement = new MeasurementSystem();
        measurement.init();
        amount *= multiplier;
        measurement.recalculate(this);
    }

    Boolean compareTo(Number otherNumber) {
        return this.equals(otherNumber);
    }

    void divisionInto(double divider) {
        MeasurementSystem measurement = new MeasurementSystem();
        measurement.init();
        amount /= divider;
        measurement.recalculate(this);
    }
    //если рекомендуют переопределять, то я буду переопределять под свой класс
    @Override
    public boolean equals(Object otherObj) {
        if (otherObj == this) {
            return true;
        }
        if (otherObj == null || otherObj.getClass() != this.getClass()) {
            return false;
        }

        Number otherNumber = (Number) otherObj;
        /*
        * amount == otherNumber.amount <==== false
        * */
        return amount.equals(otherNumber.amount) && dimensionName.equals(otherNumber.getdimensionName());
    }

    /*@Override
    public int hashCode() {  !!//как использовать hashCode, если значения Double? parseDouble?
        final int prime = 31; //быстро умножается процессором. да и все его используют.
        int result = 1;
        result = prime * result + ((dimensionName == null) ? 0 : dimensionName.hashCode());
        result = prime * result + amount;
        return result;
    }А как лучше сравнивать в этом случае? Оставлю этот вопрос на встречу с вами.*/
}