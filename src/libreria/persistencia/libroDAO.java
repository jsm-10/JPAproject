
package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import libreria.entidades.Libro;


public class libroDAO {
        private final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("libreriaPU");
    private EntityManager em= EMF.createEntityManager();
    
    public void conectar(){
        if(!em.isOpen()){
            em= EMF.createEntityManager();
        }
    }
    
    public void desconectar(){
        if(em.isOpen()){
            em.close();
        }
    }
    public void guardar(Libro libro){
        conectar();
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
        desconectar();
    }
    public void eliminar (Libro libro){
        conectar();
        em.getTransaction().begin();
        em.remove(libro);
        em.getTransaction().commit();
        desconectar();
    }
    public void merge(Libro libro) {
    conectar();
    em.getTransaction().begin();
    em.merge(libro);
    em.getTransaction().commit();
    desconectar();
}
    public List<Libro> listarTodos() throws Exception {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l").getResultList();
        desconectar();
        return libros;
    }
    public Libro buscarporTitulo(String title) throws Exception{
    try{
        conectar();
        Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.title LIKE :title").setParameter("title", title).getSingleResult();
        desconectar();
        return libro;
    }catch(Exception ex){
    return null;    
    }
    }
    public Libro buscarporId (String id){
        conectar();
        Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.id LIKE :id").setParameter("id", id).getSingleResult();
        desconectar();
        return libro;
    }
   
}
