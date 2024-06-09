/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package principal;

import MyTDAs.LinkedListPRS;
import MyTDAs.PilaPRS;
import archivos.LecturaArchivos;
import clases.Vehiculo;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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

import filtros.*;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    @FXML
    private Button btnCrear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scrlPane.setFitToWidth(true);
        scrlPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        PilaPRS<Vehiculo> pila = LecturaArchivos.leerVehiculos("Vehiculos.txt");
        cargarTodosCbx(pila);
        cargarVehiculosFlowPane(pila);
        
    }    
    
    public void cargarVehiculosFlowPane(PilaPRS<Vehiculo> pila){
        flwPane.getChildren().clear();
        for (Vehiculo v: pila){
            VBox cajaVehiculo = new VBox(10);
            cajaVehiculo.setPrefSize(196, 250);
            cajaVehiculo.setPadding(new Insets(5));
            
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
    
    public void cargarAniosCbx(PilaPRS<Vehiculo> pila){
        List<Integer> anios = new LinkedListPRS<Integer>();
        for(Vehiculo v: pila){
            anios.add(v.getAnio());
        }
        int x = anios.get(0), y = anios.get(0);
        for(Integer i: anios){
            x = Math.min(i, x);
            y = Math.max(i, y);
        }
        
        for(x=x; x<=y; x++){
            anioDesde.getItems().add(x);
            anioHasta.getItems().add(x);
        }

    }
    
    public void cargarPreciosCbx(PilaPRS<Vehiculo> pila){
        List<Double> precios = new LinkedListPRS<Double>();
        for(Vehiculo v: pila){
            precios.add(v.getPrecio());
        }
            
        for(Double d: precios){
            if(!precioDesde.getItems().contains(d) && !precioHasta.getItems().contains(d)){
                precioDesde.getItems().add(d);
                precioHasta.getItems().add(d);
            }
        }
    }
    
    public void cargarMarcasCbx(PilaPRS<Vehiculo> pila){
        List<String> marcas = new LinkedListPRS<String>();
        for(Vehiculo v: pila){
            marcas.add(v.getMarca());
        }
            
        for(String s: marcas){
            if(!cbMarca.getItems().contains(s)){
                cbMarca.getItems().add(s);
            }
        }
    }
    
    /*public void cargarModelosCbx(PilaPRS<Vehiculo> pila){
        List<String> modelos = new LinkedListPRS<String>();
        for(Vehiculo v: pila){
            modelos.add(v.getModelo());
        }
            
        for(String s: modelos){
            if(!cbModelo.getItems().contains(s)){
                cbModelo.getItems().add(s);
            }
        }
    }*/
    
    public void cargarKmCbx(PilaPRS<Vehiculo> pila){
        List<Integer> kms = new LinkedListPRS<Integer>();
        for(Vehiculo v: pila){
            kms.add(v.getKilometraje());
        }
        
        for(Integer i: kms){
            if(!kmDesde.getItems().contains(i) && !kmHasta.getItems().contains(i)){
                kmDesde.getItems().add(i);
                kmHasta.getItems().add(i);
            }
        }
    }
    
    public void cargarTodosCbx(PilaPRS<Vehiculo> pila){
        cargarPreciosCbx(pila);
        cargarAniosCbx(pila);
        cargarKmCbx(pila);
        cargarMarcasCbx(pila);

    }
    
    public static void cargarCarruselVehiculos(){
        
    }

    @FXML
    private void seleccionAnio(ActionEvent event) {
        Integer desde = anioDesde.getValue();
        anioHasta.getItems().clear();
        int x = desde, y = anioDesde.getItems().get(anioDesde.getItems().size()-1);

        for(x=x; x<=y; x++){
            anioHasta.getItems().add(x);
        }
        
    }

    @FXML
    private void seleccionMarca(ActionEvent event) {
        cbModelo.getItems().clear();
        String marca = cbMarca.getValue();
        PilaPRS<Vehiculo> pila = filtroMarca.filtrarPorMarca(LecturaArchivos.leerVehiculos("Vehiculos.txt"), marca);
        for(Vehiculo c: pila){
            if(!cbModelo.getItems().contains(c.getModelo()))
                cbModelo.getItems().add(c.getModelo());
        }
    }

    @FXML
    private void crearVehiculo(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = App.loadFXML("crearVehiculo");
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
