/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import MyTDAs.LinkedListPRS;
import java.util.Objects;

/**
 *
 * @author Pc
 */
public class Vehiculo {
    private String imagen;
    private String ubicacion;
    private double precio;
    private String marca;
    private String modelo;
    private int anio;
    private int kilometraje;
    private String motor;
    private String transmision;
    private double peso;
    private LinkedListPRS<String> historialA;
    private LinkedListPRS<String> historialM;
    
    public Vehiculo(){
        
    }

    public Vehiculo(String imagen, String ubicacion, double precio, 
            String marca, String modelo, int anio, int kilometraje, 
            String motor, String transmision, double peso, 
            LinkedListPRS<String> historialA, LinkedListPRS<String> historialM) {
        this.imagen = imagen;
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.kilometraje = kilometraje;
        this.motor = motor;
        this.transmision = transmision;
        this.peso = peso;
        this.historialA = historialA;
        this.historialM = historialM;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.imagen);
        hash = 47 * hash + Objects.hashCode(this.ubicacion);
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.precio) ^ (Double.doubleToLongBits(this.precio) >>> 32));
        hash = 47 * hash + Objects.hashCode(this.marca);
        hash = 47 * hash + Objects.hashCode(this.modelo);
        hash = 47 * hash + this.anio;
        hash = 47 * hash + this.kilometraje;
        hash = 47 * hash + Objects.hashCode(this.motor);
        hash = 47 * hash + Objects.hashCode(this.transmision);
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.peso) ^ (Double.doubleToLongBits(this.peso) >>> 32));
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehiculo other = (Vehiculo) obj;
        if (Double.doubleToLongBits(this.precio) != Double.doubleToLongBits(other.precio)) {
            return false;
        }
        if (this.anio != other.anio) {
            return false;
        }
        if (this.kilometraje != other.kilometraje) {
            return false;
        }
        if (Double.doubleToLongBits(this.peso) != Double.doubleToLongBits(other.peso)) {
            return false;
        }
        if (!Objects.equals(this.imagen, other.imagen)) {
            return false;
        }
        if (!Objects.equals(this.ubicacion, other.ubicacion)) {
            return false;
        }
        if (!Objects.equals(this.marca, other.marca)) {
            return false;
        }
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        if (!Objects.equals(this.motor, other.motor)) {
            return false;
        }
        return Objects.equals(this.transmision, other.transmision);
    }
    
    @Override
    public String toString(){
        return marca+" "+modelo+"| "+kilometraje+"Km";
    }
    
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LinkedListPRS<String> getHistorialA() {
        return historialA;
    }

    public void setHistorialA(LinkedListPRS<String> historialA) {
        this.historialA = historialA;
    }

    public LinkedListPRS<String> getHistorialM() {
        return historialM;
    }

    public void setHistorialM(LinkedListPRS<String> historialM) {
        this.historialM = historialM;
    }      

    
    
    
}
