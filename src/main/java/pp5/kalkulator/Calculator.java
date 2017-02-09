package pp5.kalkulator;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pp5.kalkulator.logger.CalculationResultLogger;

import java.util.function.Function;

/**
 * @author Adrian Michalski
 */
public class Calculator {

    // Calculations based on casual work contract for students
    private static final Function<Double, Double> NETTO_TO_BRUTTO_FUNCTION = netto -> netto / 0.856;
    private static final Function<Double, Double> BRUTTO_TO_NETTO_FUNCTION = brutto -> brutto * 0.856;
    private static final Function<Double, Double> BRUTTO_TO_EMPLOYER_COSTS_FUNCTION = brutto -> brutto;

    private static final ImmutableMap<CalculationType, Function<Double, Double>> CALCULATION_TYPE_TO_FUNCTION =
            new ImmutableMap.Builder<CalculationType, Function<Double, Double>>()
                    .put(CalculationType.NETTO_TO_BRUTTO, NETTO_TO_BRUTTO_FUNCTION)
                    .put(CalculationType.BRUTTO_TO_NETTO, BRUTTO_TO_NETTO_FUNCTION)
                    .put(CalculationType.BRUTTO_TO_EMPLOYER_COSTS, BRUTTO_TO_EMPLOYER_COSTS_FUNCTION)
                    .build();

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CalculationResultLogger resultLogger;

    public Calculator(CalculationResultLogger pResultLogger) {
        resultLogger = pResultLogger;
    }

    public CalculationResult calculate(CalculationType pCalculationType, double pInput) {
        Double calculationOutput = CALCULATION_TYPE_TO_FUNCTION.get(pCalculationType).apply(pInput);
        CalculationResult calculationResult = new CalculationResult(pCalculationType, pInput, calculationOutput);
        resultLogger.save(calculationResult); // TODO: wyprowadzić kod kalkulacji na wierzch
        return calculationResult;
    }
}
