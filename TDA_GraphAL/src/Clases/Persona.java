/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author CltControl
 */
public class Persona implements Comparable<Persona>{
    private String nombre;
    private int edad;
    private String profesion;
    private String ciudad;

    public Persona(String nombre, int edad, String profesion, String ciudad){
        this.nombre = nombre;
        this.edad = edad;
        this.profesion = profesion;
        this.ciudad = ciudad;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    
    //RECORDAR, COMPARABLE EN ESTA CLASE, YA QUE TIENE ACCESO A LA CLASE
    @Override
    public int compareTo(Persona o) {
        return this.edad - o.getEdad();
    }


}
