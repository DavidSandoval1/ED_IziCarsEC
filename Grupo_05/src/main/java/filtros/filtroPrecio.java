/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filtros;

import MyTDAs.PilaPRS;
import clases.Vehiculo;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Pc
 */
public class filtroPrecio {
    public static PilaPRS<Vehiculo> filtrarPorPrecio(PilaPRS<Vehiculo> vehiculos, double desde, double hasta){
        PilaPRS<Vehiculo> pilaVehiculos = new PilaPRS();
        List<Vehiculo> vehiculosFiltrados = vehiculos.stream()
                .filter( v -> (v.getPrecio() >= desde) && (v.getPrecio() <= hasta) )
                .collect(Collectors.toList());
        for (Vehiculo v: vehiculosFiltrados){
            pilaVehiculos.push(v);
        }
        return pilaVehiculos;
    }
}
