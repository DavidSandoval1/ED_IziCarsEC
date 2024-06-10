/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package principal;

import MyTDAs.*;
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
import java.util.Collections;
import java.util.Comparator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ordenamiento.*;

/**
 * FXML Controller class
 *
 * @author User
 */
public class InicioController implements Initializable {
    public static PilaPRS<Vehiculo> vehiculosSistema = LecturaArchivos.leerVehiculos("Vehiculos.txt");
    public static CircularListPRS<Vehiculo> vehiculosFiltrados = new CircularListPRS();

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
    @FXML
    private Button btnMisVehiculos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vehiculosFiltrados.addAll(vehiculosSistema);
        scrlPane.setFitToWidth(true);
        scrlPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        cargarTodosCbx(vehiculosSistema);
        cargarVehiculosFlowPane(vehiculosFiltrados);
        
    }    
    
    public void cargarVehiculosFlowPane(CircularListPRS<Vehiculo> pila){
        flwPane.getChildren().clear();
        for (Vehiculo v: pila){
            VBox cajaVehiculo = new VBox(10);
            cajaVehiculo.setPrefSize(196, 250);
            cajaVehiculo.setPadding(new Insets(5));
            cajaVehiculo.setOnMouseClicked((MouseEvent e) -> {
                try {
                    Vehiculo vehiculoActual = vehiculosFiltrados.actualNode(v);
                    FXMLLoader fxmlLoader = App.loadFXML("carrusel");
                    Parent root = fxmlLoader.load();
                    //Cargar el vehiculo actual al carrusel
                    CarruselController carruselController = fxmlLoader.getController();
                    carruselController.cargarController(vehiculoActual);
                    Scene s = new Scene(root, 900, 460);
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
        
        Collections.sort(precios, (p1,p2) ->{
            return new cmpPrecio().compare(p1, p2);
        });
        
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
        
        Collections.sort(marcas, (m1,m2) -> {
            return m1.compareTo(m2);
        });
            
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
        
        Collections.sort(kms, (k1,k2)->{
            return new cmpKilom().compare(k1, k2);
        });
        
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

    @FXML
    private void seleccionPrecio(ActionEvent event) {
        precioHasta.getItems().clear();
        double desde = precioDesde.getValue();
        if(desde == precioDesde.getItems().get(precioDesde.getItems().size()-1)){
            precioHasta.getItems().add(desde);
            return;
        } 
        int x = precioDesde.getItems().indexOf(desde)+1, y = precioDesde.getItems().size()-1;
        for(x=x; x<=y; x++){
            precioHasta.getItems().add(precioDesde.getItems().get(x));
        }   
    }

    @FXML
    private void seleccionKm(ActionEvent event) {
        kmHasta.getItems().clear();
        int desde = kmDesde.getValue();
        if(desde == kmDesde.getItems().get(kmDesde.getItems().size()-1)){
            kmHasta.getItems().add(desde);
            return;
        } 
        int x = kmDesde.getItems().indexOf(desde)+1, y = kmDesde.getItems().size()-1;
        for(x=x; x<=y; x++){
            kmHasta.getItems().add(kmDesde.getItems().get(x));
        }
    }
    
    @FXML
    private void seleccionAnio(ActionEvent event) {
        Integer desde = anioDesde.getValue();
        anioHasta.getItems().clear();
        if(desde == anioDesde.getItems().get(anioDesde.getItems().size()-1)){
            anioHasta.getItems().add(desde);
            return;
        } 
        int x = desde+1, y = anioDesde.getItems().get(anioDesde.getItems().size()-1);
        for(x=x; x<=y; x++){
            anioHasta.getItems().add(x);
        }
        
    }

    @FXML
    private void seleccionMarca(ActionEvent event) {
        cbModelo.getItems().clear();
        String marca = cbMarca.getValue();
        PilaPRS<Vehiculo> pila = filtroMarca.filtrarPorMarca(LecturaArchivos.leerVehiculos("Vehiculos.txt"), marca);
        LinkedListPRS<String> modelos = new LinkedListPRS<String>();
        for(Vehiculo v: pila){
            if(!modelos.contains(v.getModelo())) modelos.add(v.getModelo());
        }
        Collections.sort(modelos, (m1,m2) -> {
            return m1.compareTo(m2);
        });
        for(String m: modelos){
            cbModelo.getItems().add(m);
        }
    }

    @FXML
    private void filtrarBusqueda(){
        PilaPRS<Vehiculo> vehiculos = vehiculosSistema;
        LinkedListPRS<Vehiculo> vehiculosOrdenados = new LinkedListPRS();
        //Filtrando por Precio
        Double precioD = precioDesde.getValue();
        Double precioH = precioHasta.getValue();
        if ( precioD == null ) precioD = precioDesde.getItems().get(0);
        if ( precioH == null ) precioH = precioHasta.getItems().get(precioHasta.getItems().size()-1);
        vehiculos = filtroPrecio.filtrarPorPrecio(vehiculos, precioD, precioH);
        //Filtrando por Anio
        Integer anioD = anioDesde.getValue();
        Integer anioH = anioHasta.getValue();
        if ( anioD == null ) anioD = anioDesde.getItems().get(0);
        if ( anioH == null ) anioH = anioHasta.getItems().get(anioHasta.getItems().size()-1);
        vehiculos = filtroAnio.filtrarPorAnio(vehiculos, anioD, anioH);
        //Filtrando por Marca
        String marca = cbMarca.getValue();
        String modelo = cbModelo.getValue();
        if ( marca != null ){
            vehiculos = filtroMarca.filtrarPorMarca(vehiculos, marca);
            if ( modelo != null ){
                vehiculos = filtroModelo.filtrarPorModelo(vehiculos, modelo);
            }
        }
        //Filtrando por Kilometraje
        Integer kmD = kmDesde.getValue();
        Integer kmH = kmHasta.getValue();
        if ( kmD == null ) kmD = kmDesde.getItems().get(0);
        if ( kmH == null ) kmH = kmHasta.getItems().get(kmHasta.getItems().size()-1);
        vehiculos = filtroKilom.filtrarPorKilom(vehiculos,kmD, kmH);
        vehiculosOrdenados.addAll(vehiculos);
        //LOGICA PARA ORDENARLOS
        vehiculosFiltrados.clear();
        vehiculosFiltrados.addAll(vehiculosOrdenados);
        cargarVehiculosFlowPane(vehiculosFiltrados);
    }
    
    @FXML
    private void limpiarBusqueda(){
        vehiculosFiltrados.clear();
        vehiculosFiltrados.addAll(vehiculosSistema);
    }
    
    private void cargarVehiculosUsuario() {
        
    }
}
