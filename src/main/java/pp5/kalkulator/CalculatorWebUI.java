package pp5.kalkulator;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;

/**
 * @author Adrian Michalski
 */
@Theme("valo")
public class CalculatorWebUI extends UI {

    @Override
    protected void init(VaadinRequest pVaadinRequest) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        Panel inputPanel = new Panel("Oblicz zarobki");

        FormLayout panelContent = new FormLayout();
        panelContent.addComponent(new TextField());
        panelContent.addComponent(new Button("Oblicz"));

        inputPanel.setContent(panelContent);

        horizontalLayout.addComponent(inputPanel);

        setContent(horizontalLayout);
    }

    @WebServlet(urlPatterns = "/*", name = "CalculatorUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = CalculatorWebUI.class, productionMode = false)
    public static class CalculatorUIServlet extends VaadinServlet {

    }
}
