
package libreria.servicios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;
import libreria.persistencia.libroDAO;



public class LibroService {
private AutorService autorservice;
private EditorialService editorialservice;
private PrestamoService prestamoservice;
private final libroDAO DAO;


    public LibroService() {
        this.DAO = new libroDAO();
    }
    public void setServicios(AutorService autorservice, EditorialService editorialservice, PrestamoService prestamoservice){
        this.autorservice  = new AutorService();
        this.editorialservice = new EditorialService();
        this.prestamoservice = new PrestamoService();
    }
    
    public void existeLibro(String title) throws Exception{
        try {
            Collection <Libro> libros = DAO.listarTodos();
            if(libros == null){
                throw new Exception ("Error al traer la lista de Libros");
            }else{
                for (Libro x : libros) {
                    if(x.getTitle().equalsIgnoreCase(title)){
                        throw new Exception ("Error, ya exite el titulo en los registros");
                    }
                }
            }
            
        } catch (Exception e) {
            throw e;
        }
    }
    public Libro crearLibro(String title, int year, int ejemplares) throws Exception{
        setServicios(autorservice, editorialservice, prestamoservice);
         Libro libroExistente = busquedaporTitulo(title);
        try {          
                if (title == null || title.trim().isEmpty()) {
                    System.out.println("Titulo vacio");
                    return null;
             
            }
            if(libroExistente != null){
                
                System.out.println("Titulo repetido");
                return null;
            }
            
            
            Libro libro = new Libro();
            libro.setTitle(title);
            libro.setYear(year);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(0);
            libro.setEjemplaresRestantes(ejemplares);
            
            
            
            System.out.println("------------------------------------------------------");
            Scanner sc = new Scanner(System.in);
            autorservice.listarAutores();
            System.out.println("Indique el ID del autor del libro");
            String resp = sc.nextLine();
            Autor autor = autorservice.busquedaporId(resp);
            libro.setAutor(autor);
            
            System.out.println("------------------------------------------------------");
            editorialservice.listarEditoriales();
            System.out.println("Indique el ID de la editorial");
            resp = sc.nextLine();
            Editorial editorial = editorialservice.busquedaporId(resp);
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
    
    public Libro busquedaporISBN(String id) throws Exception{
        try {
            Libro libro = DAO.buscarporId(id);
            return libro;
            
        } catch (Exception e) {
            throw new Exception ("No se pudo buscar libro por ID");
        }
    }
    public Libro busquedaporTitulo(String title) throws Exception{
        try {
            Libro libro = DAO.buscarporTitulo(title);
            return libro;
        } catch (Exception e) {
            System.out.println("Libro no encontrado");
            return null;
            
        }
    }
    public Collection<Libro> busquedaporAutor(String nombre) throws Exception{
        Collection <Libro> librosAutores = new ArrayList();
        try {
            Collection<Libro> libros = DAO.listarTodos();
            if (libros == null) {
                throw new Exception ("la lista de libros traida es incorrecta");
            }else{
                for (Libro x : libros) {
                    if(x.getAutor().getNombre().equalsIgnoreCase(nombre)){
                        librosAutores.add(x);
                        System.out.println(x);
                    }
                    
                }
            }
            return librosAutores;
        } catch (Exception e) {
            throw new Exception ("error en la busqueda por Autor");
        }
        }
    public Collection<Libro> busquedaporEditorial (String nombre) throws Exception{
        Collection <Libro> librosEditorial = new ArrayList();
        try {
            Collection<Libro> libros = DAO.listarTodos();
            if (libros == null) {
                throw new Exception ("la lista de libros traida es incorrecta");
            }else{
                for (Libro x : libros) {
                    if(x.getEditorial().getNombre().equalsIgnoreCase(nombre)){
                        librosEditorial.add(x);
                        System.out.println(x);
                    }
                    
                }
            }
            return librosEditorial;
        } catch (Exception e) {
            throw new Exception ("error en la busqueda por Editorial");
        }
    }
    public Libro editarEjemplares(Libro libro) throws Exception{
        try {
            if (libro.getEjemplaresRestantes() > 0) {
            libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() + 1); 
            libro.setEjemplares(libro.getEjemplares()-1);
            libro.setEjemplaresRestantes(libro.getEjemplaresRestantes()-1);
        }else{
            System.out.println("No tenemos libros de ese titulo para prestar");
            return null;
        }
        DAO.merge(libro);
        return libro;
        } catch (Exception e) {
            throw e;
        }
    }
    public void eliminarlibro(String title){
        try {
            Libro libro = busquedaporTitulo(title);
            DAO.merge(libro);
            DAO.eliminar(libro);
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
    public void guardarCambios(Libro libro){
        try {
            DAO.merge(libro);
            System.out.println("libro guardado");
        } catch (Exception e) {
            System.out.println("Error al guardar");
            throw e;
        }
    }
    
    }
    


