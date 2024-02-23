package smith.inclass.tipcalculator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class TipChangeModel {
    double tipPercent;

    public TipChangeModel()
    {
        tipPercent = Defaults.DEFAULT_TIP_PERCENT;
    }

    public void setTipPercent(double tipPercent) {
        this.tipPercent = tipPercent;
    }

    public double getTipPercent() {
        return tipPercent;
    }

    public static double percentize(String percent) {
        // regex to extract digits from the percent string
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(percent);

        // Check if there is a match (i.e., if digits are found)
        if (matcher.find()) {
            // Extract the matched digits as a string
            String digits = matcher.group();

            // Convert the string of digits into a double value
            double value = Double.parseDouble(digits);

            // Multiply the double value by 0.01 to get the percentage value
            return value * 0.01;
        } else {
            return 0;
        }
    }
}
