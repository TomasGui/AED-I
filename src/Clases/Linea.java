/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import Estructuras.Listas.Lista;
/**
 *
 * @author Usuario
 */
public class Linea {
    
    
    public Lista<Palabra> Palabras ;
    int maximo_cantidadPalabras_x_linea;

    public Linea(int maximo_cantidadPalabras_x_linea) {
        this.maximo_cantidadPalabras_x_linea = maximo_cantidadPalabras_x_linea;
        this.Palabras = new Lista() ; 
        this.Palabras.setLimite(maximo_cantidadPalabras_x_linea);
    }
   
}

