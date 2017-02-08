package pp5.kalkulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pp5.kalkulator.logger.DummyCalculationResultLogger;

/**
 * @author Adrian Michalski
 */
public class ConsoleApplication {

    private static final Logger log = LoggerFactory.getLogger("ConsoleApplication");

    public static void main(String[] args) {
        DummyCalculationResultLogger resultLogger = new DummyCalculationResultLogger();
        Calculator calculator = new Calculator(resultLogger);
        CalculationResult result = calculator.calculate(CalculationType.NETTO_TO_BRUTTO, 5000.0);
        log.info("Got " + result);
    }

}
