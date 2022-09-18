
package Estructuras.Listas;

import Clases.Fecha;
import Clases.Palabra;

public class TDato<T> implements Comparable<T> {

    private T o;
    private String className;

    public TDato(T obj) {
        //Me guardo el nombre de la clase encapsulada.
        className = obj.getClass().getName();
        o = obj;
    }

    /**
     * HAY QUE REDEFINIRLO SEGUN LA CLASE!.
     *
     * @param o
     * @return
     */
//    @Override
//    public int CompareTo(T obj) {
//        return this.hashCode();
//    }

    /**
     * @return el Objeto encapsulado [Hay que castearlo]
     */
    public Object getO() {
        
        return o;
    }

    /**
     * @param o Seteamos el dato a guardar encapsulado [No se castea]
     */
    public void setO(T o) {
        this.o = o;
    }

    /**
     * @return el nombre de la clase encapsulada [para conocer el tipo por las
     * dudas]
     */
    public String getClassName() {
        return className;
    }

    /**
     * Seteamos el nombre de la clase.
     *
     * @param className
     */
    public void setClassName(String className) {
        this.className = className;
    }
    
     @Override
    public boolean equals(Object o) {
        T a = (T) o ; 
        return this.o.equals(a);
    }

    @Override
    public int compareTo(T o) {
        
        if (this.className=="Clases.Palabra") {
            Palabra p = (Palabra) o ;
            Palabra aux = (Palabra) this.o;
            return p.compareTo(aux);
        }
        
        if (this.className=="Clases.Fecha") {
            Fecha d = (Fecha) o ;
            Fecha aux = (Fecha) this.o;
            return d.compareTo(aux);
        }
        
        return this.hashCode();
    }
    
}
