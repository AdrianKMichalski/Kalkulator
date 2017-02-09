package pp5.kalkulator;

/**
 * @author Adrian Michalski
 */
public class Calculation {

    private CalculationType calculationType;

    private double input;

    private double output;

    public Calculation(CalculationType pCalculationType, double pInput, double pOutput) {
        calculationType = pCalculationType;
        input = pInput;
        output = pOutput;
    }

    public CalculationType getCalculationType() {
        return calculationType;
    }

    public double getInput() {
        return input;
    }

    public double getOutput() {
        return output;
    }

    @Override
    public String toString() {
        return "Calculation{" +
                "calculationType=" + calculationType +
                ", input=" + input +
                ", output=" + output +
                '}';
    }
}
