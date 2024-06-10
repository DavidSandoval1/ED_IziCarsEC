/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordenamiento;

import MyTDAs.LinkedListPRS;
import clases.Vehiculo;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author jaren
 */
public class cmpMarcayModelo implements Comparator<Vehiculo> {
    
    public static void ordenarModelo(LinkedListPRS<Vehiculo> vehiculos){
        Set<String> marcas = new TreeSet();
        LinkedListPRS<String> modelos = new LinkedListPRS();
        for (Vehiculo v: vehiculos){
            marcas.add(v.getMarca());
            modelos.add(v.getModelo());
        }
        LinkedListPRS<Vehiculo> ordenado = new LinkedListPRS();
        Collections.sort(modelos);
        for (String s: marcas){
            for (String m: modelos){
                for (Vehiculo v: vehiculos){
                    if ( v.getMarca().equals(s) && v.getModelo().equals(m) ){
                        ordenado.add(v);
                        vehiculos.remove(v);
                        modelos.remove(m);
                        break;
                    }
                }
            }
        }
        vehiculos.clear();
        vehiculos.addAll(ordenado);
    }
    
    // Ordenar por Marca y Modelo (en el "main" o donde lo quiera implementar)
    // Collections.sort(vehiculos, new ComparadorPorMarcaYModelo());
    
    @Override
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
