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
public class cmpMarcayModelo implements Comparator<Vehiculo> {
    
    public int compare(Vehiculo v1, Vehiculo v2) {
        // Comparamos marca
        int marcaCompare = v1.getMarca().compareTo(v2.getMarca());
        
        // Si las marcas son diferentes, se comparan entre si
        if (marcaCompare != 0) {
            return marcaCompare;
        }
        
        // Si las marcas son iguales, se compara el modelo
        return v1.getModelo().compareTo(v2.getModelo());
    }    
}