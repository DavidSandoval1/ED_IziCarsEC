/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package principal;

import MyTDAs.CircularListPRS;
import MyTDAs.PilaPRS;
import archivos.LecturaArchivos;
import clases.VehiculoUsuario;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jaren
 */
public class VUserController implements Initializable {

    @FXML
    private Button btnCrear;
    @FXML
    private ScrollPane scrlPane;
    @FXML
    private FlowPane flwPane;

    /**
     * Initializes the controller class.
     */
    
    public static PilaPRS<VehiculoUsuario> vehiculosUsuario = LecturaArchivos.leerVehiculosUsuario("VehiculosUsuario.txt");
    public static CircularListPRS<VehiculoUsuario> vehiculosFiltroUsuario = new CircularListPRS();
    @FXML
    private AnchorPane mainPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scrlPane.setFitToWidth(true);
        scrlPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        vehiculosFiltroUsuario.clear();
        vehiculosFiltroUsuario.addAll(vehiculosUsuario);
        cargarVehiculosFlowPane(vehiculosFiltroUsuario);
    }
    
    public static void recargarVehiculos(PilaPRS<VehiculoUsuario> pila){
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("src/main/resources/user/VehiculosUsuario.txt"))) {
            escritor.write("propietario[0];imagen[1];ubicacion[2];precio[3];marca[4];modelo[5];anio[6];kilometraje[7];motor[8];transmision[9];peso[10];historialA[11];historialM[12]");
            for(VehiculoUsuario vU: pila){
                StringBuilder sbHA = new StringBuilder();
                for(String s: vU.getHistorialA()){
                    if(s.equals(vU.getHistorialA().get(vU.getHistorialA().size()-1))) sbHA.append(s);
                    else sbHA.append(s).append(",");
                }
                StringBuilder sbHM = new StringBuilder();
                for(String s: vU.getHistorialM()){
                    if(s.equals(vU.getHistorialM().get(vU.getHistorialM().size()-1))) sbHM.append(s);
                    else sbHM.append(s).append(",");
                }
                escritor.newLine();
                escritor.write("" + vU.getPropietario() + ";" + vU.getImagen() + ";" + vU.getUbicacion() + ";" + vU.getPrecio() + ";" + 
                vU.getMarca() + ";" + vU.getModelo() + ";" + vU.getAnio() + ";" + vU.getKilometraje() + ";" + vU.getMotor() + ";" + 
                vU.getTransmision() + ";" + vU.getPeso() + ";" + sbHA.toString() + ";" + sbHM.toString());
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    public void cargarVehiculosFlowPane(CircularListPRS<VehiculoUsuario> pila){
        flwPane.getChildren().clear();
        for (VehiculoUsuario v: pila){
            VBox cajaVehiculo = new VBox(10);
            cajaVehiculo.setPrefSize(196, 250);
            cajaVehiculo.setPadding(new Insets(5));
            cajaVehiculo.setOnMouseClicked((MouseEvent e) -> {
                try {
                    VehiculoUsuario vehiculoActual = vehiculosFiltroUsuario.actualNode(v);
                    FXMLLoader fxmlLoader = App.loadFXML("editVehiculoUser");
                    Parent root = fxmlLoader.load();
                    //Cargar el vehiculo actual al carrusel
                    EditVehiculoUserController editController = fxmlLoader.getController();
                    editController.cargarVehiculo(vehiculoActual);
                    Scene s = new Scene(root, 900, 460);
                    //juegoController jc = fxmlLoader.getController();
                    //jc.recibirValores(txt_nombre.getText(), colorFondo);                                       
                    Stage stage = new Stage();
                    stage.setTitle("Tus Vehiculos");
                    stage.setScene(s);
                    stage.setResizable(false);
                    stage.initModality(Modality.APPLICATION_MODAL);              
                    
                    stage.show();
                    
                    Stage stage2 = (Stage) mainPane.getScene().getWindow();
                    stage2.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            
            //Borde negro
            BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2));
            Border border = new Border(borderStroke);
            cajaVehiculo.setBorder(border);
            //Imagen del vehiculo
            ImageView imagenVehiculo = new ImageView(new Image("file:"+VehiculoUsuario.pathImages+v.getImagen()));
            imagenVehiculo.setFitWidth(186);
            imagenVehiculo.setFitHeight(186);
            imagenVehiculo.setPreserveRatio(true);
            //Label de informacion
            Label infoVehiculo = new Label(v.getMarca()+" "+v.getModelo()+" - "+v.getAnio());
            Label infoPrecio = new Label(v.getPrecio()+"$ - "+v.getKilometraje()+" Km");
            
            cajaVehiculo.getChildren().addAll(imagenVehiculo, infoVehiculo, infoPrecio);
            flwPane.getChildren().add(cajaVehiculo);
        }
    }

    @FXML
    private void crearVehiculo(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = App.loadFXML("crearVehiculo");
            Scene s = new Scene(fxmlLoader.load(), 900, 460);
            Stage stage = new Stage();
            stage.setTitle("Crea tu Vehículo");
            stage.setScene(s);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
