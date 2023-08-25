/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Diccionario3;

/**
 *
 * @author alexx
 */
public class JugadorSeleccion {
    private int dorsal;
    private String nombre;
    private String demarcacion;
    
    public JugadorSeleccion(){
        
    }
    
    public JugadorSeleccion(int dorsal, String nombre, String demarcacion){
        this.dorsal = dorsal;
        this.nombre = nombre;
        this.demarcacion = demarcacion;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDemarcacion() {
        return demarcacion;
    }

    public void setDemarcacion(String demarcacion) {
        this.demarcacion = demarcacion;
    }

    @Override
    public String toString() {
        return "JugadorSeleccion{" + "dorsal=" + this.dorsal + ", nombre=" + this.nombre + ", demarcacion=" + 
                this.demarcacion + '}';
    }
    
    
}
