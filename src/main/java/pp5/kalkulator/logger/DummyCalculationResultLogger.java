package pp5.kalkulator.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pp5.kalkulator.CalculationResult;
import pp5.kalkulator.CalculationType;

/**
 * @author Adrian Michalski
 */
public class DummyCalculationResultLogger implements CalculationResultLogger {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void save(CalculationResult pCalculationResult) {
        log.info("Saved " + pCalculationResult);
    }

    @Override
    public CalculationResult read(String pCode) {
        return new CalculationResult(CalculationType.NETTO_TO_BRUTTO, 5000.0, 6000.0);
    }
}
