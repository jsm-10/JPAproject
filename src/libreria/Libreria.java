
package libreria;

import java.util.InputMismatchException;
import java.util.Scanner;
import libreria.servicios.AutorService;
import libreria.servicios.ClienteService;
import libreria.servicios.EditorialService;
import libreria.servicios.LibroService;
import libreria.servicios.PrestamoService;

public class Libreria {

 
    public static void main(String[] args) throws Exception {
        AutorService as = new AutorService();
        EditorialService es = new EditorialService();
        LibroService lb = new LibroService();
        ClienteService cs = new ClienteService();
        PrestamoService ps = new PrestamoService();
        Scanner sc = new Scanner(System.in);
        
        
        
//        try {
//             as.crearAutor("Phil Knight");
//             as.crearAutor("JK Rowling");
//             as.crearAutor("Mark Manson");
//             as.crearAutor("Walter Isaacson");
//             as.crearAutor("Fernando Iglesias");
//             as.crearAutor("Nico Perin");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            es.crearEditorial("SM");
//            es.crearEditorial("Planeta");
//            es.crearEditorial("Algarrobo");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            lb.crearLibro("Nunca te pares", 2015, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int opcion;
       
        do{
             
            System.out.println("Elija una opcion del menu");
            System.out.println("Menu");
            System.out.println("1. Modificar el alta de la editorial");
            System.out.println("2. Crear Cliente");
            System.out.println("3. Dar en prestamo un libro");
            System.out.println("4. Devolver un libro");
            System.out.println("5. Listar prestamos de cliente");
            System.out.println("6. Salir");
               opcion = sc.nextInt();
            
        switch(opcion){
            case 1: System.out.println("Cambiar el alta de una editorial");
                try {
                    es.listarEditoriales();
                    System.out.println("Seleccione el Id de la editorial a editar");
                    String id = sc.next();
                    es.editarAltaoBaja(id);
                    
                } catch (Exception e) {
                    e.printStackTrace();
            }    
                break;
            case 2: System.out.println("Creando un Cliente nuevo");
                try {
                System.out.println("----------------------------------------------------");
                System.out.println("Indique su nombre");
                String nombre = sc.next();
                System.out.println("Indique su apellido");
                String apellido = sc.next();
                System.out.println("Indique su numero de documento sin puntos");
                long documento = sc.nextLong();
                System.out.println("Indique numero de telefono");
                String telefono = sc.next();
                System.out.println("----------------------------------------------------");
                cs.crearCliente(documento, nombre, apellido, telefono);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            break;
            case 3: System.out.println("Configurar un prestamo");
                
                System.out.println("-----------------------------------------------------");
                System.out.println("Indique numero de DNI");
                long documento = sc.nextLong();
                 ps.crearPrestamo(documento); 
                 break;
            case 4: System.out.println("Configurar una devolucion");
                System.out.println("Ingrese DNI del cliente");
                long dni = sc.nextLong();
                lb.devolucionEjemplar(dni);
                break;
            case 5: System.out.println("Lista de libros prestados");
                System.out.println("Seleccione el DNI del cliente");
                long respuesta = sc.nextLong();
                lb.ListadePrestamos(respuesta);
            break;
            case 6: System.out.println("Saliendo");
        }
        }while (opcion != 6);
    sc.close();
}
}