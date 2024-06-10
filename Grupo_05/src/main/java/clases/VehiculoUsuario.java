/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import MyTDAs.LinkedListPRS;

/**
 *
 * @author Pc
 */
public class VehiculoUsuario extends Vehiculo{
    public static String pathImages = "src/main/resources/user/";
    private String propietario;
    
    public VehiculoUsuario() {
        
    }
    
    public VehiculoUsuario(String propietario, String imagen, String ubicacion, 
            double precio, String marca, String modelo, int anio, 
            int kilometraje, String motor, String transmision, double peso, 
            LinkedListPRS<String> historialA, LinkedListPRS<String> historialM) {
        super(pathImages+imagen, ubicacion, precio, marca, modelo, anio, kilometraje, 
                motor, transmision, peso, historialA, historialM);
        this.propietario = propietario;
    }
    
    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
    
}
