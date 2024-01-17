
package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import javax.persistence.NoResultException;
import libreria.entidades.Autor;
import libreria.persistencia.autorDAO;


public class AutorService {
    private final autorDAO dao;
    

    public AutorService() {
        this.dao = new autorDAO();
    }
    
    public Autor crearAutor(String nombre) throws Exception{
        Autor autorExistente = buscarPornormbre(nombre);

    if (autorExistente != null) {
        throw new Exception("Error, ya existe un autor con el mismo nombre");
    }
        
        Autor autor = new Autor();
        Scanner sc = new Scanner(System.in);
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("Error, el Autor del libro no puede estar vacío nulo o repetido");
            }
            autor.setNombre(nombre);
            System.out.println("Indique V o F si quiere dar de alta al Autor");
            String resp = sc.next();
            if (resp.equalsIgnoreCase("v") ) {
                System.out.println("Se indico el Alta");
                autor.setAlta(true);
            }
            if (resp.equalsIgnoreCase("f")) {
                System.out.println("Se nego el alta");
                autor.setAlta(false);
            }
            dao.guardar(autor);
            return autor;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public void eliminarAutor(String nombre){
        try {
            dao.conectar();
            Autor autor = dao.buscarporNombre(nombre);
            if (autor == null) {
                throw new Exception ("No se consigno un objeto corrrecto");
            }else{
                System.out.println("Autor " + autor.getNombre() + " eliminado");
                dao.eliminar(autor);
            }
        } catch (Exception e) {
        }
    }
    public Autor editarAltaoBaja(String nombre) throws Exception{
        try {
            Autor autor = dao.buscarporNombre(nombre);
             if (autor == null) {
                throw new Exception ("No se consigno un objeto corrrecto");
            }else{
                 System.out.println("Se cambia el alta o baja");
                 if(autor.isAlta() == true){
                     autor.setAlta(false);
                 }else{
                     autor.setAlta(true);
                 }
             }
             System.out.println("El estado actual de Alta o Baja es: " + autor.isAlta());
             dao.editar(autor);
             return autor;
             
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public List<Autor> listarAutores() throws Exception{
        try {
            List <Autor> autores = dao.listarTodos();
            if(autores == null){
                throw new Exception ("La lista confeccionada es incorrecta");
                
            }else{
                for (Autor autore : autores) {
                    System.out.println(autore);
                }
            return autores;
                        }
        } catch (Exception e) {
            throw e;
        }
    }
    public Autor buscarPornormbre(String nombre) throws Exception{
        try {
            return dao.buscarporNombre(nombre);
        } catch (NoResultException e) {
            return null;
        }
    }
        public Autor busquedaporId(String id) throws Exception{
        try {
             Autor autor = dao.buscarporId(id);
            return autor;
            
        } catch (Exception e) {
            throw new Exception ("No se pudo buscar autor por ID");
        }
    }
}
