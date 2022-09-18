package Estructuras.Listas;


public class Lista<T> implements ILista {

    private NodoLista inicio;
    private NodoLista fin;
    private int limite;
    private int actual;
    private int id;

    //Constructor
    @Override
    public void Lista() {
        this.inicio = null;
        this.fin = null;
        this.actual = 0;
        this.limite = 0;
        this.id = 0 ;
    }

    //Inicio
    public void setInicio(NodoLista i) {
        inicio = i;
    }

    public NodoLista getInicio() {
        return inicio;
    }

    //Fin
    public void setFin(NodoLista f) {
        fin = f;
    }

    public NodoLista getFin() {
        return fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    /**
     * ***Métodos Básicos****
     */
    //PRE:
    //POS: Retorna true si la lista no tiene nodos
    @Override
    public boolean esVacia() {
        return this.inicio == null;
    }

    //PRE: 
    //POS: Agrega un nuevo Nodo al principio de la lista
    @Override
    public boolean agregarInicio(TDato n) {
        if (puedoInsertar()) {
            this.actual++;
            NodoLista nuevo = new NodoLista(n);
            nuevo.setSig(inicio);
            this.inicio = nuevo;
            if (this.fin == null)//estoy insertando el primer nodo
            {
                this.fin = nuevo;
            }
            return true;
        }
        return false;
    }

    //PRE:
    //POS: Agrega un nuevo Nodo al final de la lista
    @Override
    public boolean agregarFinal(TDato n) {
        if (puedoInsertar()) {
            
            //NodoLista nuevo= new NodoLista(n);
            if (this.esVacia()) {
                this.agregarInicio(n);
            } else {
                this.actual++;
                NodoLista aux = this.inicio;
                while (aux.getSig() != null) {
                    aux = aux.getSig();
                }
                NodoLista nuevo = new NodoLista(n);
                aux.setSig(nuevo);
                this.fin = nuevo;
                return true;
            }
        }
        return false;
    }

    //PRE:
    //POS: Borra el primer nodo
    public boolean borrarInicio() {
        this.actual--;
        if (!this.esVacia()) {
            this.inicio = this.inicio.getSig();
        }
        return true;
    }

    //PRE:
    //POS: Borra el último nodo
    public boolean borrarFin() {
        
        if (!this.esVacia()) {
            if (this.inicio == this.fin) {
                this.borrarInicio();
            } else {
                NodoLista aux = this.inicio;
                while (aux.getSig().getSig() != null) {
                    aux = aux.getSig();
                }
                this.fin = aux;
                this.fin.setSig(null);
                this.actual--;
            }
        }
        return true;
    }

    //PRE:
    //POS: elimina todos los nodos de una lista dada
    public void vaciar() {
        this.actual = 0;
        //en java alcanza con apuntar inicio y fin a null
        //inicio=fin=null;
        while (inicio != null) {
            borrarInicio();
        }
    }

    //PRE:
    //POS: Recorre y muestra los datos de lista
    public void mostrar() {
        if (this.esVacia()) {
            System.out.println("Lista vacia");
        } else {
            NodoLista aux = this.inicio;
            while (aux != null) {
                TDato c = (TDato) aux.getDato();
                T con = (T) c.getO();
                System.out.println(con.toString());
                aux = aux.getSig();
            }
        }
    }

    /**
     * ***Otros Métodos (iterativos)****
     */
    //PRE: lista ordenada => mantiena orden
    //POS: inserta nuevo elemento en orden ascendente
    @Override
    public boolean agregarOrd(TDato n) {
        if (puedoInsertar()) {
            //lista vacía o primer elemento es mayor o igual => agrego al ppio
            if (this.esVacia() || this.inicio.getDato().compareTo(n.getO()) <= 0) {
                this.agregarInicio(n);
                return true;
            }
            //último elemento es menor o igual => agrego al final
            if (this.fin.getDato().compareTo(n.getO()) > 0 ) {
                this.agregarFinal(n);
                return true;
            }
            NodoLista aux = this.inicio;
            while (aux.getSig() != null && aux.getSig().getDato().compareTo(n.getO()) > 0) {
                aux = aux.getSig();
            }
            NodoLista nuevo = new NodoLista(n);
            nuevo.setSig(aux.getSig());
            aux.setSig(nuevo);
            this.actual++;
            return true;
        }
        return false;
    }

    //PRE: lista ordenada
    //POS: Borra la primer ocurrencia de un elemento dado
    @Override
    public boolean borrarElemento(Object n) {
        
        if (this.esVacia()) {
            return true;
        }
        
        T a = (T) n ; 
        T datoCastIni = (T) this.inicio.getDato().getO();
        T datoCastFin = (T) this.fin.getDato().getO();
        
        if (datoCastIni.equals(a)) {
            return this.borrarInicio();
        }
        
        if(datoCastFin.equals(a)){ 
            return this.borrarFin();
        }
        //falto controlar que no se haya vaciado la lista antes
        if(this.cantElementos()>1) {
            NodoLista aux = this.inicio;
            T datoCastAux = (T) aux.getSig().getDato().getO();
            while (aux.getSig() != null) {
                datoCastAux = (T) aux.getSig().getDato().getO();
                if(datoCastAux.equals(a)) {
                    aux.setSig(aux.getSig().getSig());
                    this.actual--;
                    return true;
                }
                aux = aux.getSig();
            }
        }
        return false;
    }


        
    //PRE: 
    //POS: Retorna la cantidad de nodos que tiene la lista
    @Override
    public int cantElementos() {
        int cont = 0;
        if (!this.esVacia()) {
            NodoLista aux = this.inicio;
            while (aux != null) {
                aux = aux.getSig();
                cont++;
            }
        }
        return cont;
    }

    //PRE: //POS:
    @Override
    public NodoLista obtenerElemento(Object n) {
        
        if(n !=null && !this.esVacia()) {
            
            T a = (T) n ; 
            NodoLista aux = this.inicio;
            T datoCastAux = (T) aux.getDato().getO();
            
            while (aux != null) {
                datoCastAux = (T) aux.getDato().getO();
                if(datoCastAux.equals(a)) {
                    return aux;
                }
                aux = aux.getSig();
            }
        }    
        
        return null ;
    }
      

    /**
     * *** Métodos RECURSIVOS ****
     */
    //PRE:
    //POS: muestra los datos de la lista en orden de enlace
    public void mostrarREC(NodoLista l) {
        if (l != null) {
            System.out.println(l.getDato());
            mostrarREC(l.getSig());
        }
    }

    //PRE:
    //POS: muestra los datos de la lista en orden inverso
    public void mostrarInversoREC(NodoLista l) {
        if (l != null) {
            mostrarInversoREC(l.getSig());
            System.out.println(l.getDato());
        }
    }

    //PRE:
    //POS: retorna true si el elemento pertenece a la lista
    public boolean existeDatoREC(NodoLista l, Object n) {
        if (l != null) {
            if (l.getDato().compareTo(n) == 0) {
                return true;
            } else {
                return existeDatoREC(l.getSig(), n);
            }
        } else {
            return false;
        }
    }

    //PRE: Posicion de la lista
    //POS: Object en posicion i.
    @Override
    public TDato datoEnPos(int i) {
        int contador = 0;
        if(!this.esVacia()) {
           if (i == 0) {
            return this.inicio.getDato();
        }
        NodoLista ptr = this.inicio;
        while (contador < i) {
            ptr = ptr.getSig();
            contador++;
        }
        return ptr.getDato(); 
        }
        return null;
    }
    
    
    public NodoLista NodoDatoEnPos (int i) {
        int contador = 0;
        if(!this.esVacia()) {
           if (i == 0) {
            return this.inicio;
        }
        NodoLista ptr = this.inicio;
        while (contador < i) {
            ptr = ptr.getSig();
            contador++;
        }
        return ptr; 
        }
        return null;
    }

    /**
     * Si actual < a limite, inserto //PRE: -- //POS: True si actualmente no
     * paso el limite de la lista. @return T/F
     */
    @Override
    public boolean puedoInsertar() {
        if (this.limite == 0) {
            return true;
        }
        //return this.limite > this.actual; Actual
        return this.limite > this.cantElementos();
    }

    /**
     * @return the limite
     */
    public int getLimite() {
        return limite;
    }

    /**
     * @param limite the limite to set
     */
    public void setLimite(int limite) {
        this.limite = limite;
    }

    /**
     * @return the actual
     */
    public int getActual() {
        return this.cantElementos();
        //return actual;
    }

    /**
     * @param actual the actual to set
     */
    public void setActual(int actual) {
        this.actual = actual;
    }

    @Override
    public boolean buscarElemento(TDato n) {
    
     if(this.esVacia()) {
         return false;
     }   
        
     NodoLista aux=this.inicio;
            while(aux!=null) 
            {
                TDato c = (TDato) aux.getDato();
                T con = (T) c.getO();
                
                if(con.equals(n.getO())) {
                   return true;
                }
                aux=aux.getSig();
            }   
        return false ;
    
    }

    @Override
    public void insertarEnPos(int posicion, TDato n) {
        // Verifica si la posición ingresada se encuentre en el rango
        // >= 0 y <= que el numero de elementos del la lista.
        if(posicion>=0 && posicion<=this.cantElementos())
        {
            NodoLista nuevo = new NodoLista(n);
            // Consulta si el nuevo nodo a ingresar va al inicio de la lista.
            if(posicion == 0){
                // Inserta el nuevo nodo al inicio de la lista.
                this.agregarInicio(n) ; 
            }
            else{

                    this.actual++;
                    // Si el nodo a insertar va en el medio de la lista.
                    NodoLista aux = this.inicio;
                    // Recorre la lista hasta llegar al nodo anterior
                    // a la posicion en la cual se insertara el nuevo nodo.
                    for (int i = 0; i < (posicion-1); i++) {
                        aux = aux.getSig();
                    }
                    // Guarda el nodo siguiente al nodo en la posición
                    // en la cual va a insertar el nevo nodo.
                    NodoLista siguiente = aux.getSig();
                    // Inserta el nuevo nodo en la posición indicada.
                    aux.setSig(nuevo);
                    // Une el nuevo nodo con el resto de la lista.
                    nuevo.setSig(siguiente);
                    
                    //Asignar al ultimo nodo 
                        aux=this.inicio;
                        while(aux.getSig()!=null) {
                            aux=aux.getSig();
                        }
                        this.fin=aux;

            }
        }
    }

    @Override
    public void eliminarEnPos(int posicion) {
        
        NodoLista aux = this.inicio;
        int contPos=0;
        
        if(!this.esVacia()){
            if(posicion == this.cantElementos()-1) {
                this.borrarFin();
            }else if(posicion == 0){
                this.borrarInicio();
            } 
            else {
                boolean borrado = false;
                while (aux.getSig()!= null && !borrado)
                {   
                    if(contPos+1 == posicion)
                    {
                       borrado=true;
                       aux.setSig(aux.getSig().getSig());
                    }
                    
                    aux = aux.getSig();
                    contPos++;                
                } 
                this.actual--; 
            }             
        }    
   
    }

    @Override
    public void vaciarREC() {
        eliminarEnPosREC(actual);
    }
    
    public int eliminarEnPosREC(int posicion)
    {
        if(posicion==0) {
            this.actual=0;
            return 0;
        } else {
                if(inicio != null) {
                    this.setInicio(inicio.getSig());
                        return eliminarEnPosREC(posicion-1);
                }else{
                    return eliminarEnPosREC(0);
                }
            }
    }        

    @Override
    public void borrarOcurrenciaElemento(Object n) {

        NodoLista aux = this.inicio;
        if (!this.esVacia()) {
             while (aux != null ) 
             {
                 this.borrarElemento(n);
                 aux = aux.getSig();
             }
        }  
    }
    
    public NodoLista removeElements(NodoLista head, Object n) {
        if(head == null){
            return null;
        }
        
        // Llamada recursiva
        T a = (T) n ;
        NodoLista res = removeElements(head.getSig(),n);
        T datoCastAux = (T) head.getDato();
        
        if(datoCastAux.equals(n)){
            return res;
        }else{
            head.setSig(res); 
            return head;
        }
 
    }
       
    public void eliminarOcurrenciaRec(NodoLista aux,Object obj){    
   
    if(!this.esVacia() && aux != null){
     
        if (inicio.getDato().getO().equals(obj) ){
            this.inicio = this.inicio.getSig();
        } else {           
            NodoLista temporal,anterior;
            anterior= this.inicio;         
            temporal= this.inicio.getSig();
            
            if( temporal !=null && !temporal.getDato().getO().equals(obj) ){
                anterior= anterior.getSig();
                temporal= temporal.getSig();
            }
                 
            if(temporal != null && temporal.getDato().getO().equals(obj)){
                anterior.setSig(temporal.getSig());
                
                if(temporal==this.fin) {
                    this.fin=anterior;
                }
            }     
        }  
        eliminarOcurrenciaRec(aux.getSig(),obj);
    }     
}
    
    
    public boolean agregarFinalREC(NodoLista aux , TDato n) {
        if(this.esVacia()) {
            this.agregarInicio(n);
            return true;
        }
        if(aux.getSig()==null) {
            NodoLista nuevo = new NodoLista (n);
            aux.setSig(nuevo);
            this.fin = nuevo;
            this.actual++;
            return true;
        } else {
            return agregarFinalREC(aux.getSig() , n);
        }
        
    }

    }

