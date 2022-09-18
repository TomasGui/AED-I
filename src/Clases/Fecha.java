/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Fecha {
    Date fecha ; 

    public Fecha(Date fecha) {
        this.fecha = fecha;
    }
 
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public int compareTo(Fecha o) {
        Date d = (Date) o.fecha;
        Date aux = (Date) this.fecha ; 
        return aux.compareTo(d);
    }
    
    @Override
    public boolean equals(Object obj) {
      if((obj !=null) && (obj instanceof Fecha)) {
          Fecha d = (Fecha) obj;
           return this.fecha.compareTo(d.fecha) == 0 ; 
      } else {
         return false;
    }
    }
    
     @Override
    public String toString() {
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        String fecha_ini = format1.format(fecha);
        return fecha_ini ;
    }
}
