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
public class cmpAnio implements Comparator<Vehiculo>{
    
    public static void ordenarAnio(LinkedListPRS<Vehiculo> vehiculos){
        LinkedListPRS<Integer> anios = new LinkedListPRS();
        for (Vehiculo v: vehiculos) anios.add(v.getAnio());
        Collections.sort(anios, (p1,p2) ->{
            return compare(p1, p2);
        });
        LinkedListPRS<Vehiculo> ordenado = new LinkedListPRS();
        for (Integer i: anios){
            for (Vehiculo v: vehiculos){
                if ( Integer.compare(i, v.getAnio()) == 0 ){
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
    public int compare(Vehiculo v1, Vehiculo v2){
        return Integer.compare(v1.getAnio(), v2.getAnio());
    }
    
    public static int compare(int a1, int a2){
        return Integer.compare(a1, a2);
    }
}
