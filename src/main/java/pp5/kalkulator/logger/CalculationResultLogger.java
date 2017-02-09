package pp5.kalkulator.logger;

import pp5.kalkulator.Calculation;

/**
 * @author Adrian Michalski
 */
public interface CalculationResultLogger {

    String save(Calculation pCalculation);

    Calculation read(String pCode);

}
