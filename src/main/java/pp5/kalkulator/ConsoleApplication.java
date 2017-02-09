package pp5.kalkulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pp5.kalkulator.logger.CalculationResultLogger;
import pp5.kalkulator.logger.PostgreSQLCalculationResultLogger;

import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author Adrian Michalski
 */
public class ConsoleApplication {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CalculationResultLogger resultLogger = new PostgreSQLCalculationResultLogger();
    private final Calculator calculator = new Calculator(resultLogger);

    private ConsoleApplication() {
        printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Wybierz rodzaj obliczeń:");
        Stream.of(CalculationType.values())
                .map(singleEnum -> "" + singleEnum.ordinal() + ". " + singleEnum.getDescription())
                .forEach(System.out::println);
        System.out.println(CalculationType.values().length + ". Odczytaj wynik z kodu");

        int calculationType = scanner.nextInt();
        if (calculationType < 0 || calculationType > CalculationType.values().length) {
            return;
        } else if (calculationType == CalculationType.values().length) {
            scanner.nextLine();
            System.out.println("Wprowadź kod:");
            String code = scanner.nextLine().trim().toLowerCase();
            CalculationResult resultFromLogger = resultLogger.read(code);
            if (resultFromLogger != null) {
                printNiceResult(resultFromLogger);
            } else {
                System.out.println("Nie znaleziono takiego kodu");
            }
        } else {
            System.out.println("Podaj kwotę:");
            double amount = scanner.nextDouble();
            CalculationResult result = calculator.calculate(CalculationType.values()[calculationType], amount);
            printNiceResult(result);
        }
    }

    public static void main(String[] args) {
        new ConsoleApplication();
    }

    private void printNiceResult(CalculationResult pResult) {
        System.out.format(pResult.getCalculationType().getResultFormat(),
                pResult.getInput(),
                pResult.getOutput());
    }

    private void printWelcomeMessage() {
        System.out.println("Kalkulator wynagrodzeń");
        System.out.println("Kamil Maj & Adrian Michalski\n");
    }

}
