
package libreria;

import libreria.servicios.AutorService;

public class Libreria {

 
    public static void main(String[] args) {
        AutorService as = new AutorService();
        
        as.crearAutor("Phil Knight");
        as.crearAutor("JK Rowling");
        as.crearAutor("Mark Manson");
        as.crearAutor("Walter Isaacson");
        
    }
    
}
