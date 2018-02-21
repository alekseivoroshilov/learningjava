public class Number {
    public double amount;
    //public double subamount;
    public String scaleName;

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

    public static  Number createNumber(double amount, String scaleName) {
        Number creation = new Number();
        creation.amount = amount;
        //creation.subamount = subamount;
        creation.scaleName = scaleName;
        return creation;
    }
}
