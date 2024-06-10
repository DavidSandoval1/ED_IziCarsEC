/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordenamiento;

import MyTDAs.LinkedListPRS;
import clases.Vehiculo;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author jaren
 */
public class cmpKilom implements Comparator<Vehiculo>{
    
    public static void ordenarKilom(LinkedListPRS<Vehiculo> vehiculos){
        LinkedListPRS<Integer> kilometrajes = new LinkedListPRS();
        for (Vehiculo v: vehiculos) kilometrajes.add(v.getKilometraje());
        Collections.sort(kilometrajes, (p1,p2) ->{
            return compare(p1, p2);
        });
        LinkedListPRS<Vehiculo> ordenado = new LinkedListPRS();
        for (Integer i: kilometrajes){
            for (Vehiculo v: vehiculos){
                if ( Integer.compare(i, v.getKilometraje()) == 0 ){
                    ordenado.add(v);
                    vehiculos.remove(v);
                    break;
                }
            }
        }
        vehiculos.clear();
        vehiculos.addAll(ordenado);
    }
    
    @Override
    public int compare(Vehiculo v1, Vehiculo v2) {
        return Double.compare(v1.getKilometraje(), v2.getKilometraje());
    }   
    
    public static int compare(double d1, double d2) {
        return Double.compare(d1, d2);
    }
    
}
