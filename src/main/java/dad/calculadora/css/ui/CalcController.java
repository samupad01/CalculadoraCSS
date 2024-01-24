package dad.calculadora.css.ui;

import dad.calculadora.css.Calculadora;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class CalcController {

    // model
    private Calculadora calculadora = new Calculadora();

    // view
    @FXML
    private GridPane view;

    @FXML
    private TextField screenText;


    public CalcController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CalcView.fxml"));
        loader.setController(this);
        loader.load();
    }

    @FXML
    private void initialize() {
        screenText.textProperty().bind(calculadora.screenProperty());

        // Crear el menú contextual
        ContextMenu contextMenu = new ContextMenu();

        // Opciones de estilo
        MenuItem volverOriginalItem = new MenuItem("Volver al Estado Original");
        MenuItem estiloClasicoItem = new MenuItem("Estilo Clásico");
        MenuItem estiloModernoItem = new MenuItem("Estilo Moderno");
 
        volverOriginalItem.setOnAction(event -> cambiarEstilo("/css/original.css"));
        estiloClasicoItem.setOnAction(event -> cambiarEstilo("/css/style_clasica.css"));
        estiloModernoItem.setOnAction(event -> cambiarEstilo("/css/style_moderna.css"));

        // Agregar opciones al menú
        contextMenu.getItems().addAll(volverOriginalItem, estiloClasicoItem, estiloModernoItem);

        // Asociar el menú contextual al nodo principal (en este caso, el GridPane)
        view.setOnContextMenuRequested(event -> contextMenu.show(view, event.getScreenX(), event.getScreenY()));
    }

    private void cambiarEstilo(String estilo) {
        view.getStylesheets().clear();
        view.getStylesheets().add(getClass().getResource(estilo).toExternalForm());
    }

    @FXML
    private void onOperationButtonHandle(ActionEvent e) {
        String texto = ((Button) e.getSource()).getText();
        if (texto.equals("CE")) {
            calculadora.cleanEverything();
        } else if (texto.equals("C")) {
            calculadora.clean();
        } else if (texto.equals("+/-")) {
            calculadora.negate();
        } else {
            calculadora.operate(texto.charAt(0));
        }
    }

    @FXML
    private void onCommaButtonHandle(ActionEvent e) {
        calculadora.insertComma();
    }

    @FXML
    private void onNumberButtonHandle(ActionEvent e) {
        String texto = ((Button) e.getSource()).getText();
        calculadora.insert(texto.charAt(0));
    }

    public GridPane getView() {
        return view;
    }

}

