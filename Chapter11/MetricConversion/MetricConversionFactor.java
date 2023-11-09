package MetricConversion;

public class MetricConversionFactor {
    public String fromSingular, toSingular, fromPlural, toPlural;
    public double conversionFactor;

    public MetricConversionFactor(String fromS, String toS, String fromP, String toP, double conversionFactor) {
        this.fromSingular = fromS;
        this.toSingular = toS;
        this.fromPlural = fromP;
        this.toPlural = toP;
        this.conversionFactor = conversionFactor;
    }

    public String convert(double fromValue) {
        String output = Double.toString(fromValue);

        if (fromValue == 1.0) {
            output += " " + this.fromSingular;
        } else {
            output += " " + this.fromPlural;
        }

        output += " = " + Double.toString(fromValue * conversionFactor);

        if (fromValue * conversionFactor == 1.0) {
            output += " " + this.toSingular;
        } else {
            output += " " + this.toPlural;
        }

        return output;
    }

    public String getName() {
        return this.fromPlural + " to " + this.toPlural;
    }
}
