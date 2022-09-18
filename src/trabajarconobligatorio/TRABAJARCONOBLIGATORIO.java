package trabajarconobligatorio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rafael
 */
public class TRABAJARCONOBLIGATORIO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        Obligatorio obl = new Obligatorio();
        Prueba p = new Prueba();
        juegodepruebadefensa(obl, p); // juego de prueba ejemplo
    }
    
    public static void juegodepruebadefensa(Obligatorio obl, Prueba p) throws ParseException {
        // escriba su juego de prueba aqui
        int MAX_CANT_PALABRAS_X_LINEA = 3;
        
        p.ver(obl.crearSistemaMensajes(MAX_CANT_PALABRAS_X_LINEA).resultado, Retorno.Resultado.OK, "Se creara sistma para 3 palabras por linea");
        p.ver(obl.agregarContacto(1, "Carlos").resultado, Retorno.Resultado.OK, "se agrega contacto Carlos  al sistema");
        p.ver(obl.agregarContacto(1, "Carlos").resultado, Retorno.Resultado.ERROR, "se intenta agregar contacto Carlos que ya existe");
        p.ver(obl.eliminarContacto(1).resultado, Retorno.Resultado.OK, "se elimina contacto Carlos  del sistema");
       
        p.ver(obl.agregarContacto(1, "Juan").resultado, Retorno.Resultado.OK, "se agrega contacto Juan Perez al sistema");
        p.ver(obl.agregarContacto(2, "Pedro").resultado, Retorno.Resultado.OK, "se agrega contacto Juan Perez al sistema");
        p.ver(obl.agregarContacto(3, "Ana").resultado, Retorno.Resultado.OK, "se agrega contacto Juan Perez al sistema");
        p.ver(obl.agregarContacto(4, "Maria").resultado, Retorno.Resultado.OK, "se agrega contacto Juan Perez al sistema");
        
        // ----------------------
        // Mostramos contactos - IMPLEMENTAR Y AGREGAR EN INRERFACE
            obl.getListaContactos().mostrar();
            
        //-----------------------    
        
        // definimos una fecha        
        Date fecha=new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        fecha=formato.parse("23/03/2022");

        System.out.println("MENSAJES DE LA FECHA 23/03/2022");
        
        // Agregamos 3 mensajes enviados por Ana a contacto Pedro que Existe
        p.ver(obl.agregarMensaje(1,2, fecha).resultado, Retorno.Resultado.OK, "Se agrega mensaje 1 enviado de Ana a pedro");
        p.ver(obl.agregarMensaje(1,2, fecha).resultado, Retorno.Resultado.OK, "Se agrega mensaje 2 enviado de Ana a pedro ");
        p.ver(obl.agregarMensaje(1,2, fecha).resultado, Retorno.Resultado.OK, "Se agrega mensaje 3 enviado de Ana a pedro");
 
       // Agregamos 3 mensajes enviados por juan a contacto Pedro que Existe        
        p.ver(obl.agregarMensaje(3,2, fecha).resultado, Retorno.Resultado.OK, "Se agrega mensaje 4 enviado de juan a pedro");
        p.ver(obl.agregarMensaje(3,2, fecha).resultado, Retorno.Resultado.OK, "Se agrega mensaje 5 enviado de juan a pedro ");
        p.ver(obl.agregarMensaje(3,2, fecha).resultado, Retorno.Resultado.OK, "Se agrega mensaje 6 enviado de juan a pedro");
  
  // Agregamos 3 mensajes enviados por juan a contacto Ana que Existe       
        p.ver(obl.agregarMensaje(2,3, fecha).resultado, Retorno.Resultado.OK, "Se agrega mensaje 1 enviado de juan a Ana");
        p.ver(obl.agregarMensaje(2,3, fecha).resultado, Retorno.Resultado.OK, "Se agrega mensaje 2 enviado de juan a Ana ");
        p.ver(obl.agregarMensaje(2,3, fecha).resultado, Retorno.Resultado.OK, "Se agrega mensaje 3 enviado de juan a Ana");       

        // Agregamos 1 mensajes enviado por juan a contacto 8 que NO Existe
        p.ver(obl.agregarMensaje(1,8, fecha).resultado, Retorno.Resultado.ERROR, "Se agrega mensaje de juan  a contacto inexistente ");
 
        // Se elimina mensaje 1 de pedro 
        p.ver(obl.eliminarMensaje(2, 1).resultado, Retorno.Resultado.OK, "Se elimina el mensaje 1 al contacto 2");       
       // Se elimina mensaje con error
        p.ver(obl.eliminarMensaje(3, 5).resultado, Retorno.Resultado.ERROR, "Se elimina el mensaje 5 inexistente al contacto 3");
        p.ver(obl.eliminarMensaje(9, 1).resultado, Retorno.Resultado.ERROR, "Se elimina el mensaje 1 al contacto 9 inexistente");     
 
       // se agregan lineas con error
        p.ver(obl.insertarLinea(9, 1).resultado, Retorno.Resultado.ERROR, "Agrego una linea a contacto inexistente");
        p.ver(obl.insertarLinea(2, 9).resultado, Retorno.Resultado.ERROR, "Agrego una linea a mensaje inexistente");
        
        // Agregamos lineas al mensaje 2 de pedro
        p.ver(obl.insertarLinea(2, 1).resultado, Retorno.Resultado.ERROR, "Agrego una linea al mensaje 1 del contacto 2");
        //Resultado deberia ser ERROR porque ya no existe mensaje con id 1
        // Agregamos lineas al mensaje 2 de pedro en posicion indicada
        p.ver(obl.insertarLineaEnPosicion(2, 2, 1).resultado, Retorno.Resultado.OK, "Se agrega linea 1 en blanco al mensaje 2 de Pedro");
        p.ver(obl.insertarLineaEnPosicion(2, 2, 2).resultado, Retorno.Resultado.OK, "Se agrega linea 2 en blanco al mensaje 2 de Pedro");
        p.ver(obl.insertarLineaEnPosicion(2, 2, 3).resultado, Retorno.Resultado.OK, "Se agrega linea 3 en blanco al mensaje 2 de Pedro");
   
      // MOSTRAR LINEAS DEL MENSAJE DE PEDRO implementar en interface
        p.ver(obl.imprimirLinea(2,2 ,1 ).resultado, Retorno.Resultado.OK, "linea 1 mensaje 1 de pedro");
        p.ver(obl.imprimirLinea(2,2 ,2 ).resultado, Retorno.Resultado.OK, "linea 2 mensaje 1 de pedro ");   
        p.ver(obl.imprimirLinea(2,2 ,3 ).resultado, Retorno.Resultado.OK, "linea 3 mensaje 1 de pedro");
        
        // Eliminamos lineas al mensaje 2 de Pedro
        p.ver(obl.borrarLinea(2, 2, 1).resultado, Retorno.Resultado.OK, "Se elimina la linea 1 del mensaje 2 de Pedro");
        p.ver(obl.borrarLinea(9, 2, 1).resultado, Retorno.Resultado.ERROR, "Se elimina la linea 1 del mensaje 2 de contacto inexistente");
        p.ver(obl.borrarLinea(2, 9, 1).resultado, Retorno.Resultado.ERROR, "Se elimina la linea 1 del mensaje inexistente de Pedro");
        p.ver(obl.borrarLinea(2, 2, 3).resultado, Retorno.Resultado.OK, "Se elimina una linea inexistente del mensaje 2 de Pedro");
        //Linea 3 en mensaje 2 de Pedro si existe, deberia retornar OK
        
        //Mostrar mensje 2 de pedro 
        p.ver(obl.imprimirTexto(2, 2).resultado,Retorno.Resultado.OK,"Se imprime texto mensaje 2 de pedro que esta vacio y tiene lineas");
        
        // Eliminamos todas las lineas del mensaje 2 de Pedro
        p.ver(obl.borrarTodo(2, 2).resultado, Retorno.Resultado.OK, "Borramos todas las lineas del mensaje 2 de Pedro");
        p.ver(obl.borrarTodo(9, 2).resultado, Retorno.Resultado.ERROR, "Borramos todas las lineas del mensaje 2 de contacto inexistente");
        p.ver(obl.borrarTodo(2, 9).resultado, Retorno.Resultado.ERROR, "Borramos todas las lineas del mensaje inexistente de Pedro");
   
        // MOSTRAR LINEAS DEL MENSAJE DE PEDRO 
        p.ver(obl.imprimirLinea(2,1 ,1 ).resultado, Retorno.Resultado.OK, "linea 1 mensaje 1 de pedro despues de borrar todo");
        // no debe mostrar lineas del mensaje 2 de pedro
        // Agregamos palabras al mensaje 2 de pedro en la linea 1
        p.ver(obl.insertarPalabraEnLinea(2, 1, 1, 1, "Hola").resultado, Retorno.Resultado.ERROR, "Se agrega palabra Hola linea 1 mensaje 1 de Pedro");
        //Mensaje 1 ya no existe, deberia devovler ERROR
        p.ver(obl.insertarPalabraEnLinea(2, 1, 1, 2, "Pedro").resultado, Retorno.Resultado.ERROR, "Se agrega palabra Pedro linea 1 mensaje 1 de Pedro");
        //Mensaje 1 ya no existe, deberia devovler ERROR
        p.ver(obl.insertarPalabraEnLinea(9, 1, 1, 2, "err").resultado, Retorno.Resultado.ERROR, "Se agrega palabra err linea 1 mensaje 1 de contacto inexistente");
        p.ver(obl.insertarPalabraEnLinea(2, 9, 1, 2, "err").resultado, Retorno.Resultado.ERROR, "Se agrega palabra err linea 1 mensaje inexistente de Pedro");
        p.ver(obl.insertarPalabraEnLinea(2, 1, 9, 2, "err").resultado, Retorno.Resultado.ERROR, "Se agrega palabra err linea inexistente mensaje 1 de Pedro");
        p.ver(obl.insertarPalabraEnLinea(2, 1, 1, 9, "err").resultado, Retorno.Resultado.ERROR, "Se agrega palabra err linea 1 mensaje 1 de Pedro en posicion inexistente");

        p.ver(obl.insertarPalabraEnLinea(2, 1, 1, 3, "querido").resultado, Retorno.Resultado.ERROR, "Se agrega palabra querido linea 1 mensaje 1 de Pedro");
        //Mensaje 1 ya no existe, deberia devovler ERROR
        p.ver(obl.insertarPalabraEnLinea(2, 1, 1, 4, "fueradelimite").resultado, Retorno.Resultado.ERROR, "Se agrega palabra fueradelimite en linea 1 mensaje 1 de Pedro se pasa limite de palabras");
        
        // Imprimimos mensaje 2 de pedro
        p.ver(obl.imprimirTexto(2, 1).resultado, Retorno.Resultado.ERROR, "Se imprime mensaje 1 de Pedro");
        //Mensaje 1 ya no existe, deberia devovler ERROR
        p.ver(obl.imprimirTexto(2, 2).resultado, Retorno.Resultado.OK, "Se imprime mensaje 2 de Pedro vacío");
        
        // Borramos ocurrencia del mensaje 
        p.ver(obl.insertarLinea(2, 2).resultado, Retorno.Resultado.OK, "Agrego una linea al mensaje 1 del contacto 2");
        p.ver(obl.insertarPalabraEnLinea(2, 2, 1, 1, "Hola").resultado, Retorno.Resultado.OK, "Se agrega palabra Hola linea 1 mensaje 1 de Pedro");
        p.ver(obl.insertarPalabraEnLinea(2, 2, 1, 2, "Chau").resultado, Retorno.Resultado.OK, "Se agrega palabra Hola linea 1 mensaje 1 de Pedro");
        p.ver(obl.insertarPalabraEnLinea(2, 2, 1, 3, "Hola").resultado, Retorno.Resultado.OK, "Se agrega palabra Hola linea 1 mensaje 1 de Pedro");
        p.ver(obl.borrarOcurrenciasPalabraEnTexto(2, 2, "Hola").resultado, Retorno.Resultado.OK, "Se borran las ocurrencias de la palabra Hola en el mensaje 1 del contacto 2");
        p.ver(obl.imprimirTexto(2, 2).resultado, Retorno.Resultado.OK, "Se imprime mensaje 1 de Pedro");
        
//         Insertar palabra y desplazar
        p.ver(obl.insertarPalabraYDesplazar(2, 2, 1 , 1, "Holis").resultado, Retorno.Resultado.OK,"Se agrega palabra puedo en linea 2 mensaje 2 posicion 2");
        p.ver(obl.insertarPalabraYDesplazar(2, 2, 1 , 2, "Que").resultado, Retorno.Resultado.OK,"Se agrega palabra puedo en linea 2 mensaje 2 posicion 2");
        p.ver(obl.insertarPalabraYDesplazar(2, 2, 1 , 3, "Tal").resultado, Retorno.Resultado.OK,"Se agrega palabra puedo en linea 2 mensaje 2 posicion 2");
        p.ver(obl.imprimirTexto(2, 2).resultado, Retorno.Resultado.OK, "Se imprime mensaje 1 de Pedro");
        p.ver(obl.insertarPalabraYDesplazar(2, 2, 1 , 1, "Palabra1").resultado, Retorno.Resultado.OK,"Se agrega palabra puedo en linea 2 mensaje 2 posicion 2");
        
        p.ver(obl.insertarPalabraYDesplazar(2, 2, 1 , 1, "Palabra1").resultado, Retorno.Resultado.OK,"Se agrega palabra puedo en linea 2 mensaje 2 posicion 2");
        p.ver(obl.insertarPalabraYDesplazar(2, 2, 1 , 1, "Palabra1").resultado, Retorno.Resultado.OK,"Se agrega palabra puedo en linea 2 mensaje 2 posicion 2");
        p.ver(obl.insertarPalabraYDesplazar(2, 2, 1 , 1, "Palabra1").resultado, Retorno.Resultado.OK,"Se agrega palabra puedo en linea 2 mensaje 2 posicion 2");
        p.ver(obl.imprimirTexto(2, 2).resultado, Retorno.Resultado.OK, "Se imprime mensaje 1 de Pedro");

        // Borrar palabra
        p.ver(obl.borrarPalabra(2, 2, 1, 2).resultado, Retorno.Resultado.OK, "Se elimina palabra pos 2 de mensaje 2 linea 2 de pedro");
        p.ver(obl.imprimirTexto(2, 2).resultado, Retorno.Resultado.OK, "Se imprime mensaje 2 de Pedro");

        // Borrar ocurrencia en linea 
        p.ver(obl.insertarPalabraYDesplazar(2, 2, 2 , 2, "Holis").resultado, Retorno.Resultado.OK,"Se agrega palabra puedo en linea 2 mensaje 2 posicion 2");
        p.ver(obl.imprimirTexto(2, 2).resultado, Retorno.Resultado.OK, "Se imprime mensaje 2 de Pedro");
        p.ver(obl.borrarOcurrenciasPalabraEnLinea(2, 2, 1, "Holis").resultado, Retorno.Resultado.OK, "borramos ocurrencia de holis en la linea 1 del mensaje 2 del contacto 2");
        p.ver(obl.imprimirTexto(2, 2).resultado, Retorno.Resultado.OK, "Se imprime mensaje 2 de Pedro");
        
        // Imprimir linea
        p.ver(obl.imprimirLinea(2, 2, 2).resultado, Retorno.Resultado.OK, "Se imprime la linea 1 del mensaje 2 del contacto 2");
        
        // agregar al diccionario
        p.ver(obl.ingresarPalabraDiccionario("Holis").resultado, Retorno.Resultado.OK, "Agregamos la palabra Holis al diccionario");
        p.ver(obl.ingresarPalabraDiccionario("Holis").resultado, Retorno.Resultado.ERROR, "Agregamos la palabra duplicada Holis al diccionario");
        
        // borrar del diccionario
        p.ver(obl.borrarPalabraDiccionario("Holis").resultado, Retorno.Resultado.OK, "Borramos la palabra Holis del diccionario");
        p.ver(obl.borrarPalabraDiccionario("Holis").resultado, Retorno.Resultado.ERROR, "Borramos la palabra Holis del diccionario x2");
        
        // imprimir diccionario
        p.ver(obl.ingresarPalabraDiccionario("Hola").resultado, Retorno.Resultado.OK, "Agregamos la palabra Holis al diccionario");
        p.ver(obl.ingresarPalabraDiccionario("querido").resultado, Retorno.Resultado.OK, "Agregamos la palabra Holis al diccionario");
        p.ver(obl.ingresarPalabraDiccionario("Tal").resultado, Retorno.Resultado.OK, "Agregamos la palabra Holis al diccionario");
        p.ver(obl.ingresarPalabraDiccionario("Chau").resultado, Retorno.Resultado.OK, "Agregamos la palabra Holis al diccionario");
        p.ver(obl.imprimirDiccionario().resultado, Retorno.Resultado.OK, "Imprimimos diccionario");
        
        // Imprimir texto incorrecto 
        p.ver(obl.ImprimirTextoIncorrecto().resultado, Retorno.Resultado.OK, "Imprimimos texto incorrecto");
        
        // Agregamos mas mensajes 
        fecha=formato.parse("24/03/2022");
        p.ver(obl.agregarMensaje(1,2, fecha).resultado, Retorno.Resultado.OK, "Se agrega  mensaje  4 para Pedro en  fecha 24/03/2022 ");
        p.ver(obl.agregarMensaje(1,2, fecha).resultado, Retorno.Resultado.OK, "Se agrega  mensaje 5 para Pedro en  fecha 24/03/2022 ");
         
        p.ver(obl.agregarMensaje(2,3, fecha).resultado, Retorno.Resultado.OK, "Se agrega  mensaje  4 para Ana en fecha 24/03/2022 ");
        p.ver(obl.agregarMensaje(2,3, fecha).resultado, Retorno.Resultado.OK, "Se agrega  mensaje 5 para Ana en fecha 24/03/2022");
           
        fecha=formato.parse("25/03/2022");
        
        p.ver(obl.agregarMensaje(1,2, fecha).resultado, Retorno.Resultado.OK, "Se agrega  mensaje para Pedro en  fecha 25/03/2022 ");
        p.ver(obl.agregarMensaje(1,4, fecha).resultado, Retorno.Resultado.OK, "Se agrega  mensaje para Maria en  fecha 25/03/2022 ");
         
        p.ver(obl.agregarMensaje(2,4, fecha).resultado, Retorno.Resultado.OK, "Se agrega  mensaje   para Maria en fecha 25/03/2022 ");
        p.ver(obl.agregarMensaje(2,4, fecha).resultado, Retorno.Resultado.OK, "Se agrega  mensaje  para Maria en fecha 25/03/2022");
        
        // Imprimir cantidad de mensajes 
        p.ver(obl.cantidadDeMensajes(1).resultado, Retorno.Resultado.OK, "Imprimimos la cantidad de mensajes");
        //p.ver(obl.cantidadDeMensajes(2).resultado, Retorno.Resultado.OK, "Imprimimos la cantidad de mensajes"); 
        //p.ver(obl.cantidadDeMensajes(3).resultado, Retorno.Resultado.OK, "Imprimimos la cantidad de mensajes"); 
        //p.ver(obl.cantidadDeMensajes(4).resultado, Retorno.Resultado.OK, "Imprimimos la cantidad de mensajes");
      
        //defensa
        p.ver(obl.imprimirContactos().resultado, Retorno.Resultado.OK, "Se imprimen todos los contactos");
        p.ver(obl.imprimircontactoMaxCantMensajes().resultado, Retorno.Resultado.OK, "Se imprime el contacto con mas mensajes");
        obl.agregarMensaje(1, 3, fecha);
        obl.agregarMensaje(1, 3, fecha);
        obl.agregarMensaje(1, 3, fecha);
        obl.agregarMensaje(1, 3, fecha);
        
        System.out.println("CONTACTO CON MAXIMA CANTIDAD DE MENSAJES");
        p.ver(obl.imprimircontactoMaxCantMensajes().resultado, Retorno.Resultado.OK, " contacto con mas mensajes "+obl.imprimircontactoMaxCantMensajes().valorString);
        
        p.ver(obl.destruirSistemaMensajes().resultado, Retorno.Resultado.OK, " sistema eliminado");

        p.imprimirResultadosPrueba();
    }

//    public static void juegodepruebadefensa(Obligatorio obl, Prueba p) throws ParseException {
//        
//        //        // definimos una fecha        
//        Date fecha=new Date();
//        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//  
//       //-------------------------------
//        fecha=formato.parse("28/03/2022");
//       //--------------------------------
////        2.3. Crear Contacto  
//        p.ver(obl.crearSistemaMensajes(3).resultado, Retorno.Resultado.OK, "Se creara sistma para 3 palabras por linea");
//        p.ver(obl.agregarContacto(1, "Juan Perez").resultado, Retorno.Resultado.OK, "se agrega contacto Juan Perez al sistema");
//        p.ver(obl.agregarContacto(1, "Juan Perez").resultado, Retorno.Resultado.ERROR, "se intenta agregar contacto Juan Perez que ya existe"); 
//        p.ver(obl.agregarContacto(2, "Juan Barrera").resultado, Retorno.Resultado.OK, "se agrega contacto Juan Barrera");
//        p.ver(obl.agregarContacto(3, "Diego Frutos").resultado, Retorno.Resultado.OK, "se agrega contacto Diego Frutos");
//        p.ver(obl.agregarContacto(4, "Jorge Mendez").resultado, Retorno.Resultado.OK, "se agrega contacto Jorge Mendez");
//        p.ver(obl.agregarContacto(5, "Gustavo Pintos").resultado, Retorno.Resultado.OK, "se agrega contacto Gustavo Pintos ");   
//        p.ver(obl.agregarContacto(6, "Santiago Gomez").resultado, Retorno.Resultado.OK, "se agrega contacto  Santiago Gomez ");
//        obl.ListaContactos.mostrar();
//        
////      System.out.println();
//        
////      2.4 Eliminar Contacto       
//      p.ver(obl.eliminarContacto(5).resultado, Retorno.Resultado.OK, "se elimina contacto Gustavo Pintos del sistema"); 
//      p.ver(obl.eliminarContacto(5).resultado, Retorno.Resultado.ERROR, "se elimina contacto Gustavo Pintos del sistema , EN ESTE CASO NO ES POSIBLE , no existe"); 
//      obl.ListaContactos.mostrar();
//        
//      System.out.println();
//       
//       
////      2.5 Agregar Mensaje 
//      p.ver(obl.agregarMensaje(1, 2, fecha).resultado, Retorno.Resultado.OK, "Se agrega el mensaje Contacto origen 1 y contacto destino 2 28/03/22");
//      p.ver(obl.agregarMensaje(1, 4, fecha).resultado, Retorno.Resultado.OK, "Se agrega el mensaje Contacto origen 1 y contacto destino 4 28/03/22");
//      p.ver(obl.agregarMensaje(1, 3, fecha).resultado, Retorno.Resultado.OK, "Se agrega el mensaje Contacto origen 1 y contacto destino 3 28/03/22");
//      p.ver(obl.agregarMensaje(2, 3, fecha).resultado, Retorno.Resultado.OK, "Se agrega el mensaje Contacto origen 2 y contacto destino 3 28/03/22");
//      p.ver(obl.agregarMensaje(4, 3, fecha).resultado, Retorno.Resultado.OK, "Se agrega el mensaje Contacto origen 4 y contacto destino 3 28/03/22");
//      p.ver(obl.agregarMensaje(2, 1, fecha).resultado, Retorno.Resultado.OK, "Se agrega el mensaje --");
//
////      
//      System.out.println();
//      obl.MostrarMensajesContacto(1);
//      System.out.println();
//      obl.MostrarMensajesContacto(2);
//      System.out.println();
//      obl.MostrarMensajesContacto(4);
////        
//////    2.6. Eliminar Mensaje       
//      p.ver(obl.eliminarMensaje(4,1).resultado, Retorno.Resultado.OK, "Se Elimina el mensaje Conctaco Origen 2, IdMensaje : 4 --");
//      System.out.println();
//      obl.MostrarMensajesContacto(4);
//      p.ver(obl.eliminarMensaje(4,1).resultado, Retorno.Resultado.ERROR, "Se Elimina el mensaje Conctaco Origen 2, IdMensaje : 4 -- , se espera ERROR");
//      System.out.println();
//      obl.MostrarMensajesContacto(4);
//               
////      3.1 Imprime el texto por pantalla. 
////3.2. Inserta una nueva línea vacía al final del texto del mensaje.
//      p.ver(obl.insertarLinea(1, 1).resultado, Retorno.Resultado.OK, "Inserta linea en Id Msj : 1  y contacto 1");
//      p.ver(obl.insertarLinea(1, 1).resultado, Retorno.Resultado.OK, "Inserta linea en Id Msj : 1  y contacto 1");
//      p.ver(obl.insertarLinea(1, 1).resultado, Retorno.Resultado.OK, "Inserta linea en Id Msj : 1  y contacto 1");
//      p.ver(obl.imprimirTexto(1, 1).resultado, Retorno.Resultado.OK, "Imprime texto en pantalla Mensaje Id 1");
//      p.ver(obl.insertarPalabraEnLinea(1, 1 , 1 , 1 ,"Palabra2").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//      p.ver(obl.insertarPalabraEnLinea(1, 1 , 1 , 1 ,"Palabra2").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//      p.ver(obl.insertarPalabraEnLinea(1, 1 , 1 , 1 ,"Palabra1").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//      p.ver(obl.insertarPalabraEnLinea(1, 1 , 2 , 1 ,"Palabra3").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//      p.ver(obl.insertarPalabraEnLinea(1, 1 , 2 , 1 ,"Palabra2").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//      p.ver(obl.insertarPalabraEnLinea(1, 1 , 2 , 1 ,"Palabra4").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//      p.ver(obl.insertarPalabraEnLinea(1, 1 , 3 , 1 ,"Palabra4").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//      p.ver(obl.imprimirTexto(1, 1).resultado, Retorno.Resultado.OK, "Imprime texto en pantalla Mensaje Id 1 *** ");                  
//////
//////      
////////      3.3 Inserta una nueva línea vacía en la posición indicada.
//////       
//      p.ver(obl.insertarLineaEnPosicion(1, 1 ,3).resultado, Retorno.Resultado.OK, "Ingresar una linea en la posicion");
//      p.ver(obl.insertarLineaEnPosicion(1, 1 ,4).resultado, Retorno.Resultado.OK, "Ingresar una linea en la posicion");
//      p.ver(obl.insertarPalabraEnLinea(1, 1 , 1 , 1 ,"Palabra1").resultado, Retorno.Resultado.OK, "Ingresar una palabra en una linea");        
//      p.ver(obl.imprimirTexto(1, 1).resultado, Retorno.Resultado.OK, "Imprime texto en pantalla Mensaje 5");        
//      p.ver(obl.insertarLineaEnPosicion(1, 1 ,1).resultado, Retorno.Resultado.OK, "Ingresar una linea en la posicion");
//      p.ver(obl.imprimirTexto(1, 1).resultado, Retorno.Resultado.OK, "Imprime texto en pantalla Mensaje 1");
//////      
////////    3.4 Borra la línea en la posición indicada.
//////
//    p.ver(obl.borrarLinea(1,1,4).resultado, Retorno.Resultado.OK, "Imprime texto en pantalla Mensaje 1");
//    p.ver(obl.borrarLinea(1,1,8).resultado, Retorno.Resultado.ERROR, "Imprime texto en pantalla Mensaje 1 , linea que no existe");
//    p.ver(obl.imprimirTexto(1,1).resultado, Retorno.Resultado.OK, "Imprime texto en pantalla Mensaje 1");
////
//////   3.5. Borra todas las líneas del texto.
////
//    p.ver(obl.borrarTodo(1,1) .resultado, Retorno.Resultado.OK, "Borra texto en pantalla Mensaje 1");
//    p.ver(obl.imprimirTexto(1, 1).resultado, Retorno.Resultado.OK, "Imprime texto en pantalla Mensaje 1");
//
////
////// 3.6 Borrar todas las ocurrencias de una palabra en el texto
//    p.ver(obl.insertarLinea(1, 1).resultado, Retorno.Resultado.OK, "Inserta linea en Id Msj : 1  y contacto 1");
//    p.ver(obl.insertarLinea(1, 1).resultado, Retorno.Resultado.OK, "Inserta linea en Id Msj : 1  y contacto 1");
//    p.ver(obl.insertarLinea(1, 1).resultado, Retorno.Resultado.OK, "Inserta linea en Id Msj : 1  y contacto 1");
//    p.ver(obl.insertarPalabraEnLinea(1, 1 , 1 , 1 ,"Palabra2").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//    p.ver(obl.insertarPalabraEnLinea(1, 1 , 1 , 1 ,"Palabra2").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//    p.ver(obl.insertarPalabraEnLinea(1, 1 , 1 , 1 ,"Palabra21").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//    p.ver(obl.insertarPalabraEnLinea(1, 1 , 2 , 1 ,"Palabra2").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//    p.ver(obl.insertarPalabraEnLinea(1, 1 , 2 , 1 ,"Palabra3").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//    p.ver(obl.insertarPalabraEnLinea(1, 1 , 2 , 1 ,"Palabra5").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//    p.ver(obl.insertarPalabraEnLinea(1, 1 , 3 , 1 ,"Palabra2").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//    p.ver(obl.insertarPalabraEnLinea(1, 1 , 2 , 1 ,"Palabra8").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//    p.ver(obl.imprimirTexto(1, 1).resultado, Retorno.Resultado.OK, "Imprime texto en pantalla Mensaje 1");
//    p.ver(obl.borrarOcurrenciasPalabraEnTexto(1,1,"Palabra2").resultado, Retorno.Resultado.OK, "Imprime texto en pantalla Mensaje 1");
//    p.ver(obl.imprimirTexto(1, 1).resultado, Retorno.Resultado.OK, "Imprime texto en pantalla Mensaje 1");
//  
//////3.7 Insertar una palalbra en una linea   
//      p.ver(obl.borrarTodo(1,1) .resultado, Retorno.Resultado.OK, "Borra texto en pantalla Mensaje 1");
//      p.ver(obl.insertarLinea(1,1).resultado, Retorno.Resultado.OK, "Inserta linea en Id Msj : 1 y contacto 1");
//      p.ver(obl.insertarLinea(1,1).resultado, Retorno.Resultado.OK, "Inserta linea en Id Msj : 1 y contacto 1");
//   
//      p.ver(obl.insertarPalabraEnLinea(1, 1 , 2 , 1 ,"Palabra1").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//      p.ver(obl.insertarPalabraEnLinea(1, 1 , 2 , 2 ,"Palabra2").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea "); //Revisar el orden cuando hace el desplazamiento hacia la fila posterior
//      p.ver(obl.insertarPalabraEnLinea(1, 1 , 2 , 1 ,"Palabra3").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//      p.ver(obl.imprimirTexto(1, 1).resultado, Retorno.Resultado.OK, "Imprime texto en pantalla Mensaje 5");
//        
//      p.ver(obl.insertarPalabraYDesplazar(1, 1 , 1 , 1 ,"Palabra1").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//      p.ver(obl.insertarPalabraYDesplazar(1, 1 , 1 , 2 ,"Palabra2").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//      p.ver(obl.insertarPalabraYDesplazar(1, 1 , 1 , 3 ,"Palabra3").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//      p.ver(obl.imprimirTexto(1,1).resultado, Retorno.Resultado.OK, "Imprime texto en pantalla Mensaje 1 del Contacto 2 ");
//      p.ver(obl.insertarPalabraYDesplazar(1, 1 , 1 , 1 ,"Palabra4").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//        
//      p.ver(obl.insertarPalabraYDesplazar(1, 1 , 1 , 1 ,"Palabra4").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//      p.ver(obl.imprimirTexto(1,1).resultado, Retorno.Resultado.OK, "Imprime texto en pantalla Mensaje 1 del Contacto 2 ");
//      p.ver(obl.insertarPalabraYDesplazar(1, 1 , 1 , 1 ,"Palabra5").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//      p.ver(obl.insertarPalabraYDesplazar(1, 1 , 1 , 1 ,"Palabra5").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//      p.ver(obl.insertarPalabraYDesplazar(1, 1 , 1 , 1 ,"Palabra5").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//      p.ver(obl.insertarPalabraYDesplazar(1, 1 , 2 , 1 ,"Palabra5").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//      p.ver(obl.insertarPalabraYDesplazar(1, 1 , 3 , 3 ,"Palabra6").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
//      p.ver(obl.insertarPalabraYDesplazar(1, 1 , 2 , 1 ,"Palabra9").resultado, Retorno.Resultado.OK, "Insertar una palabra en una linea ");
////     
//      p.ver(obl.imprimirTexto(1,1).resultado, Retorno.Resultado.OK, "Imprime texto en pantalla Mensaje 1 del Contacto 1 ");
////      
////// 3.11 Imprimir linea de un mensaje
//       p.ver(obl.imprimirLinea(1,1,1).resultado, Retorno.Resultado.OK, "Imprime linea 1 del mensaje 1 "); 
//       p.ver(obl.imprimirLinea(1,1,2).resultado, Retorno.Resultado.OK, "Imprime linea 2 del mensaje 1 "); 
//       p.ver(obl.imprimirLinea(1,1,3).resultado, Retorno.Resultado.OK, "Imprime linea 3 del mensaje 1 ");
//////3.12 Agrgegar palabra al diccionario
//       p.ver(obl.ingresarPalabraDiccionario("Hoja").resultado, Retorno.Resultado.OK, "Se ingresa la palabra Hoja"); 
//       p.ver(obl.ingresarPalabraDiccionario("Hojalata").resultado, Retorno.Resultado.OK, "Se ingresa la palabra Hojalata"); 
//       p.ver(obl.ingresarPalabraDiccionario("Bosque").resultado, Retorno.Resultado.OK, "Se ingresa la palabra Bosque"); 
//       p.ver(obl.ingresarPalabraDiccionario("Casa").resultado, Retorno.Resultado.OK, "Se ingresa la palabra Bosque"); 
//       p.ver(obl.imprimirDiccionario().resultado, Retorno.Resultado.OK, "Se imprime el diccionario"); 
////       
//////3.13  Borrar palabra al diccionario
//       p.ver(obl.ingresarPalabraDiccionario("Palabra5").resultado, Retorno.Resultado.OK, "Se ingresa la palabra Palabra5"); 
//       p.ver(obl.ingresarPalabraDiccionario("Palabra2").resultado, Retorno.Resultado.OK, "Se ingresa la palabra Palabra5"); 
//       p.ver(obl.borrarPalabraDiccionario("CASA").resultado, Retorno.Resultado.OK, "Se borra la palabra casa"); 
//       p.ver(obl.imprimirDiccionario().resultado, Retorno.Resultado.OK, "Se imprime el diccionario"); 
//////3.15 Imprimir texto incorrecto que no esta en el Diccionario
//       p.ver(obl.ImprimirTextoIncorrecto().resultado, Retorno.Resultado.OK, "Imprimir texto incorrecto");
////       
//////4.1 Muestras la cantidad de mensajes enviados por un contacto
//       fecha=formato.parse("29/03/2022");
//      p.ver(obl.agregarMensaje(1, 2, fecha).resultado, Retorno.Resultado.OK, "Se agrega el mensaje Contacto origen 1 y contacto destino 2 29/03/2022");
//      p.ver(obl.agregarMensaje(1, 4, fecha).resultado, Retorno.Resultado.OK, "Se agrega el mensaje Contacto origen 1 y contacto destino 4 29/03/2022");
//      p.ver(obl.agregarMensaje(4, 3, fecha).resultado, Retorno.Resultado.OK, "Se agrega el mensaje Contacto origen 4 y contacto destino 3 29/03/2022");
//      p.ver(obl.agregarMensaje(1, 2, fecha).resultado, Retorno.Resultado.OK, "Se agrega el mensaje Contacto origen 1 y contacto destino 2 29/03/2022");
//      p.ver(obl.agregarMensaje(1, 4, fecha).resultado, Retorno.Resultado.OK, "Se agrega el mensaje Contacto origen 1 y contacto destino 4 29/03/2022");
//      p.ver(obl.agregarMensaje(4, 3, fecha).resultado, Retorno.Resultado.OK, "Se agrega el mensaje Contacto origen 4 y contacto destino 3 29/03/2022");
//      p.ver(obl.agregarMensaje(2, 3, fecha).resultado, Retorno.Resultado.OK, "Se agrega el mensaje Contacto origen 2 y contacto destino 3 29/03/2022");
//      fecha=formato.parse("30/03/2022");
//      p.ver(obl.agregarMensaje(1, 6, fecha).resultado, Retorno.Resultado.OK, "Se agrega el mensaje Contacto origen 1 y contacto destino 6 30/03/2022");
//      p.ver(obl.agregarMensaje(1, 6, fecha).resultado, Retorno.Resultado.OK, "Se agrega el mensaje Contacto origen 1 y contacto destino 6 30/03/2022");
//      p.ver(obl.agregarMensaje(1, 4, fecha).resultado, Retorno.Resultado.OK, "Se agrega el mensaje Contacto origen 1 y contacto destino 4 30/03/2022");
//      p.ver(obl.agregarMensaje(3, 6, fecha).resultado, Retorno.Resultado.OK, "Se agrega el mensaje Contacto origen 3 y contacto destino 6 30/03/2022");
//      p.ver(obl.agregarMensaje(2, 6, fecha).resultado, Retorno.Resultado.OK, "Se agrega el mensaje Contacto origen 2 y contacto destino 6 30/03/2022");
//            
//      p.ver(obl.cantidadDeMensajes(1).resultado, Retorno.Resultado.OK, "Imprimir la cantidad de mensajes");
//      p.ver(obl.cantidadDeMensajes(2).resultado, Retorno.Resultado.OK, "Imprimir la cantidad de mensajes");
//      p.ver(obl.cantidadDeMensajes(3).resultado, Retorno.Resultado.OK, "Imprimir la cantidad de mensajes");
//      p.ver(obl.cantidadDeMensajes(4).resultado, Retorno.Resultado.OK, "Imprimir la cantidad de mensajes");
//      p.ver(obl.cantidadDeMensajes(6).resultado, Retorno.Resultado.OK, "Imprimir la cantidad de mensajes");
//      
//      p.ver(obl.destruirSistemaMensajes().resultado, Retorno.Resultado.OK, " sistema eliminado");
//
//      p.imprimirResultadosPrueba();
//    }
       
}
