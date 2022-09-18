/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Estructuras.Listas.Lista;
import java.util.Objects;

/**
 *
 * @author Usuario
 */
public class Contacto {
       
    int numero ; 
    String nombre ;
    
    Lista<Mensaje> Mensajes ;
    /**
     *
     * @param numero
     * @param nombre
     */
    public Contacto(int numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
        this.Mensajes = new Lista<Mensaje>();
        this.Mensajes.setId(1);
    }
           
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Lista<Mensaje> getMensajes() {
        return Mensajes;
    }

    public void setMensajes(Lista<Mensaje> Mensajes) {
        this.Mensajes = Mensajes;
    }
    
    /**
     *
     * @param obj
     * @return
     */
    
    @Override
    public boolean equals(Object obj) {
      if((obj !=null) && (obj instanceof Contacto)) {
          Contacto c = (Contacto) obj;
           return c.numero == this.numero ; 
      } else {
         return false;
    }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.numero;
        hash = 43 * hash + Objects.hashCode(this.nombre);
        return hash;
    }
    
    @Override
    public String toString() {
    return this.numero + " - " + this.nombre ;
    }


}

