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
public class filtroMarca {
    public static PilaPRS<Vehiculo> filtrarPorMarca(PilaPRS<Vehiculo> vehiculos, String marca){
        PilaPRS<Vehiculo> pilaVehiculos = new PilaPRS();
        List<Vehiculo> vehiculosFiltrados = vehiculos.stream()
                .filter( v -> v.getMarca().equals(marca) )
                .collect(Collectors.toList());
        for (Vehiculo v: vehiculosFiltrados){
            pilaVehiculos.push(v);
        }
        return pilaVehiculos;
    }
}
