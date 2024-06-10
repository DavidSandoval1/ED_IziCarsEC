/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordenamiento;

import MyTDAs.LinkedListPRS;
import clases.Vehiculo;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author jaren
 */
public class cmpPrecio implements Comparator<Vehiculo> {
    
    public static void ordenarPrecio(LinkedListPRS<Vehiculo> vehiculos){
        LinkedListPRS<Double> precios = new LinkedListPRS();
        for (Vehiculo v: vehiculos) precios.add(v.getPrecio());
        Collections.sort(precios, (p1,p2) ->{
            return compare(p1, p2);
        });
        LinkedListPRS<Vehiculo> ordenado = new LinkedListPRS();
        for (Double i: precios){
            for (Vehiculo v: vehiculos){
                if ( Double.compare(i, v.getPrecio()) == 0 ){
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
        return Double.compare(v1.getPrecio(), v2.getPrecio());
    }
    
    public static int compare(Double d1, Double d2) {
        return Double.compare(d1, d2);
    }
    
}
