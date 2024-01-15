
package libreria;

import libreria.servicios.AutorService;
import libreria.servicios.EditorialService;

public class Libreria {

 
    public static void main(String[] args) {
        AutorService as = new AutorService();
        EditorialService es = new EditorialService();
        
        as.crearAutor("Phil Knight");
        as.crearAutor("JK Rowling");
        as.crearAutor("Mark Manson");
        as.crearAutor("Walter Isaacson");
        
        es.crearEditorial("Planeta");
        es.crearEditorial("SM");
        es.crearEditorial("HarperCollins");
        es.crearEditorial("Scholastic");
        es.crearEditorial("Wiley");
        
    }
    
}
