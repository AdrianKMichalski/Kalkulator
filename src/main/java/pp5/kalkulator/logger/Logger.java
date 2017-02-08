package pp5.kalkulator.logger;

import pp5.kalkulator.CalculationType;

/**
 * @author Adrian Michalski
 */
interface Logger {

    void save(CalculationType pCalculationType, double pInput, double pOutput);

    double read(String pCode);
}
