
package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Autor;
import libreria.entidades.Libro;


public class autorDAO {
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
    public void guardar(Autor autor){
        conectar();
        em.getTransaction().begin();
        em.persist(autor);
        em.getTransaction().commit();
        desconectar();
    }
    public void eliminar (Autor autor){
        conectar();
        em.getTransaction().begin();
        em.remove(autor);
        em.getTransaction().commit();
        desconectar();
    }
    public void editar(Autor autor){
        conectar();
        em.getTransaction().begin();
        em.merge(autor);
        em.getTransaction().commit();
        desconectar();
    }
    public List<Autor> listarTodos() throws Exception {
        conectar();
        List<Autor> autores = em.createQuery("SELECT a FROM Autor a").getResultList();
        desconectar();
        return autores;
    }
    public Autor buscarporNombre(String nombre) throws Exception{
        conectar();
        Autor autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.title LIKE :nombre").setParameter("title", nombre).getSingleResult();
        desconectar();
        return autor;
        
    }
}
