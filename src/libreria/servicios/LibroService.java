
package libreria.servicios;

import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.persistencia.libroDAO;



public class LibroService {
private AutorService autorservice;
private EditorialService editorialservice;
private final libroDAO DAO;


    public LibroService() {
        this.DAO = new libroDAO();
    }
    public void setServicios(AutorService autorservice, EditorialService editorialservice){
        this.autorservice = autorservice;
        this.editorialservice = editorialservice; 
    }
    public Libro crearLibro(String title, int year, int ejemplares) throws Exception{
        Libro libro = new Libro();
        try {
            libro.setTitle(title);
            if (title == null || title.trim().isEmpty()) {
            throw new Exception("Error, el título del libro no puede estar vacío o nulo.");
            }
            libro.setYear(year);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(0);
            libro.setEjemplaresRestantes(ejemplares);
            System.out.println("Indique el nombre del autor del libro");
            Scanner sc = new Scanner(System.in);
            String resp = sc.nextLine();
            Autor autor = autorservice.buscarPornormbre(resp);
            libro.setAutor(autor);
            System.out.println("Indique el nombre de la editorial");
            resp = sc.nextLine();
            Editorial editorial = editorialservice.buscarPornormbre(resp);
            libro.setEditorial(editorial);
            if (autor != null && editorial != null){
                DAO.guardar(libro);
            }else{
                throw new Exception ("Error, Autor o editorial nulos");
            }
            return libro;
        } catch (Exception e) {
            throw e;
        }
    }
}
