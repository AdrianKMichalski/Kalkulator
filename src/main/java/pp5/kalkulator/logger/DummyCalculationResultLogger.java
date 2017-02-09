package pp5.kalkulator.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pp5.kalkulator.Calculation;
import pp5.kalkulator.CalculationType;

/**
 * @author Adrian Michalski
 */
public class DummyCalculationResultLogger implements CalculationResultLogger {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String save(Calculation pCalculation) {
        log.info("Saved " + pCalculation);
        return "ABC123";
    }

    @Override
    public Calculation read(String pCode) {
        Calculation calculation = new Calculation(CalculationType.NETTO_TO_BRUTTO, 5000.0, 5500.0);
        log.info("Reading " + calculation);
        return calculation;
    }
}
