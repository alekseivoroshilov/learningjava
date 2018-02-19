class Number {
    private double amount;
    private String scaleName;
    private Number(){
        amount = 0.0;
        scaleName = "meters";
    }
    private Number createNumber(double amount, String scaleName){
        Number creation = new Number();
        creation.amount = amount;
        creation.scaleName = scaleName;
        return creation;
    }
    public Number sum(Number otherNumber){
        if (this.scaleName == otherNumber.scaleName){
        return createNumber(amount = this.amount + otherNumber.amount, this.scaleName);
        } else{
            System.out.println("Operation's result is null, because you gave the wrong scaleName");
            return null;
        }
    }
    public Number multiplication(double multiplier){
        return createNumber(this.amount * multiplier, this.scaleName);
    }
    public Boolean compareTo(Number otherNumber)
    {
        if ((this.scaleName != otherNumber.scaleName) || (this.amount != otherNumber.amount))
            return false;
        else
            return true;
    }
    public Number divisionInto(double divider){
        return createNumber(this.amount / divider, this.scaleName);
    }
}
