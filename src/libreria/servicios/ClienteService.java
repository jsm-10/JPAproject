/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.servicios;

import java.util.List;
import libreria.entidades.Cliente;
import libreria.persistencia.clienteDAO;

/**
 *
 * @author juans
 */
public class ClienteService {
    private clienteDAO dao;

    public ClienteService() {
        this.dao = new clienteDAO();
    }
    public Cliente crearCliente (long documento, String nombre, String apellido, String telefono ) throws Exception{
        Cliente xDni = buscarporDNI(documento);
        Cliente xCel = busquedaporTel(telefono);
        try {
            if(nombre == null || nombre.trim().isEmpty()){
                System.out.println("Error, nombre invalido o inexistente");
                return null;
            }
            if(xDni != null){
                System.out.println("Error, DNI utilizado");
                return null;
            }
            if(xCel != null){
                System.out.println("Error, Celular utilizado");
            }
            
            Cliente cliente = new Cliente();
            cliente.setDocumento(documento);
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setTelefono(telefono);
            dao.guardar(cliente);
            System.out.println("Cliente Creado");
            dao.guardar(cliente);
            return cliente;
        } catch (Exception e) {
            throw new Exception("Error al crear el cliente");
        }
    }
    public List<Cliente> listarCliente(){
        try {
            List <Cliente> clientes = dao.listarTodos();
            if(clientes == null){
                System.out.println("No existe lista de clientes");
                return null;
            }else{
                for(Cliente ct : clientes){
                    System.out.println(ct);
                }
            }
            return clientes;
        
        } catch (Exception e) {
            throw e;
        }
    }
    
   public Cliente buscarporDNI(long documento){
       try {
           return dao.buscarporDNI(documento);
       } catch (Exception e) {
           return null;
           
       }
   }
   public Cliente busquedaporTel(String telefono) throws Exception{
       try {
           return dao.buscarporCel(telefono);
       } catch (Exception e) {
           return null;
       }
   }
    
    
}
