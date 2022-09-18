
package trabajarconobligatorio;
import java.util.Date;
/**
 *
 * @author Rafael
 */
public interface IObligatorio {
    //PRE:  se ingresa la cantidad maxima de palabras admitidas por lineas para todos los mensajes 
    //POST: se crea el sistema       
Retorno crearSistemaMensajes(int MAX_CANT_PALABRAS_X_LINEA);

    //PRE:  que exista el sistema
    //POST: se elimina sistema
Retorno destruirSistemaMensajes();

    //PRE:  sistema creado , ingreso de numero de contacto y su nombre
    //POST: se agrega a la lista el contacto a la lista sino existe en ella
Retorno agregarContacto(int numContacto, String nomContacto);

     //PRE: el sistema este creado , ingreso de numero de contacto  
    //POST: se elimina el contacto si existe
Retorno eliminarContacto(int numContacto);

    //PRE: el sistema este creado , ingreso de numero de contacto de origen / destino y una fecha con formato valido.   
    //POST: se agrega el mensaje al contacto de origen , siempre y cuando existan los contactos.
Retorno agregarMensaje(int numContactoOrigen, int numContactoDestino, Date fecha);
    //PRE: el sistema este creado , ingreso de numero de contacto origen y numero de mensaje
    //POST: se elimina el mensaje al contacto seleccionado , siempre y cuando exista
Retorno eliminarMensaje(int numContactoOrigen, int numMensaje);

    //PRE: el sistema este creado , ingreso de numero de contacto y numero de mensaje
    //POST: imprime el texto de un contacto origen existente , en caso contrario se indica lista vacia
Retorno imprimirTexto(int numContactoOrigen, int numMensaje);

    //PRE: el sistema este creado , ingreso de numero de contacto y un numero de mensaje 
    //POST: ingresa una linea vacia al final , para un contacto de origen y numero de mensaje existente
Retorno insertarLinea(int numContactoOrigen, int numMensaje);

    //PRE: el sistema este creado , ingreso de numero de contacto , un numero de mensaje y posicion de la linea . Se tiene en cuenta que la posicion inicial es 1 
    //POST: ingresa una linea vacia en la posicion indicada dentro de la posicion inicial y el limite de la lista de mensajes , para un contacto de origen y numero de mensaje existente
Retorno insertarLineaEnPosicion(int numContactoOrigen, int numMensaje, int posicionLinea);

    //PRE: el sistema este creado , ingreso de numero de contacto , un numero de mensaje y posicion de la linea . Se tiene en cuenta que la posicion inicial es 1 
    //POST: elimina una linea en la posicion indicada dentro de la posicion inicial y el limite de la lista de mensajes , para un contacto de origen y numero de mensaje existente
    //se borra todos los nodos que esten comprendidos dentro de ella.
Retorno borrarLinea(int numContactoOrigen, int numMensaje, int posicionLinea);

    //PRE: el sistema este creado , ingreso de numero de contacto , un numero de mensaje.
    //POST: Borra todas las lineas comprendidas para el contacto y mensaje existe en ese nodo. 
Retorno borrarTodo(int numContactoOrigen, int numMensaje);

    //PRE: el sistema este creado , ingreso de numero de contacto , un numero de mensaje y palabra a borrar
    //POST: Borra todas las ocurrencias de la palabras en el mensaje del contacto exisitente.
Retorno borrarOcurrenciasPalabraEnTexto(int numContactoOrigen, int numMensaje, String palabraABorrar);

    //PRE: el sistema este creado , ingreso de numero de contacto , un numero de mensaje , posicion de la linea y posicion de la palabra. Se tiene en cuenta que la posiciones iniciales comienzan en 1
    //POST: se inserta una palabra en una linea a un mensaje y contacto existentes, siempre que en al linea existan posiciones de palabras vacias. 
Retorno insertarPalabraEnLinea(int numContactoOrigen, int numMensaje, int posicionLinea, int posicionPalabra, String palabraAIngresar);

    //PRE: el sistema este creado , ingreso de numero de contacto , un numero de mensaje , posicion de la linea y posicion de la palabra. Se tiene en cuenta que la posiciones iniciales comienzan en 1
    //POST: se inserta una palabra en una linea a un mensaje y contacto existentes, 
    //en caso de que la linea este completa de palabras , se creara una nueva linea y se desplazara todas las palabras siguientes a la poscion de palabra ingresada
Retorno insertarPalabraYDesplazar(int numContactoOrigen, int numMensaje, int posicionLinea, int posicionPalabra, String palabraAIngresar);

    //PRE: el sistema este creado , ingreso de numero de contacto , un numero de mensaje y posicion de palabra a borrar.Se tiene en cuenta que la posiciones iniciales comienzan en 1
    //POST: Borra la palabra en la posicion indicada , para un contacto con mensaje y lineas existentes. Solo se borrara en posiciones de palabras validas .
Retorno borrarPalabra(int numContactoOrigen, int numMensaje, int posicionLinea, int posicionPalabra);

    //PRE: el sistema este creado , ingreso de numero de contacto , un numero de mensaje, pocion de la linea y palabra a borrar. Se tiene en cuenta que la posiciones iniciales comienzan en 1
    //POST: Borra todas las ocurrencias de la palabras en la linea indicada , para una posicion valida de un contacto y mensaje existe.
Retorno borrarOcurrenciasPalabraEnLinea(int numContactoOrigen, int numMensaje, int posicionLinea , String palabraABorrar);

    //PRE: el sistema este creado , ingreso de numero de contacto , numero de mensaje y posicion de la linea. Se tiene en cuenta que la posiciones iniciales comienzan en 1
    //POST: imprime la lista de palabras de una linea de un mensaje con contacto origen existente , en caso contrario se indica lista vacia.
Retorno imprimirLinea(int numContactoOrigen, int numMensaje, int posicionLinea);

    //PRE: el sistema este creado , ingreso de palabra
    //POST: se agrega en una lista ordenada de palabras , siempre y cuando esta no se repita
Retorno ingresarPalabraDiccionario(String palabraAIngresar);

    //PRE: el sistema este creado , ingreso de palabra
    //POST: se borra de la lista ordenada la palabra ingresa , siempre y cuando esta exista
Retorno borrarPalabraDiccionario(String palabraABorrar);
    
    //PRE: el sistema este creado
    //POST: se imprime la lista ordenada de palabras
Retorno imprimirDiccionario();

    //PRE: el sistema este creado
    //POST: se imprime que no esten contenidas en el diccionario , se recorre todas las listas de los mensajes y se compara con las palabras que existen en el diccionario
Retorno ImprimirTextoIncorrecto();

    //PRE: el sistema este creado , ingresar un numero de contacto
    //POST: se genera una matriz depenedieno de los datos existentes en el sistema y se imprime esta.
Retorno cantidadDeMensajes(int numContactoOrigen);

Retorno imprimirContactos();

Retorno imprimircontactoMaxCantMensajes();

}




