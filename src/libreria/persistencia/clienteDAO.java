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
import libreria.entidades.Cliente;

/**
 *
 * @author juans
 */
public class clienteDAO {
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
    public void guardar (Cliente cliente){
        conectar();
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        desconectar();
    }
    public void eliminar (Cliente cliente){
        conectar();
        em.getTransaction().begin();
        em.remove(cliente);
        em.getTransaction().commit();
        desconectar();
    }
    public void editar (Cliente cliente){
        conectar();
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
        desconectar();
    }
    public List <Cliente> listarTodos(){
        conectar();
        List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c").getResultList();
        desconectar();
        return clientes;
    }
    public Cliente buscarporDNI(long documento) throws Exception{
        try {
            conectar();
            Cliente cliente = (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.documento = :documento").setParameter("documento", documento).getSingleResult();
            desconectar();
            return cliente;
        } catch (NoResultException e) {
            System.out.println("No hubo resultado");
        return null;
        }
    }
    public Cliente buscarporCel (String telefono) throws Exception{
        try {
            conectar();
            Cliente cliente = (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.telefono LIKE :telefono").setParameter("telefono", telefono).getSingleResult();
            desconectar();
            return cliente;
        } catch (NoResultException e) {
            System.out.println("No hubo resultado");
            return null;
        }
    }
}
