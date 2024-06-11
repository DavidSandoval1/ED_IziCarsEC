/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package archivos;

import MyTDAs.LinkedListPRS;
import MyTDAs.PilaPRS;
import clases.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 * @author Pc
 */
public class LecturaArchivos {
    public static String pathFiles = "src/main/resources/files/";
    public static String pathImages = "src/main/resources/img/";
    public static String pathFilesUser = "src/main/resources/user/";
    
    public static PilaPRS<Vehiculo> leerVehiculos(String nombreArchivo){
        PilaPRS<Vehiculo> vehiculos = new PilaPRS();
        
        try ( BufferedReader bf = new BufferedReader( new FileReader(pathFiles+nombreArchivo)) ){
            String linea = bf.readLine();
            while ( (linea = bf.readLine()) != null ){
                String[] dato = linea.split(";");
                Vehiculo v = new Vehiculo();
                
                v.setImagen(pathImages+dato[0]); v.setUbicacion(dato[1]); v.setPrecio(Double.parseDouble(dato[2]));
                v.setMarca(dato[3]); v.setModelo(dato[4]); v.setAnio(Integer.parseInt(dato[5]));
                v.setKilometraje(Integer.parseInt(dato[6])); v.setMotor(dato[7]); v.setTransmision(dato[8]);
                v.setPeso(Double.parseDouble(dato[9]));
                
                LinkedListPRS<String> historialA = new LinkedListPRS();
                for (String e: dato[10].split(",")) historialA.add(e);
                v.setHistorialA(historialA);
                
                LinkedListPRS<String> historialM = new LinkedListPRS();
                for (String e: dato[11].split(",")) historialM.add(e);
                v.setHistorialM(historialM);
                vehiculos.push(v);
            }
            
        }catch (FileNotFoundException e1){
            System.out.println("Archivo de Vehículos no encontrado");
        }catch (Exception e){
            System.out.println("Ocurrió una excepción en la lectura de Vehículos");;
        }
        return vehiculos;
    }
    
    public static PilaPRS<VehiculoUsuario> leerVehiculosUsuario(String nombreArchivo){
        PilaPRS<VehiculoUsuario> vehiculos = new PilaPRS();
        
        try ( BufferedReader bf = new BufferedReader( new FileReader(pathFilesUser+nombreArchivo)) ){
            String linea = bf.readLine();
            while ( (linea = bf.readLine()) != null ){
                String[] dato = linea.split(";");
                VehiculoUsuario v = new VehiculoUsuario();
                
                v.setPropietario(dato[0]) ;v.setImagen(pathFilesUser+dato[1]); v.setUbicacion(dato[2]);
                v.setPrecio(Double.parseDouble(dato[3])); v.setMarca(dato[4]); v.setModelo(dato[5]); 
                v.setAnio(Integer.parseInt(dato[6])); v.setKilometraje(Integer.parseInt(dato[7])); 
                v.setMotor(dato[8]); v.setTransmision(dato[9]); v.setPeso(Double.parseDouble(dato[10]));
                
                LinkedListPRS<String> historialA = new LinkedListPRS();
                for (String e: dato[11].split(",")) historialA.add(e);
                v.setHistorialA(historialA);
                
                LinkedListPRS<String> historialM = new LinkedListPRS();
                for (String e: dato[12].split(",")) historialM.add(e);
                v.setHistorialM(historialM);
                vehiculos.push(v);
            }
            
        }catch (FileNotFoundException e1){
            System.out.println("Archivo de Vehículos no encontrado");
        }catch (Exception e){
            System.out.println("Ocurrió una excepción en la lectura de Vehículos de Usuario");;
        }
        return vehiculos;
    }
}
