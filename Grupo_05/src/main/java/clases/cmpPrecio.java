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
public class cmpPrecio implements Comparator<Vehiculo> {

    @Override
    public int compare(Vehiculo v1, Vehiculo v2) {
        return Double.compare(v1.getPrecio(), v2.getPrecio());
    }
    
}
