package pp5.kalkulator;

/**
 * @author Adrian Michalski
 */
public class CalculationResult {

    private String code;

    private Calculation calculation;

    public CalculationResult(String pCode, Calculation pCalculation) {
        code = pCode;
        calculation = pCalculation;
    }

    public String getCode() {
        return code;
    }

    public Calculation getCalculation() {
        return calculation;
    }

}
