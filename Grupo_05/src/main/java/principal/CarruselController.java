/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import clases.Vehiculo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    
    public void cargarController(Vehiculo actual){
        imgVehiculo.setImage(new Image("file:"+actual.getImagen()));
        lbNombreVehiculo.setText(actual.getMarca()+" "+actual.getModelo());
        lbPrecio.setText(String.format("%.2f",actual.getPrecio()));
        lbUbicacion.setText(actual.getUbicacion());
        lbAnio.setText(actual.getAnio()+"");
        lbKilometraje.setText(actual.getKilometraje()+"");
        lbMotor.setText(actual.getMotor());
        lbTransmision.setText(actual.getTransmision());
        lbPeso.setText(String.format("%.2f", actual.getPeso()));
        String stringM = "";
        for (String s: actual.getHistorialM()) stringM += s+"\n";
        lbMantenimiento.setText(stringM);
        String stringA = "";
        for (String s: actual.getHistorialA()) stringA += s+"\n";
        lbAccidente.setText(stringA);
    }
    
    @FXML
    private void prevCar(ActionEvent event){
        this.cargarController(InicioController.vehiculosFiltrados.prevNode());
    }
    @FXML
    private void nextCar(ActionEvent event){
        this.cargarController(InicioController.vehiculosFiltrados.nextNode());
    }
    
}
