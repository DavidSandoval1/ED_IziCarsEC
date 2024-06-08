/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package principal;

import MyTDAs.PilaPRS;
import archivos.LecturaArchivos;
import clases.Vehiculo;
import filtros.filtroAnio;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
    private ComboBox<Integer> anioDesde;
    @FXML
    private ComboBox<Integer> anioHasta;
    @FXML
    private ComboBox<String> cbMarca;
    @FXML
    private ComboBox<String> cbModelo;
    @FXML
    private ComboBox<Integer> kmDesde;
    @FXML
    private ComboBox<Integer> kmHasta;
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
    @FXML
    private ScrollPane scrlPane;
    @FXML
    private FlowPane flwPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scrlPane.setFitToWidth(true);
        scrlPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }    
    
    public void cargarVehiculosFlowPane(PilaPRS<Vehiculo> pila){
        flwPane.getChildren().clear();
        for (Vehiculo v: pila){
            VBox cajaVehiculo = new VBox(10);
            cajaVehiculo.setPrefSize(198, 250);
            cajaVehiculo.setPadding(new Insets(5));
            //Borde negro
            BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2));
            Border border = new Border(borderStroke);
            cajaVehiculo.setBorder(border);
            //Imagen del vehiculo
            ImageView imagenVehiculo = new ImageView(new Image("file:"+v.getImagen()));
            imagenVehiculo.setFitWidth(188);
            imagenVehiculo.setFitHeight(188);
            imagenVehiculo.setPreserveRatio(true);
            //Label de informacion
            Label infoVehiculo = new Label(v.getMarca()+" "+v.getModelo()+" - "+v.getAnio());
            Label infoPrecio = new Label(v.getPrecio()+"$ - "+v.getKilometraje()+" Km");
            
            cajaVehiculo.getChildren().addAll(imagenVehiculo, infoVehiculo, infoPrecio);
            flwPane.getChildren().add(cajaVehiculo);
        }
    }
    
    public static void cargarCarruselVehiculos(){
        
    }
}
