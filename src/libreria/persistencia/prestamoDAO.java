/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
        List<Prestamo> clientes = em.createQuery("SELECT p FROM Prestamo p").getResultList();
        desconectar();
        return clientes;
    }
    public Prestamo buscarprestamosDNI(long documento) throws Exception{
        try {
            conectar();
            Prestamo prestamo = (Prestamo) em.createQuery("SELECT p FROM Prestamo p WHERE c.documento = :documento").setParameter("documento", documento).getSingleResult();
            desconectar();
            return prestamo;
        } catch (Exception e) {
            e.printStackTrace();
        return null;
        }
    }
  
}
