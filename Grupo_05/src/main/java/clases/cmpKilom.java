/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.Comparator;

/**
 *
 * @author jaren
 */
public class cmpKilom implements Comparator<Vehiculo>{
    
    // Ordenar por Marca y Modelo (en el "main" o donde lo quiera implementar)
    // Collections.sort(vehiculos, new ComparadorPorMarcaYModelo());

    @Override
    public int compare(Vehiculo v1, Vehiculo v2) {
        return Double.compare(v1.getKilometraje(), v2.getKilometraje());
    }
    
}
