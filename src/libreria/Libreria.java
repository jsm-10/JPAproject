
package libreria;

import libreria.servicios.AutorService;

public class Libreria {

 
    public static void main(String[] args) {
        AutorService as = new AutorService();
        
        try {
             as.crearAutor("Phil Knight");
             as.crearAutor("JK Rowling");
             as.crearAutor("Mark Manson");
             as.crearAutor("Walter Isaacson");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            as.crearAutor("Fernando Iglesias");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            as.crearAutor("Nico Perin");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
       
        
        
    }
    
}
