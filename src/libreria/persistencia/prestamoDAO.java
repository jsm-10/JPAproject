/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;


/**
 *
 * @author juans
 */
public class prestamoDAO {
    private final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("libreriaPU");
    private EntityManager em= EMF.createEntityManager();

    public prestamoDAO() {
    }
    
    
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
    public void guardar (Prestamo prestamo){
        conectar();
        em.getTransaction().begin();
        em.persist(prestamo);
        em.getTransaction().commit();
        desconectar();
    }
    public void eliminar (Prestamo prestamo){
        conectar();
        if(!em.contains(prestamo)){
                       prestamo = em.merge(prestamo);
                   }
        em.getTransaction().begin();
        em.remove(prestamo);
        em.getTransaction().commit();
        desconectar();
    }
    public void editar (Prestamo prestamo){
        conectar();
        em.getTransaction().begin();
        em.merge(prestamo);
        em.getTransaction().commit();
        desconectar();
    }
    public List <Prestamo> listarTodos(){
        conectar();
        List<Prestamo> prestamos = em.createQuery("SELECT p FROM Prestamo p").getResultList();
        desconectar();
        return prestamos;
    }
    public List<Prestamo> buscarprestamosDNI(long documento) throws Exception{
        try {
            conectar();
            List<Prestamo> prestamos = em.createQuery("SELECT p FROM Prestamo p JOIN p.cliente c WHERE c.documento = :documento").setParameter("documento", documento).getResultList();
            
            desconectar();
            return prestamos;
        } catch (NoResultException e) {
            System.out.println("Prestamos no encontrados");  
            return null;
            
        
        }
    }
     public Prestamo buscarporId (String id){
        conectar();
        Prestamo prestamo = (Prestamo) em.createQuery("SELECT p FROM Prestamo p WHERE p.id LIKE :id").setParameter("id", id).getSingleResult();
        desconectar();
        return prestamo;
    }
   
     
  
}
