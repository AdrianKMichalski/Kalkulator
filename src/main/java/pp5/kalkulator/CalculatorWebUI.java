package pp5.kalkulator;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import pp5.kalkulator.logger.CalculationResultLogger;
import pp5.kalkulator.logger.PostgreSQLCalculationResultLogger;

import javax.servlet.annotation.WebServlet;
import java.util.Arrays;
import java.util.List;

/**
 * @author Adrian Michalski
 */
@Theme("valo")
public class CalculatorWebUI extends UI {

    private final CalculationResultLogger resultLogger;
    private final Calculator calculator;

    private Panel inputPanel;
    private Panel resultPanel;

    public CalculatorWebUI() {
        resultLogger = new PostgreSQLCalculationResultLogger();
        calculator = new Calculator(resultLogger);
    }

    protected void init(VaadinRequest pVaadinRequest) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        inputPanel = initInputPanel();
        resultPanel = initResultPanel();

        horizontalLayout.addComponents(inputPanel, resultPanel);
        horizontalLayout.setMargin(true);
        horizontalLayout.setSpacing(true);

        setContent(horizontalLayout);
    }

    private Panel initResultPanel() {
        Panel panel = new Panel("Calculation result");
        panel.setVisible(false);
        return panel;
    }

    private Panel initInputPanel() {
        Panel panel = new Panel("Calculate your earnings");
        panel.setSizeUndefined();

        FormLayout panelContent = new FormLayout();
        panelContent.setMargin(true);

        TextField inputTextField = new TextField("Your earnings");
        panelContent.addComponent(inputTextField);

        OptionGroup conversionOptions = createConversionOptions();
        panelContent.addComponent(conversionOptions);

        panelContent.addComponent(createCalculationButton(inputTextField, conversionOptions));

        panelContent.addComponent(createCodePopup());

        panel.setContent(panelContent);
        return panel;
    }

    private Button createCalculationButton(TextField pInputTextField, OptionGroup pConversionOptions) {
        Button calculateButton = new Button("Calculate");

        calculateButton.addClickListener(clickEvent -> {
            FormLayout resultContent = new FormLayout();
            resultContent.setMargin(true);

            CalculationType selectedCalculationType = (CalculationType) pConversionOptions.getValue();

            CalculationResult calculationResult = calculator.calculate(selectedCalculationType, Double.parseDouble(pInputTextField.getValue()));
            String niceResult = getNiceResult(calculationResult.getCalculation());

            resultContent.addComponent(new Label(niceResult));
            resultContent.addComponent(new Label("Your calculation code: <b>" + calculationResult.getCode() + "</b>", ContentMode.HTML));

            resultPanel.setContent(resultContent);
            resultPanel.setVisible(true);
        });
        return calculateButton;
    }

    private OptionGroup createConversionOptions() {
        OptionGroup conversionOptions = new OptionGroup("Calculation type");

        List<CalculationType> availableCalculationTypes = Arrays.asList(CalculationType.values());
        conversionOptions.addItems(availableCalculationTypes);
        return conversionOptions;
    }

    private PopupView createCodePopup() {
        VerticalLayout codePopupContent = new VerticalLayout();
        TextField calculationCodeTextField = new TextField("Enter calculation code");
        codePopupContent.addComponent(calculationCodeTextField);
        Button resultByCodeButton = new Button("Get result");
        resultByCodeButton.addClickListener(clickEvent -> {
            FormLayout resultContent = new FormLayout();
            resultContent.setMargin(true);

            Calculation calculation = resultLogger.read(calculationCodeTextField.getValue());
            String formattedMessage = getNiceResult(calculation);
            resultContent.addComponent(new Label(formattedMessage));

            resultPanel.setContent(resultContent);
            resultPanel.setVisible(true);
        });
        codePopupContent.addComponent(resultByCodeButton);
        codePopupContent.setMargin(true);
        return new PopupView("Or enter calculation code here", codePopupContent);
    }

    private String getNiceResult(Calculation pCalculation) {
        return String.format(pCalculation.getCalculationType().getResultFormat(), pCalculation.getInput(), pCalculation.getOutput());
    }

    @WebServlet(urlPatterns = "/*", name = "CalculatorUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = CalculatorWebUI.class, productionMode = false)
    public static class CalculatorUIServlet extends VaadinServlet {

    }
}
