/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package principal;

import MyTDAs.LinkedListPRS;
import clases.Vehiculo;
import clases.VehiculoUsuario;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static principal.InicioController.vehiculosFiltrados;
import static principal.InicioController.vehiculosSistema;

/**
 * FXML Controller class
 *
 * @author jaren
 */
public class EditVehiculoUserController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnBorrar;
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
    private TextField txtFHistA;
    @FXML
    private TextField txtFHistM;
    @FXML
    private Button btnSubir;
    @FXML
    private ImageView imView;
    
    private String nameFile = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void subirImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog((Stage)mainPane.getScene().getWindow());
        
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imView.setImage(image);
            String targetDir = "src/main/resources/user/";
            File targetDirectory = new File(targetDir);
            File targetFile = new File(targetDirectory, selectedFile.getName());
            nameFile = selectedFile.getName();

            try {
                Files.copy(selectedFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Archivo guardado en: " + targetFile.getAbsolutePath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
    }    
    

    @FXML
    private void borrarVehiculo(ActionEvent event) {
        VehiculoUsuario v = VUserController.vehiculosFiltroUsuario.retornarActual();
        VUserController.vehiculosUsuario.remove(v);
        //REESCRIBIR TXT
        VUserController.recargarVehiculos(VUserController.vehiculosUsuario);
        VUserController.vehiculosFiltroUsuario.addAll(VUserController.vehiculosUsuario);
        
        // Mostrar una ventana que diga que completo el borrado
        
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }
    
    public void cargarVehiculo(VehiculoUsuario v) {
        txtFPropietario.setText(v.getPropietario());
        txtFMarca.setText(v.getMarca());
        txtFModelo.setText(v.getModelo());
        txtFPrecio.setText(""+v.getPrecio());
        txtFKm.setText(""+v.getKilometraje());
        txtFUbicacion.setText(v.getUbicacion());
        txtFAnio.setText(""+v.getAnio());
        txtFMotor.setText(v.getMotor());
        txtFTransmision.setText(v.getTransmision());
        txtFPeso.setText(""+v.getPeso());
        txtFHistA.setText(v.getHistorialA().toString());
        txtFHistM.setText(v.getHistorialM().toString());
        imView.setImage(new Image("file:"+VehiculoUsuario.pathImages+v.getImagen()));
    }

    @FXML
    private void editarVehiculoBtn(ActionEvent event) {
        VehiculoUsuario v = VUserController.vehiculosFiltroUsuario.retornarActual();
        try
        {
            String prop = txtFPropietario.getText();
            String marca = txtFMarca.getText();
            String modelo = txtFModelo.getText();
            String ubicacion = txtFUbicacion.getText();
            String motor = txtFMotor.getText();
            String transmision = txtFTransmision.getText();
            double precio = Double.parseDouble(txtFPrecio.getText());
            double peso = Double.parseDouble(txtFPeso.getText());
            int anio = Integer.parseInt(txtFAnio.getText());
            int km = Integer.parseInt(txtFKm.getText());
            if (nameFile == null){
                nameFile = v.getImagen();
            }
            
            StringBuilder sbHA = new StringBuilder();
            LinkedListPRS<String> HistA = new LinkedListPRS();
            if(!txtFHistA.getText().isEmpty()){
                String[] tokensHA = txtFHistA.getText().split(", ");
                for(int i = 0; i< tokensHA.length; i++){
                    HistA.add(tokensHA[i]);
                    if(i==tokensHA.length-1) sbHA.append(tokensHA[i]);
                    else sbHA.append(tokensHA[i]).append(",");
                }
            }
            
            StringBuilder sbHM = new StringBuilder();
            LinkedListPRS<String> HistM = new LinkedListPRS();
            if(!txtFHistM.getText().isEmpty()){
                String[] tokensHM = txtFHistM.getText().split(", ");
                for(int i = 0; i< tokensHM.length; i++){
                    HistM.add(tokensHM[i]);
                    if(i==tokensHM.length-1) sbHM.append(tokensHM[i]);
                    else sbHM.append(tokensHM[i]).append(",");
                }
            }
            
            if( prop == null ||  ubicacion == null || precio == 0|| marca == null || modelo == null|| anio == 0 || km == 0 ||
                motor == null || transmision == null || peso == 0 || nameFile == null || HistA.isEmpty() || HistM.isEmpty()){
                Alert a = new Alert(Alert.AlertType.WARNING, "Campos vacíos.");
                a.show();
            }else{
                VehiculoUsuario vUsuario = new VehiculoUsuario(prop, nameFile, ubicacion, precio, marca, modelo, anio, km,
            motor, transmision, peso, HistA, HistM);
                Platform.runLater(() -> {                    
                    System.out.println(v);
                    VUserController.vehiculosUsuario.remove(v);
                    System.out.println(vUsuario);
                    VUserController.vehiculosUsuario.add(vUsuario);
                    // REESCRIBIR TXT
                    VUserController.recargarVehiculos(VUserController.vehiculosUsuario);
                    VUserController.vehiculosFiltroUsuario.addAll(VUserController.vehiculosUsuario);
                    
                });
                Stage stage = (Stage) mainPane.getScene().getWindow();
                stage.close();
            }
        }catch(NumberFormatException e){
            Alert a = new Alert(Alert.AlertType.ERROR, "Formato incorrecto para los datos.");
            a.show();
        }
    }
    
}
