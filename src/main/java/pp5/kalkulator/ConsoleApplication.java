package pp5.kalkulator;

import pp5.kalkulator.logger.DummyCalculationResultLogger;

/**
 * @author Adrian Michalski
 */
public class ConsoleApplication {

    public static void main(String[] args) {
        DummyCalculationResultLogger resultLogger = new DummyCalculationResultLogger();
        resultLogger.read("ABC123");
    }
    
}
