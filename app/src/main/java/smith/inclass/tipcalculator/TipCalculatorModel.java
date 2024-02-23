package smith.inclass.tipcalculator;

public class TipCalculatorModel {
    double subtotal;
    double total;
    double tipPercent;
    public TipCalculatorModel(){
        subtotal = 0;
        total = 0;
        setTipPercent(Defaults.DEFAULT_TIP_PERCENT);
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

    public double getTip() {
        return subtotal * tipPercent;
    }
}
