package pp5.kalkulator.logger;

import pp5.kalkulator.CalculationResult;
import pp5.kalkulator.CalculationType;

/**
 * @author Adrian Michalski
 */
interface CalculationResultLogger {

    void save(CalculationResult pCalculationResult);

    CalculationResult read(String pCode);

}
