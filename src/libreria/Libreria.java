
package libreria;

import java.util.Scanner;
import libreria.servicios.AutorService;
import libreria.servicios.EditorialService;
import libreria.servicios.LibroService;

public class Libreria {

 
    public static void main(String[] args) throws Exception {
        AutorService as = new AutorService();
        EditorialService es = new EditorialService();
        LibroService lb = new LibroService();
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
            System.out.println("1. modificar el alta de la editorial");
            System.out.println("2. salir");
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
            case 2: System.out.println("saliendo del programa");
        }
    
        
        }while (opcion != 2);
    sc.close();
}
}