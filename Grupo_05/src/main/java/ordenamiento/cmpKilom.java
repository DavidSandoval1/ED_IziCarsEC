/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordenamiento;

import clases.Vehiculo;
import java.util.Comparator;

/**
 *
 * @author jaren
 */
public class cmpKilom implements Comparator<Vehiculo>{

    @Override
    public int compare(Vehiculo v1, Vehiculo v2) {
        return Double.compare(v1.getKilometraje(), v2.getKilometraje());
    }
    
    public int compare(double d1, double d2) {
        return Double.compare(d1, d2);
    }
    
}
