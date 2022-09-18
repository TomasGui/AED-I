
package Clases;
import Estructuras.Listas.Lista;
import java.text.SimpleDateFormat;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Mensaje {
    
    public int id ; 
    public Contacto ContactoOrigen ;
    public Contacto ContactoDestino ; 
    public Date Fecha; 
    public Lista<Linea> Lineas ;
    
    public Mensaje() {
    }

    public Mensaje(Contacto ContactoOrigen, Contacto ContactoDestino, Date Fecha ) {
        
        this.ContactoOrigen = ContactoOrigen;
        this.ContactoDestino = ContactoDestino;
        this.Fecha = Fecha;
        this.Lineas = new Lista<Linea>();
    
    }

    public int getId() {
        return id;
    }
    
    public int setId(int id) {
        return this.id=id;
    }

    public Contacto getContactoOrigen() {
        return ContactoOrigen;
    }

    public void setContactoOrigen(Contacto ContactoOrigen) {
        this.ContactoOrigen = ContactoOrigen;
    }

    public Contacto getContactoDestino() {
        return ContactoDestino;
    }

    public void setContactoDestino(Contacto ContactoDestino) {
        this.ContactoDestino = ContactoDestino;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public Lista<Linea> getLineas() {
        return Lineas;
    }

    public void setLineas(Lista<Linea> Lineas) {
        this.Lineas = Lineas;
    }
    
    
    
    @Override
    public boolean equals(Object obj) {
      if((obj !=null) && (obj instanceof Mensaje)) {
          Mensaje m = (Mensaje) obj;
           return m.id == this.id && m.ContactoOrigen.equals(this.ContactoOrigen) ; 
      } else {
         return false;
    }
    }
    
    @Override
    public String toString() {
     SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        String fecha_ini = format1.format(this.Fecha);
    return "ID Msj : " + this.id + "\n Contacto Origen : " + this.ContactoOrigen.toString() + " \n Contacto Destino: " +this.ContactoDestino.toString() + "\n Fecha : " + fecha_ini;
    }
    
    
}
