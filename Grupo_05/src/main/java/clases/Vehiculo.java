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
public class Vehiculo {
    private String imagen;
    private String ubicacion;
    private double precio;
    private String marca;
    private String modelo;
    private int año;
    private int kilometraje;
    private String motor;
    private String transmision;
    private double peso;
    private LinkedListPRS<String> historialA;
    private LinkedListPRS<String> historialM;
    
    @Override
    public String toString(){
        return marca+" "+modelo;
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

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
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
