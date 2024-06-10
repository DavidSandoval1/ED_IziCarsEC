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
public class cmpAnio implements Comparator<Vehiculo>{
    
    @Override
    public int compare(Vehiculo v1, Vehiculo v2){
        return Integer.compare(v1.getAnio(), v2.getAnio());
    }
    
    public int compare(int a1, int a2){
        return Integer.compare(a1, a2);
    }
}
