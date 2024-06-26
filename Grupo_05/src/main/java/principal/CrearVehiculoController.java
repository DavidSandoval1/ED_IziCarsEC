/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package principal;

import MyTDAs.*;
import clases.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private TextField txtFHistM;
    @FXML
    private TextField txtFHistA;
    @FXML
    private Button btnCrear;
    @FXML
    private AnchorPane mainPane;
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
        
    }    

    @FXML
    private void crearVehiculoBtn(ActionEvent event) {
        try{
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
                Alert a = new Alert(AlertType.WARNING, "Campos vacíos.");
            }else{
                VehiculoUsuario vUsuario = new VehiculoUsuario(prop, nameFile, ubicacion, precio, marca, modelo, anio, km,
            motor, transmision, peso, HistA, HistM);
                Platform.runLater(() -> {
                    VUserController.vehiculosUsuario.add(vUsuario);
                    // REESCRIBIR TXT
                    VUserController.recargarVehiculos(VUserController.vehiculosUsuario);
                    VUserController.vehiculosFiltroUsuario.addAll(VUserController.vehiculosUsuario);
                });

                Stage stage = (Stage) mainPane.getScene().getWindow();
                stage.close(); //2024-12-02: perimetral, 2022-01-24: trinitaria
            }
        }catch(NumberFormatException e){
            Alert a = new Alert(AlertType.ERROR, "Formato incorrecto para los datos.");
            a.show();
        }
    }

    @FXML
    private void subirImagen(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog((Stage)mainPane.getScene().getWindow());
        
        if(nameFile != null && selectedFile != null){
            Image image = new Image(selectedFile.toURI().toString());
            imView.setImage(image);
            try {
                // Eliminar el archivo anterior si existe                
                Path previousPath = Paths.get("src/main/resources/user/", nameFile);
                if (Files.exists(previousPath)) Files.delete(previousPath);

                // Ruta del archivo en el directorio de destino
                Path targetPath = Paths.get("src/main/resources/user/", selectedFile.getName());
                Files.copy(selectedFile.toPath(), targetPath);

                // Almacenar el nombre del archivo recién cargado
                nameFile = selectedFile.getName();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }    
        
        
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imView.setImage(image);
            String targetDir = "src/main/resources/user/";
            File targetDirectory = new File(targetDir);
            File targetFile = new File(targetDirectory, selectedFile.getName());
            nameFile = selectedFile.getName();

            try {
                Files.copy(selectedFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
}
