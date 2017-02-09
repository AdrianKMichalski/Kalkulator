package pp5.kalkulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pp5.kalkulator.logger.CSVCalculationResultLogger;
import pp5.kalkulator.logger.CalculationResultLogger;

/**
 * @author Adrian Michalski
 */
public class ConsoleApplication {

    private static final Logger log = LoggerFactory.getLogger("ConsoleApplication");

    public static void main(String[] args) {
        CalculationResultLogger resultLogger = new CSVCalculationResultLogger();
        Calculator calculator = new Calculator(resultLogger);

        CalculationResult result = calculator.calculate(CalculationType.NETTO_TO_BRUTTO, 2000.0);
        log.info("Got " + result);

        CalculationResult resultFromLogger = resultLogger.read("dab5f767dfda");
        log.info("Got from logger " + resultFromLogger);
    }

}
