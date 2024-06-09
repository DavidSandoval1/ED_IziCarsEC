/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package principal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CrearVehiculoController implements Initializable {

    @FXML
    private TextField txtFPropietario;
    @FXML
    private TextField txtFMarca;
    @FXML
    private TextField txtFModelo;
    @FXML
    private TextField txtFPrecio;
    @FXML
    private TextField txtFKm;
    @FXML
    private TextField txtFUbicacion;
    @FXML
    private TextField txtFAnio;
    @FXML
    private TextField txtFMotor;
    @FXML
    private TextField txtFTransmision;
    @FXML
    private TextField txtFPeso;
    @FXML
    private TextField txtFlblHistA;
    @FXML
    private TextField txtFHistM;
    @FXML
    private Button btnCrear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void btnCrear(ActionEvent event) {
    }
    
}
