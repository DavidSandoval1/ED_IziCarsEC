/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 *
 * @author Pc
 */
public class CarruselController implements Initializable  {
    
    @FXML
    private Button btNextCar;
    @FXML
    private Button btPrevCar;
    @FXML
    private ImageView imgVehiculo;
    @FXML
    private Label lbNombreVehiculo;
    @FXML
    private Label lbPrecio;
    @FXML
    private Label lbUbicacion;
    @FXML
    private Label lbAnio;
    @FXML
    private Label lbKilometraje;
    @FXML
    private Label lbMotor;
    @FXML
    private Label lbTransmision;
    @FXML
    private Label lbPeso;
    @FXML
    private Label lbMantenimiento;
    @FXML
    private Label lbAccidente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void prevCar(ActionEvent event){
        
    }
    @FXML
    private void nextCar(ActionEvent event){
        
    }
    
}
