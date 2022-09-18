/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Usuario
 */
public class Palabra implements Comparable<Palabra> {
    public String dato ;

    public Palabra(String dato) {
        this.dato = dato;
    }
    
    @Override
    public String toString() {
        return dato ;
    }
    
     @Override
    public boolean equals(Object obj) {
      if((obj !=null) && (obj instanceof Palabra)) {
          Palabra p = (Palabra) obj;
           return this.dato.compareTo(p.dato) == 0 ; 
      } else {
         return false;
    }
    }

    @Override
    public int compareTo(Palabra o) {
        String p = (String) o.dato;
        String aux= (String) this.dato ; 
        return aux.compareTo(p);
    }
}
