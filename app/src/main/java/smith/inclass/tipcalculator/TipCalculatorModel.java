package smith.inclass.tipcalculator;

public class TipCalculatorModel {
    double subtotal;
    double total;
    double tipPercent;

    public TipCalculatorModel(){
        subtotal = 0;
        total = 0;
        tipPercent = 0;
    }

    public void setSubtotal(double st)
    {
        subtotal = st;
    }

    public void setTipPercent(double tp)
    {
        tipPercent = tp;
    }

    public double getTotal()
    {
        return subtotal + (subtotal * tipPercent);
    }
}
