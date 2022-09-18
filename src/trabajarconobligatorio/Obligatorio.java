package trabajarconobligatorio;

import java.util.Date;
import Clases.Contacto;
import Clases.Fecha;
import Clases.Linea;
import Clases.Mensaje;
import Clases.Palabra;
import Estructuras.Listas.Lista;
import Estructuras.Listas.NodoLista;
import Estructuras.Listas.TDato;
import java.text.SimpleDateFormat;
import java.util.Arrays;


public class Obligatorio implements IObligatorio {
    
       
    Lista<Contacto> ListaContactos ;
    Lista<Palabra>Diccionario ;
    int max_cantidad_palabras_x_linea; 
    Lista<Fecha> ListaFechas ; 

    public Lista<Contacto> getListaContactos() {
        return ListaContactos;
    }

    public void setListaContactos(Lista<Contacto> ListaContactos) {
        this.ListaContactos = ListaContactos;
    }

    public Lista<Palabra> getDiccionario() {
        return Diccionario;
    }

    public void setDiccionario(Lista<Palabra> Diccionario) {
        this.Diccionario = Diccionario;
    }

    public int getMax_cantidad_palabras_x_linea() {
        return max_cantidad_palabras_x_linea;
    }

    public void setMax_cantidad_palabras_x_linea(int max_cantidad_palabras_x_linea) {
        this.max_cantidad_palabras_x_linea = max_cantidad_palabras_x_linea;
    }
 
    //PRE:  se ingresa la cantidad maxima de palabras admitidas por lineas para todos los mensajes 
    //POST: se crea el sistema    
    @Override
    public Retorno crearSistemaMensajes(int MAX_CANT_PALABRAS_X_LINEA) {
        
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;
        
        if( MAX_CANT_PALABRAS_X_LINEA>=1) {
            this.ListaContactos = new Lista();
            this.Diccionario=new Lista();
            this.max_cantidad_palabras_x_linea = MAX_CANT_PALABRAS_X_LINEA;
            ret.valorbooleano=true;
            this.ListaFechas=new Lista();
        }
        
        return ret;
    }
    
    //PRE:  que exista el sistema
    //POST: se elimina sistema
    @Override
    public Retorno destruirSistemaMensajes() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        this.ListaContactos = null;
        this.Diccionario=null;
        this.max_cantidad_palabras_x_linea = 0;
        this.ListaFechas=null;
        return ret;
    }
    
    //PRE:  sistema creado , ingreso de numero de contacto y su nombre
    //POST: se agrega a la lista el contacto a la lista sino existe en ella
    @Override
    public Retorno agregarContacto(int numContacto, String nomContacto) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;
        
        Contacto c = new Contacto(numContacto , nomContacto);

        if (!this.ListaContactos.buscarElemento(new TDato<Contacto>(c))) {
            ret.valorbooleano = this.ListaContactos.agregarFinal(new TDato<Contacto>(c));
        } else {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }
        
        return ret;
    }
    
    //PRE: el sistema este creado , ingreso de numero de contacto  
    //POST: se elimina el contacto si existe
    @Override
    public Retorno eliminarContacto(int numContacto) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        
        Contacto c = new Contacto(numContacto , null);
        
        if (this.ListaContactos.buscarElemento(new TDato<Contacto>(c))) {
             this.ListaContactos.borrarElemento(c);
         } else {
           ret = new Retorno(Retorno.Resultado.ERROR);
        }
        
        return ret;
    }
    //PRE: el sistema este creado , ingreso de numero de contacto de origen / destino y una fecha con formato valido.   
    //POST: se agrega el mensaje al contacto de origen , siempre y cuando existan los contactos. 

    @Override
    public Retorno agregarMensaje(int numContactoOrigen, int numContactoDestino, Date fecha) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        
        Contacto aux1 = new Contacto (numContactoOrigen , null);
        Contacto aux2 = new Contacto (numContactoDestino , null);
        
        NodoLista cOrigen = this.ListaContactos.obtenerElemento(aux1);
        NodoLista cDestino = this.ListaContactos.obtenerElemento(aux2);
        
        if (cOrigen != null && cDestino !=null) {
            
                Contacto contactoOrigen = (Contacto) cOrigen.getDato().getO();
                Contacto contactoDestino = (Contacto) cDestino.getDato().getO();  
                
               Mensaje mNuevo = new Mensaje( contactoOrigen, contactoDestino , fecha) ;
               mNuevo.setId(contactoOrigen.getMensajes().getActual()+1) ; 
                if (contactoOrigen.getMensajes().agregarFinal(new TDato<Mensaje>(mNuevo))) {
                    Fecha nuevaFecha = new Fecha(fecha);
                    TDato nfecha = new TDato<Fecha> (nuevaFecha);
                    if(!this.ListaFechas.buscarElemento(nfecha))
                    {
                        this.ListaFechas.agregarOrd(nfecha);
                    }
                                       
                } 

        } else {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }
        
        return ret;
    }
    //PRE: el sistema este creado , ingreso de numero de contacto  
    //POST: imprime la lista de mensajes de un contacto existente , en caso contrario se indica lista vacia
    public void MostrarMensajesContacto(int numContactoOrigen) {
        Contacto aux1 = new Contacto (numContactoOrigen , null);
        NodoLista cOrigen = this.ListaContactos.obtenerElemento(aux1);
        
        if(cOrigen!=null) {
             Contacto contactoDestino = (Contacto) cOrigen.getDato().getO();
            
             contactoDestino.getMensajes().mostrar();
        }
    }
    //PRE: el sistema este creado , ingreso de numero de contacto origen y numero de mensaje
    //POST: se elimina el mensaje al contacto seleccionado , siempre y cuando exista
    @Override
    public Retorno eliminarMensaje(int numContactoOrigen, int numMensaje) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        
        Contacto aux1 = new Contacto (numContactoOrigen , null);
        NodoLista cOrigen = this.ListaContactos.obtenerElemento(aux1);
        NodoLista NodoMensaje= null;
        
        if(cOrigen!=null) {
             Contacto contactoOrigen = (Contacto) cOrigen.getDato().getO();
             
             Mensaje men1 = new Mensaje();
             men1.setId(numMensaje);
             men1.setContactoOrigen(contactoOrigen);
             
             NodoMensaje = contactoOrigen.getMensajes().obtenerElemento(men1);
             
             if(NodoMensaje != null) {
                 contactoOrigen.getMensajes().borrarElemento(men1);
             } else {
                 ret = new Retorno(Retorno.Resultado.ERROR);
             }
        }
        if(cOrigen==null || NodoMensaje ==null) {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }
        
       return ret;
    } 
    
    //PRE: el sistema este creado , ingreso de numero de contacto y numero de mensaje
    //POST: imprime el texto de un contacto origen existente , en caso contrario se indica lista vacia

    @Override
    public Retorno imprimirTexto(int numContactoOrigen, int numMensaje) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        
        Contacto aux1 = new Contacto (numContactoOrigen , null);
        NodoLista cOrigen = this.ListaContactos.obtenerElemento(aux1);
        NodoLista NodoMensaje =null;
        
        if(cOrigen!=null) {
             Contacto contactoOrigen = (Contacto) cOrigen.getDato().getO();
             Mensaje men1 = new Mensaje();
             men1.setId(numMensaje);
             men1.setContactoOrigen(contactoOrigen);
             NodoMensaje = contactoOrigen.getMensajes().obtenerElemento(men1);
             //NodoMensaje =this.Mensajes.obtenerElemento(men1);
             
             if(NodoMensaje != null) {
                    Mensaje m = (Mensaje) NodoMensaje.getDato().getO();
            
                    if(m.Lineas.cantElementos() != 0) {
                        //Imprimimos lista
                       for(int i = 0; i < m.Lineas.cantElementos(); i++){
                       //Casteo ciudad de TDato
                       TDato c = (TDato) m.Lineas.datoEnPos(i);
                       Linea li = (Linea) c.getO();
                       System.out.print(i+1 + ": ");
               
                          for(int j = 0; j < li.Palabras.cantElementos(); j++){
                              TDato a = li.Palabras.datoEnPos(j); 
                              Palabra p = (Palabra) a.getO();
                              
                              System.out.print( p.toString() + "  ");
                            }
                            System.out.println();
                       }
                    } else {
                        System.out.println( "-- Texto vacio --");
                    }
             }
        }
        
        if (cOrigen==null || NodoMensaje ==null) {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }
        
        return ret;
    }
    
    //PRE: el sistema este creado , ingreso de numero de contacto y un numero de mensaje 
    //POST: ingresa una linea vacia al final , para un contacto de origen y numero de mensaje existente

    @Override
    public Retorno insertarLinea(int numContactoOrigen, int numMensaje) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        
        ret.valorbooleano =false;
        
        Contacto aux1 = new Contacto (numContactoOrigen , null);
        NodoLista cOrigen = this.ListaContactos.obtenerElemento(aux1);
        NodoLista NodoMensaje = null;
        
        if(cOrigen!=null) {
             Contacto contactoOrigen = (Contacto) cOrigen.getDato().getO();
             Mensaje men1 = new Mensaje();
             men1.setId(numMensaje);
             men1.setContactoOrigen(contactoOrigen);
             
             NodoMensaje = contactoOrigen.getMensajes().obtenerElemento(men1);
             boolean ok =NodoMensaje !=null;
             //NodoMensaje =this.Mensajes.obtenerElemento(men1);
             if(ok) {
                 Mensaje m = (Mensaje) NodoMensaje.getDato().getO();
                 Linea nuevaLinea = new Linea(this.max_cantidad_palabras_x_linea) ;
                   ret.valorbooleano=m.Lineas.agregarFinalREC(m.Lineas.getInicio() ,new TDato<Linea>(nuevaLinea));
             }
        }
        
        if (cOrigen==null || NodoMensaje ==null && !ret.valorbooleano) {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }
        
        return ret;
    }
    //PRE: el sistema este creado , ingreso de numero de contacto , un numero de mensaje y posicion de la linea . Se tiene en cuenta que la posicion inicial es 1 
    //POST: ingresa una linea vacia en la posicion indicada dentro de la posicion inicial y el limite de la lista de mensajes , para un contacto de origen y numero de mensaje existente
    @Override
    public Retorno insertarLineaEnPosicion(int numContactoOrigen, int numMensaje, int posicionLinea) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        
        Contacto aux1 = new Contacto (numContactoOrigen , null);
        NodoLista cOrigen = this.ListaContactos.obtenerElemento(aux1);
        NodoLista NodoMensaje =null;
        boolean posicionValida = false;
        
        if(cOrigen!=null) {
            
             Contacto contactoOrigen = (Contacto) cOrigen.getDato().getO();
             Mensaje men1 = new Mensaje();
             men1.setId(numMensaje);
             men1.setContactoOrigen(contactoOrigen);
             
             NodoMensaje = contactoOrigen.getMensajes().obtenerElemento(men1);   
             
             if(NodoMensaje !=null) {
                 Mensaje m = (Mensaje) NodoMensaje.getDato().getO();
                 Linea nuevaLinea = new Linea(this.max_cantidad_palabras_x_linea) ;
                               
                    if(m.Lineas.cantElementos() >=0 && posicionLinea-1<=m.Lineas.cantElementos() && posicionLinea>=1 ) {
                            m.Lineas.insertarEnPos(posicionLinea-1, new TDato<Linea>(nuevaLinea));
                            posicionValida = true;
                    } 
             }
        } 
        
        if (cOrigen==null || NodoMensaje ==null || posicionValida==false) {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }
        
        return ret;
    }
    //PRE: el sistema este creado , ingreso de numero de contacto , un numero de mensaje y posicion de la linea . Se tiene en cuenta que la posicion inicial es 1 
    //POST: elimina una linea en la posicion indicada dentro de la posicion inicial y el limite de la lista de mensajes , para un contacto de origen y numero de mensaje existente
    //se borra todos los nodos que esten comprendidos dentro de ella.
    @Override
    public Retorno borrarLinea(int numContactoOrigen, int numMensaje, int posicionLinea) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        
        Contacto aux1 = new Contacto (numContactoOrigen , null);
        NodoLista cOrigen = this.ListaContactos.obtenerElemento(aux1);
        NodoLista NodoMensaje =null;
        boolean posicionValida = false;
        
        if(cOrigen!=null) {
            
             Contacto contactoOrigen = (Contacto) cOrigen.getDato().getO();
             Mensaje men1 = new Mensaje();
             men1.setId(numMensaje);
             men1.setContactoOrigen(contactoOrigen);
             
             NodoMensaje = contactoOrigen.getMensajes().obtenerElemento(men1);
             
             if(NodoMensaje != null) {
                Mensaje m = (Mensaje) NodoMensaje.getDato().getO();
                if(m.Lineas.cantElementos() >=0 && posicionLinea-1<=m.Lineas.cantElementos() && posicionLinea>=1 ) {
                    m.Lineas.eliminarEnPos(posicionLinea-1);
                        posicionValida = true;
                }
             }
        }
        
        if (cOrigen==null || NodoMensaje ==null || posicionValida==false) {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }
        
        return ret;
    }
    //PRE: el sistema este creado , ingreso de numero de contacto , un numero de mensaje.
    //POST: Borra todas las lineas comprendidas para el contacto y mensaje existe en ese nodo.    
    @Override
    public Retorno borrarTodo(int numContactoOrigen, int numMensaje) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        
        Contacto aux1 = new Contacto (numContactoOrigen , null);
        NodoLista cOrigen = this.ListaContactos.obtenerElemento(aux1);
        NodoLista NodoMensaje=null;
        
        if(cOrigen!=null) {
            
             Contacto contactoOrigen = (Contacto) cOrigen.getDato().getO();
             Mensaje men1 = new Mensaje();
             men1.setId(numMensaje);
             men1.setContactoOrigen(contactoOrigen);
             
             NodoMensaje = contactoOrigen.getMensajes().obtenerElemento(men1);
             
             if(NodoMensaje != null) {
                Mensaje m = (Mensaje) NodoMensaje.getDato().getO();
                    m.Lineas.vaciarREC();
             }
             
        }   
         if (cOrigen==null || NodoMensaje ==null) {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }
        
        return ret;
    }
    //PRE: el sistema este creado , ingreso de numero de contacto , un numero de mensaje y palabra a borrar
    //POST: Borra todas las ocurrencias de la palabras en el mensaje del contacto exisitente.
    @Override
    public Retorno borrarOcurrenciasPalabraEnTexto(int numContactoOrigen, int numMensaje, String palabraABorrar) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        
        Contacto aux1 = new Contacto (numContactoOrigen , null);
        NodoLista cOrigen = this.ListaContactos.obtenerElemento(aux1);
        NodoLista NodoMensaje =null;
        
        if(cOrigen!=null) {
             Contacto contactoOrigen = (Contacto) cOrigen.getDato().getO();
             Mensaje men1 = new Mensaje();
             men1.setId(numMensaje);
             men1.setContactoOrigen(contactoOrigen);
             
             NodoMensaje = contactoOrigen.getMensajes().obtenerElemento(men1);
             
             if(NodoMensaje != null) {
                    Mensaje m = (Mensaje) NodoMensaje.getDato().getO();
            
                    if(m.Lineas.cantElementos() != 0) {
                       for(int i = 0; i < m.Lineas.cantElementos(); i++){
                       //Casteo ciudad de TDato
                       
                       TDato c = (TDato) m.Lineas.datoEnPos(i);
                       Linea li = (Linea) c.getO();
                       li.Palabras.eliminarOcurrenciaRec(li.Palabras.getInicio(), new Palabra (palabraABorrar));
                       
                       } 
                    }
            }
        }
        if (cOrigen==null || NodoMensaje ==null) {
                ret = new Retorno(Retorno.Resultado.ERROR);
            }
        return ret;
    }
        
    //PRE: el sistema este creado , ingreso de numero de contacto , un numero de mensaje , posicion de la linea y posicion de la palabra. Se tiene en cuenta que la posiciones iniciales comienzan en 1
    //POST: se inserta una palabra en una linea a un mensaje y contacto existentes, siempre que en al linea existan posiciones de palabras vacias. 
  
    
    @Override
    public Retorno insertarPalabraEnLinea(int numContactoOrigen, int numMensaje, int posicionLinea, int posicionPalabra, String palabraAIngresar) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        
        palabraAIngresar=palabraAIngresar.trim();
        Contacto aux1 = new Contacto (numContactoOrigen , null);
        NodoLista cOrigen = this.ListaContactos.obtenerElemento(aux1);
        NodoLista NodoMensaje = null;
        TDato datoLinea = null;
        boolean tieneEspacios = this.TieneEspaciosEnBlanco(palabraAIngresar);
        
        if(cOrigen!=null && !tieneEspacios) {
            
             Contacto contactoOrigen = (Contacto) cOrigen.getDato().getO();
             Mensaje men1 = new Mensaje();
             men1.setId(numMensaje);
             men1.setContactoOrigen(contactoOrigen);
             
             NodoMensaje = contactoOrigen.getMensajes().obtenerElemento(men1);
             
             if(NodoMensaje !=null) {
                 Mensaje m = (Mensaje) NodoMensaje.getDato().getO();
                 //Falto validacion
                 if(m.Lineas.cantElementos() > posicionLinea-1) {
                     datoLinea = m.Lineas.datoEnPos(posicionLinea-1);
              
                    if(datoLinea !=null) {
                        Linea lineaActual = (Linea) datoLinea.getO();   

                        if(lineaActual.Palabras.puedoInsertar()) {
                            lineaActual.Palabras.insertarEnPos(posicionPalabra-1, new TDato<Palabra> (new Palabra (palabraAIngresar)) );
                       }
                    }
                 }
                                
             }
        } 
        
        if (cOrigen==null || NodoMensaje ==null || datoLinea ==null ) {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }
        
        return ret;
    }
    
    //PRE: el sistema este creado , ingreso de numero de contacto , un numero de mensaje , posicion de la linea y posicion de la palabra. Se tiene en cuenta que la posiciones iniciales comienzan en 1
    //POST: se inserta una palabra en una linea a un mensaje y contacto existentes, 
    //en caso de que la linea este completa de palabras , se creara una nueva linea y se desplazara todas las palabras siguientes a la poscion de palabra ingresada
    
    @Override
    public Retorno insertarPalabraYDesplazar(int numContactoOrigen, int numMensaje, int posicionLinea, int posicionPalabra, String palabraAIngresar) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        Contacto aux1 = new Contacto (numContactoOrigen , null);
        NodoLista cOrigen = this.ListaContactos.obtenerElemento(aux1);
        NodoLista NodoMensaje = null; 
        TDato datoLinea = null;
        boolean tieneEspacios = this.TieneEspaciosEnBlanco(palabraAIngresar);
        
        if(cOrigen!=null && !tieneEspacios) {
            
             Contacto contactoOrigen = (Contacto) cOrigen.getDato().getO();
             Mensaje men1 = new Mensaje();
             men1.setId(numMensaje);
             men1.setContactoOrigen(contactoOrigen);
             
             NodoMensaje = contactoOrigen.getMensajes().obtenerElemento(men1);
             
             if(NodoMensaje !=null) {
                 
                 Mensaje m = (Mensaje) NodoMensaje.getDato().getO();
                 datoLinea = m.Lineas.datoEnPos(posicionLinea-1);
                 
                 if(datoLinea !=null) {
                     Linea lineaActual = (Linea) datoLinea.getO();   
                                          
                     if(lineaActual.Palabras.puedoInsertar()) {
                         lineaActual.Palabras.insertarEnPos(posicionPalabra-1, new TDato<Palabra> (new Palabra (palabraAIngresar)) );
                    } else {
                         NodoLista NodoUltimo = null ;
                         NodoLista NodoAnterior= null;
                        
                         int i = posicionLinea -1 ;
                         
                         int cantidadInicial= m.Lineas.cantElementos() ;                        
                          
                         while (  i <= m.Lineas.cantElementos()-1 ) {
                             
                            datoLinea = m.Lineas.datoEnPos(i);
                            
                            if(datoLinea !=null) {
                                
                                lineaActual = (Linea) datoLinea.getO();  
                                
                                if(i==m.Lineas.cantElementos()-1 && !lineaActual.Palabras.puedoInsertar()) {
                                    Linea nuevaLinea = new Linea(this.max_cantidad_palabras_x_linea) ;
                                    m.Lineas.agregarFinal(new TDato<Linea>(nuevaLinea));
                                }
                                
                                if (i==posicionLinea -1) {
                                    NodoUltimo = lineaActual.Palabras.getFin(); 
                                    lineaActual.Palabras.borrarFin();
                                    lineaActual.Palabras.insertarEnPos(posicionPalabra-1, new TDato<Palabra> (new Palabra (palabraAIngresar)) );
                                }
                  
                                if(lineaActual.Palabras.puedoInsertar() && i>posicionLinea -1) {
                                    lineaActual.Palabras.agregarInicio(NodoUltimo.getDato());
                                } else if (!lineaActual.Palabras.puedoInsertar() && i>posicionLinea -1) {
                                    NodoAnterior = lineaActual.Palabras.getFin(); 
                                    lineaActual.Palabras.borrarFin();
                                    lineaActual.Palabras.agregarInicio(NodoUltimo.getDato());
                                    NodoUltimo=NodoAnterior;
                                } 
                            }
                            i++;
                         } 
                     }
                 }       
             }
        } 
        
        if (cOrigen==null || NodoMensaje ==null || datoLinea ==null ) {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }
        
        return ret;
    }
    
    //PRE: el sistema este creado , ingreso de numero de contacto , un numero de mensaje y posicion de palabra a borrar.Se tiene en cuenta que la posiciones iniciales comienzan en 1
    //POST: Borra la palabra en la posicion indicada , para un contacto con mensaje y lineas existentes. Solo se borrara en posiciones de palabras validas . 

    @Override
    public Retorno borrarPalabra(int numContactoOrigen, int numMensaje, int posicionLinea, int posicionPalabra) {
        
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        
        Contacto aux1 = new Contacto (numContactoOrigen , null);
        NodoLista cOrigen = this.ListaContactos.obtenerElemento(aux1);
        NodoLista NodoMensaje = null; 
        TDato datoLinea = null;
        
        if(cOrigen!=null) {
            
             Contacto contactoOrigen = (Contacto) cOrigen.getDato().getO();
             Mensaje men1 = new Mensaje();
             men1.setId(numMensaje);
             men1.setContactoOrigen(contactoOrigen);
             
             NodoMensaje = contactoOrigen.getMensajes().obtenerElemento(men1);
             
             if(NodoMensaje !=null) {
                 Mensaje m = (Mensaje) NodoMensaje.getDato().getO();
                 datoLinea = m.Lineas.datoEnPos(posicionLinea-1);
                 
              
                 if(datoLinea !=null ) {
                     Linea lineaActual = (Linea) datoLinea.getO();   
                     if(posicionPalabra -1 <= lineaActual.Palabras.cantElementos()) {
                         lineaActual.Palabras.eliminarEnPos(posicionPalabra-1);
                     }
                        
                 }               
             }
        }  
        
        if (cOrigen==null || NodoMensaje ==null || datoLinea ==null ) {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }
        
        return ret;
    }
    //PRE: el sistema este creado , ingreso de numero de contacto , un numero de mensaje, pocion de la linea y palabra a borrar. Se tiene en cuenta que la posiciones iniciales comienzan en 1
    //POST: Borra todas las ocurrencias de la palabras en la linea indicada , para una posicion valida de un contacto y mensaje existe.
    @Override
    public Retorno borrarOcurrenciasPalabraEnLinea(int numContactoOrigen, int numMensaje, int posicionLinea, String palabraABorrar) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        
        Contacto aux1 = new Contacto (numContactoOrigen , null);
        NodoLista cOrigen = this.ListaContactos.obtenerElemento(aux1);
        NodoLista NodoMensaje = null;
        TDato datoLinea=null;
        
        if(cOrigen!=null) {
            
             Contacto contactoOrigen = (Contacto) cOrigen.getDato().getO();
             Mensaje men1 = new Mensaje();
             men1.setId(numMensaje);
             men1.setContactoOrigen(contactoOrigen);
             
             NodoMensaje = contactoOrigen.getMensajes().obtenerElemento(men1);
             
             if(NodoMensaje !=null) {
                 //Falta controlar las posicion de la linea
                 Mensaje m = (Mensaje) NodoMensaje.getDato().getO();
                 datoLinea = m.Lineas.datoEnPos(posicionLinea-1);
                 m.Lineas.cantElementos(); 
              
                 if(datoLinea !=null) {
                     Linea lineaActual = (Linea) datoLinea.getO();   
                     lineaActual.Palabras.borrarOcurrenciaElemento(new Palabra (palabraABorrar));
                 }               
             }
        } 
        
        if (cOrigen==null || NodoMensaje ==null || datoLinea ==null ) {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }

        return ret;
    }
    
    //PRE: el sistema este creado , ingreso de numero de contacto , numero de mensaje y posicion de la linea. Se tiene en cuenta que la posiciones iniciales comienzan en 1
    //POST: imprime la lista de palabras de una linea de un mensaje con contacto origen existente , en caso contrario se indica lista vacia.
    
    @Override
    public Retorno imprimirLinea(int numContactoOrigen, int numMensaje, int posicionLinea) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        Contacto aux1 = new Contacto (numContactoOrigen , null);
        NodoLista cOrigen = this.ListaContactos.obtenerElemento(aux1);
        NodoLista NodoMensaje=null;
        
        if(cOrigen!=null) {
             Contacto contactoOrigen = (Contacto) cOrigen.getDato().getO();
             Mensaje men1 = new Mensaje();
             men1.setId(numMensaje);
             men1.setContactoOrigen(contactoOrigen);
             
             NodoMensaje = contactoOrigen.getMensajes().obtenerElemento(men1);
             
             if(NodoMensaje!=null) {
             Mensaje m = (Mensaje) NodoMensaje.getDato().getO();
             int cantidad = m.Lineas.cantElementos();
            
            if(cantidad > 0 && posicionLinea < cantidad ) {

               //Casteo ciudad de TDato
               TDato c = (TDato) m.Lineas.datoEnPos(posicionLinea);
               Linea li = (Linea) c.getO();
               
               System.out.print( posicionLinea + " : ");
               for(int j = 0; j < li.Palabras.cantElementos(); j++){
                      TDato a = li.Palabras.datoEnPos(j); 
                      Palabra p = (Palabra) a.getO();
                      System.out.print( p.toString() + "  ");
               }
               System.out.println();
            } else {
                System.out.println(m.id + "-- Linea vacio --");
            }
            }   
        }
        return ret;
    }
    
    //PRE: el sistema este creado , ingreso de palabra
    //POST: se agrega en una lista ordenada de palabras , siempre y cuando esta no se repita

    @Override
    public Retorno ingresarPalabraDiccionario(String palabraAIngresar) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        palabraAIngresar=palabraAIngresar.toUpperCase().trim();
        Palabra nuevaPalabra=new Palabra(palabraAIngresar);
        boolean tieneEspacios= this.TieneEspaciosEnBlanco(palabraAIngresar.trim());
        if(!this.Diccionario.buscarElemento(new TDato<Palabra>(nuevaPalabra)) && !tieneEspacios) {
              this.Diccionario.agregarOrd(new TDato<Palabra>(nuevaPalabra));
        } else {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }
        return ret;
    }
    //PRE: el sistema este creado , ingreso de palabra
    //POST: se borra de la lista ordenada la palabra ingresa , siempre y cuando esta exista
    @Override
    public Retorno borrarPalabraDiccionario(String palabraABorrar) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        
        palabraABorrar=palabraABorrar.toUpperCase().trim();
       
        Palabra borrar = new Palabra(palabraABorrar);
        
        if(this.Diccionario.buscarElemento(new TDato<Palabra>(borrar))) {
            this.Diccionario.borrarElemento(borrar);
        }else {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }

        return ret;
    }

    //PRE: el sistema este creado
    //POST: se imprime la lista ordenada de palabras
    @Override
    public Retorno imprimirDiccionario() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        Diccionario.mostrar();        
        return ret;
    }
    
    //PRE: el sistema este creado
    //POST: se imprime que no esten contenidas en el diccionario , se recorre todas las listas de los mensajes y se compara con las palabras que existen en el diccionario

    @Override
    public Retorno ImprimirTextoIncorrecto() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        //Recorrer el texto y solo borrar las que no estan
        if(!this.ListaContactos.esVacia()) {
            for(int i= 0 ; i<= this.ListaContactos.cantElementos()-1 ;i++) {
            NodoLista cOrigen = this.ListaContactos.NodoDatoEnPos(i);
            if(cOrigen!=null) { 
                Contacto contactoOrigen = (Contacto) cOrigen.getDato().getO();
                for(int j=0; j<=contactoOrigen.getMensajes().cantElementos()-1; j++) 
                {
                    NodoLista NodoMensaje= contactoOrigen.getMensajes().NodoDatoEnPos(j);
                        
                    if(NodoMensaje != null) {
                    Mensaje m = (Mensaje) NodoMensaje.getDato().getO();
                                       
                    System.out.println(m.toString());
                    System.out.println();
                    if(m.Lineas.cantElementos() != 0) {
                        //Imprimimos lista
                       for(int x = 0; x < m.Lineas.cantElementos(); x++){
                       //Casteo ciudad de TDato
                       TDato c = (TDato) m.Lineas.datoEnPos(x);
                       Linea li = (Linea) c.getO();
                       System.out.print(x+1 + ": ");
               
                          for(int y = 0; y < li.Palabras.cantElementos(); y++){
                              TDato a = li.Palabras.datoEnPos(y); 
                              Palabra p = (Palabra) a.getO();
                              p.dato=p.dato.toUpperCase();
                              
                              if(!this.Diccionario.buscarElemento(new TDato<Palabra>(p))) {
                                  System.out.print( p.toString() + "  ");
                              } 
                            }  
                                System.out.println();
                            }
                            System.out.println();
                            
                       } else {
                        System.out.println( "-- Texto vacio --");
                        System.out.println();
                    }
                    System.out.println("----------------------------------------------------------------");
                    }                     
                }  
              }
           }
        }else {
            System.out.println("No hay contactos con mensajes disponibles");
        }
        
       
        return ret;
    }
    //PRE: el sistema este creado , ingresar un numero de contacto
    //POST: se genera una matriz depenedieno de los datos existentes en el sistema y se imprime esta.
    @Override
    public Retorno cantidadDeMensajes(int numContactoOrigen) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        //Se crea e inicia una matriz con valores int en cero
        int [][] registro = new int [0][0] ;
        Arrays.fill(registro, 0);
        int filas = 0;
        int columnas = 0;
        Contacto aux1 = new Contacto (numContactoOrigen , null);
        NodoLista cOrigen = this.ListaContactos.obtenerElemento(aux1);
        //Lista auxiliar para guardar los nombres sin repetir para crear las columnas
        Lista<String> listaContactoAux = new Lista();
        //Bloque de codigo para parametrizar la matriz
        if(cOrigen!=null) {
            Contacto contactoOrigen = (Contacto) cOrigen.getDato().getO();
            for(int j=0; j<=contactoOrigen.getMensajes().cantElementos()-1; j++) 
                {
                    NodoLista NodoMensaje= contactoOrigen.getMensajes().NodoDatoEnPos(j);
                    if(NodoMensaje != null) {
                    Mensaje m = (Mensaje) NodoMensaje.getDato().getO();
                        String nombreContacto = m.ContactoDestino.getNombre() + "" ;
                        TDato datoNombre = new TDato(nombreContacto);
                        if(!listaContactoAux.buscarElemento(datoNombre)) {
                                listaContactoAux.agregarFinal(datoNombre);
                            }     
                    }
                }
        //Se dimenciona las filas y columnas de la matriz    
        filas=listaContactoAux.cantElementos();
        columnas=this.ListaFechas.cantElementos();  
                            
        registro = new int [filas][columnas];
         //Se recorren todos los mensajes que tiene disponible el contacto de origen                
        for(int j=0; j<=contactoOrigen.getMensajes().cantElementos()-1; j++) 
        {
          NodoLista NodoMensaje= contactoOrigen.getMensajes().NodoDatoEnPos(j);
          if(NodoMensaje != null) 
          {
           Mensaje m = (Mensaje) NodoMensaje.getDato().getO();
                  for(int i=0 ;i<filas;i++) 
                  {
                    String NombreContacto = (String)listaContactoAux.datoEnPos(i).getO();
                    for(int x=0 ; x<columnas ; x++) 
                    {  
                          Fecha fechaComparar = (Fecha)this.ListaFechas.datoEnPos(x).getO();
                          //Se aplica un contador para cada fila y columna dada para un contacto y fecha dada
                           if(fechaComparar.getFecha().compareTo(m.getFecha())==0 && NombreContacto.equals(m.ContactoDestino.getNombre())) {
                             registro[i][x] ++; 
                          }
                    }
             }                                      
           }
        }     
        }
        
        if(registro.length==0) {
            System.out.println("--- No hay registors disponibles ---");
        } else {
            //Se utiliza una funcion auxiliar para imprimir los datos de la matriz 
           System.out.println(mostrarmatriz(registro, listaContactoAux , this.ListaFechas)); 
        }

        return ret;
    }
    
    public String mostrarmatriz(int[][] m ,Lista<String> filasContactos , Lista<Fecha> columnasFecha ) {
        String  ret = "";
        int filas=m.length;
        int columnas=m[0].length;
        int maxCaracteres = maxCaracteresPalabra(filasContactos);
        ret = "\n";
        ret += generarEspacios(maxCaracteres);
         for(int a=0 ;a<columnas;a++) {
             Fecha fechaComparar = (Fecha)columnasFecha.datoEnPos(a).getO();
             ret+= fechaComparar.toString() + "  ";
         }
        ret=ret+"\n";
        for(int i=0 ;i<filas;i++) {
            String NombreContacto = (String)filasContactos.datoEnPos(i).getO();
            ret+=NombreContacto + generarEspacios(maxCaracteres-NombreContacto.length());
            for(int j=0 ; j<columnas ; j++) 
            {
                ret=ret+"      "+m[i][j]+"    ";
            }
            ret=ret+"\n";
        }
        
        return ret ;
    }
    
    //Funcion aux verificar espacios en blanco
    private boolean TieneEspaciosEnBlanco(String palabra) {
        int i =0 ; 
        while(i<palabra.length())
        {
            if(palabra.charAt(i)== ' '){
                return true;
            }
            i++;
        }
        
        return false;
    }
    
    //Funcion para generar espacios en blanco
    private String generarEspacios (int cantidadEspacios) {
    String espacios = "";
        for(int i=0;i<=cantidadEspacios;i++) 
        {
            espacios+=" ";
        }
        return espacios;
    }
    //Funcion para obtener la cantidad maxima de carateres de una lista de nombres de contacto
     private int maxCaracteresPalabra ( Lista<String> nombres) {
     int cantidad = 0 ;
     for(int i=0;i<=nombres.cantElementos()-1;i++)
     {
         String nombre= (String)nombres.datoEnPos(i).getO();
         if(nombre.length() > cantidad) {
             cantidad = nombre.length();
         }
         
     }
     return cantidad;
     }
     
    @Override
    public Retorno imprimirContactos() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorString = "";
        for (int i=0;i<this.ListaContactos.cantElementos();i++) {
            TDato c = getListaContactos().datoEnPos(i);
            Contacto con = (Contacto) c.getO();
            ret.valorString +="\n"+con.getNumero() + "-";
            ret.valorString +=con.getNombre() + "\n";
        }
        return ret;
    }
    
    @Override
    public Retorno imprimircontactoMaxCantMensajes(){
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorString = "";
        double max = Double.NEGATIVE_INFINITY;
        Contacto conMax = null;
        for (int i = 0; i<this.ListaContactos.cantElementos();i++) {
            TDato c = getListaContactos().datoEnPos(i);
            Contacto con = (Contacto) c.getO();
            double cantMensajes = con.getMensajes().cantElementos();
            if(cantMensajes>max) {
                conMax = con;
                max = cantMensajes;
            }
        }
        ret.valorString = "\nEl contacto con mas mensajes es " +conMax.getNumero()+ " - " + conMax.getNombre();
        return ret;
    }
}
