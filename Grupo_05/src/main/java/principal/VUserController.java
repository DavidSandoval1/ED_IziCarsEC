/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package principal;

import MyTDAs.CircularListPRS;
import MyTDAs.LinkedListPRS;
import MyTDAs.PilaPRS;
import archivos.LecturaArchivos;
import clases.Vehiculo;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
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
    
    public static PilaPRS<Vehiculo> vehiculosUsuario = LecturaArchivos.leerVehiculosUsuario("VehiculosUsuario.txt");
    public static CircularListPRS<Vehiculo> vehiculosFiltroUsuario = new CircularListPRS();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vehiculosFiltroUsuario.addAll(vehiculosUsuario);
        scrlPane.setFitToWidth(true);
        scrlPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        cargarVehiculosFlowPane(vehiculosFiltroUsuario);
    }
    
    public void cargarVehiculosFlowPane(CircularListPRS<Vehiculo> pila){
        flwPane.getChildren().clear();
        for (Vehiculo v: pila){
            VBox cajaVehiculo = new VBox(10);
            cajaVehiculo.setPrefSize(196, 250);
            cajaVehiculo.setPadding(new Insets(5));
            cajaVehiculo.setOnMouseClicked((MouseEvent e) -> {
                try {
                    Vehiculo vehiculoActual = vehiculosFiltroUsuario.actualNode(v);
                    FXMLLoader fxmlLoader = App.loadFXML("carrusel");
                    Parent root = fxmlLoader.load();
                    //Cargar el vehiculo actual al carrusel
                    CarruselController carruselController = fxmlLoader.getController();
                    carruselController.cargarController(vehiculoActual);
                    Scene s = new Scene(root, 900, 460);
                    //juegoController jc = fxmlLoader.getController();
                    //jc.recibirValores(txt_nombre.getText(), colorFondo);
                    Stage stage = new Stage();
                    stage.setTitle("Vehiculo");
                    stage.setScene(s);
                    stage.setResizable(false);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            
            //Borde negro
            BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2));
            Border border = new Border(borderStroke);
            cajaVehiculo.setBorder(border);
            //Imagen del vehiculo
            ImageView imagenVehiculo = new ImageView(new Image("file:"+v.getImagen()));
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
            //juegoController jc = fxmlLoader.getController();
            //jc.recibirValores(txt_nombre.getText(), colorFondo);
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
