/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.Objects;

/**
 *
 * @author renzo
 */
public class Sala {

    private String nombre;
    private int capacidad;

    public Sala(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setCapacidad(int capacidad){
        this.capacidad = capacidad;
    }
    
}
