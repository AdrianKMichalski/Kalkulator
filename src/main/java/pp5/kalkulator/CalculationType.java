package pp5.kalkulator;

/**
 * @author Adrian Michalski
 */
public enum CalculationType {
    NETTO_TO_BRUTTO("Kwota netto do brutto", "%.2f zł netto to %.2f zł brutto"),
    BRUTTO_TO_NETTO("Kwota brutto do netto", "%.2f zł brutto to %.2f zł netto"),
    BRUTTO_TO_EMPLOYER_COSTS("Kwota brutto do kosztów pracodawcy", "Przy %.2f zł brutto koszty pracodawcy wynoszą %.2f zł");

    private String description;
    private String resultFormat;

    CalculationType(String pDescription, String pResultFormat) {
        description = pDescription;
        resultFormat = pResultFormat;
    }

    public String getDescription() {
        return description;
    }

    public String getResultFormat() {
        return resultFormat;
    }
}
