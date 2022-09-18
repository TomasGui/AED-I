package Estructuras.Listas;

/**
 *
 * @author RC
 */
public interface ILista {

    //POS: Constructor, crea una lista vacía
    void Lista();
    
    //POS: Retorna true si la lista no tiene nodos
    boolean esVacia();
    
    //POS: Inserta un nodo al principio de la lista
    boolean agregarInicio(TDato n);

    //POS: Inserta un nodo al final de la lista
    boolean agregarFinal(TDato n);
    
    public boolean agregarOrd(TDato n);
    
    public boolean borrarElemento(Object n) ;
    
    public int cantElementos();
     
    public NodoLista obtenerElemento(Object n);
    
    public TDato datoEnPos(int i);

    //POS: Borra el primer nodo
    boolean borrarInicio();

    //POS: Borra el último nodo
    boolean borrarFin();

    //POS: Muestra los datos de la lista
    void mostrar();

    //POS: Elimina todos los nodos de la lista
    void vaciar();
    
    boolean buscarElemento(TDato n);
    
    void insertarEnPos (int posicion , TDato n);
    
    void eliminarEnPos (int posicion);
    
    void vaciarREC();
    
    void borrarOcurrenciaElemento(Object n);
    
    public boolean puedoInsertar();
}
