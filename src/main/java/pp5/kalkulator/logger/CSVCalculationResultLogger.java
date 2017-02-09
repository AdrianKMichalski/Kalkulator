package pp5.kalkulator.logger;

import com.google.common.collect.ImmutableList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import pp5.kalkulator.CalculationResult;
import pp5.kalkulator.CalculationType;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * @author Adrian Michalski
 */
public class CSVCalculationResultLogger extends MD5HashGenerator implements CalculationResultLogger {

    private static final String CSV_RESULTS_RESOURCE = "csv/results.csv";

    File csvFile;

    public CSVCalculationResultLogger() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(CSV_RESULTS_RESOURCE);
        csvFile = new File(resource.getFile());
    }

    @Override
    public String save(CalculationResult pCalculationResult) {

        String code = generateHash();

        try {
            FileWriter fileWriter = new FileWriter(csvFile, true);
            CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);

            List<String> resultRecord = ImmutableList.of(
                    code,
                    pCalculationResult.getCalculationType().name(),
                    String.valueOf(pCalculationResult.getInput()),
                    String.valueOf(pCalculationResult.getOutput())
            );

            csvPrinter.printRecord(resultRecord);

            fileWriter.flush();
            fileWriter.close();
            csvPrinter.close();
        } catch (IOException pE) {
            pE.printStackTrace();
        }

        return code;
    }

    @Override
    public CalculationResult read(String pCode) {
        try {
            FileReader fileReader = new FileReader(csvFile);
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);

            return csvParser.getRecords().stream()
                    .filter(record -> record.get(0).equals(pCode))
                    .findFirst()
                    .map(record -> new CalculationResult(
                            CalculationType.valueOf(record.get(1)),
                            Double.parseDouble(record.get(2)),
                            Double.parseDouble(record.get(3))
                    )).orElse(null);
        } catch (IOException pE) {
            pE.printStackTrace();
        }
        return null;
    }
}
