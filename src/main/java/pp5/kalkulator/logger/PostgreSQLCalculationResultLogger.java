package pp5.kalkulator.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pp5.kalkulator.CalculationResult;
import pp5.kalkulator.CalculationType;

import java.sql.*;

/**
 * @author Adrian Michalski
 */
public class PostgreSQLCalculationResultLogger extends MD5HashGenerator implements CalculationResultLogger {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String save(CalculationResult pCalculationResult) {
        Connection connection = null;
        Statement statement = null;
        String code = generateHash();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/calculator",
                    "calculator", "calculator");
            connection.setAutoCommit(false);

            log.info("Opened database successfully");

            statement = connection.createStatement();
            String sql = "INSERT INTO results (code, calculation_type, input, output) VALUES ("
                    + "'" + code + "', "
                    + "'" + pCalculationResult.getCalculationType().name() + "', "
                    + pCalculationResult.getInput() + ", "
                    + pCalculationResult.getOutput() + ");";

            statement.executeUpdate(sql);

            statement.close();
            connection.commit();
            connection.close();
        } catch (ClassNotFoundException | SQLException pE) {
            throw new RuntimeException(pE);
        }

        log.info("Records created successfully");

        return code;
    }

    @Override
    public CalculationResult read(String pCode) {
        CalculationResult result = null;

        try {
            Connection connection = null;
            Statement statement = null;
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/calculator",
                    "calculator", "calculator");
            connection.setAutoCommit(false);

            log.info("Opened database successfully");

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT calculation_type, input, output FROM results WHERE code = '" + pCode + "';");

            if (resultSet.next()) {
                String calculationType = resultSet.getString("calculation_type").trim();
                double input = resultSet.getDouble("input");
                double output = resultSet.getDouble("output");

                result = new CalculationResult(CalculationType.valueOf(calculationType), input, output);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException pE) {
            throw new RuntimeException(pE);
        }

        return result;
    }
}
