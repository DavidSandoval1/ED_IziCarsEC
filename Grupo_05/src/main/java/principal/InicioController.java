/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package principal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author User
 */
public class InicioController implements Initializable {

    @FXML
    private VBox filtrosVBox;
    @FXML
    private ComboBox<Double> precioDesde;
    @FXML
    private ComboBox<Double> precioHasta;
    @FXML
    private ComboBox<Integer> añoDesde;
    @FXML
    private ComboBox<Integer> añoHasta;
    @FXML
    private ComboBox<String> cbMarca;
    @FXML
    private ComboBox<String> cbModelo;
    @FXML
    private ComboBox<?> kmDesde;
    @FXML
    private ComboBox<?> kmHasta;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private RadioButton rdBtnPrecio;
    @FXML
    private RadioButton rdBtnKm;
    @FXML
    private Button btnAscDesc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
