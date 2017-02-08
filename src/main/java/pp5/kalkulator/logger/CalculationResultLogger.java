package pp5.kalkulator.logger;

import pp5.kalkulator.CalculationResult;

/**
 * @author Adrian Michalski
 */
public interface CalculationResultLogger {

    void save(CalculationResult pCalculationResult);

    CalculationResult read(String pCode);

}
