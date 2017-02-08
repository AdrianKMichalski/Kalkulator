package pp5.kalkulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pp5.kalkulator.logger.CalculationResultLogger;

/**
 * @author Adrian Michalski
 */
public class Calculator {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CalculationResultLogger resultLogger;

    public Calculator(CalculationResultLogger pResultLogger) {
        resultLogger = pResultLogger;
    }

    public CalculationResult calculate() {
        CalculationResult calculationResult = new CalculationResult(null, 0.0, 0.0); // FIXME
        resultLogger.save(calculationResult);
        return calculationResult;
    }
}
