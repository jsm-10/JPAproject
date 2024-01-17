
package libreria;

import libreria.servicios.AutorService;
import libreria.servicios.EditorialService;

import libreria.servicios.LibroService;



public class Libreria {

 
    public static void main(String[] args) {
        AutorService as = new AutorService();
        EditorialService es = new EditorialService();
        LibroService lb = new LibroService();

        
        
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
        
    }
    
}
