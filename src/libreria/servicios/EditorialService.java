/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Editorial;
import libreria.persistencia.editorialDAO;

/**
 *
 * @author juansebam
 */
public class EditorialService {
    
    private editorialDAO dao;

    public EditorialService(editorialDAO dao) {
        this.dao = new editorialDAO();
    }
        public Editorial crearEditorial(String nombre) throws Exception{
        Editorial editorial = new Editorial();
        Scanner sc = new Scanner(System.in);
        try {
             if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("Error, el nombre de la editorial no puede estar vacio");
             
            }
            if(buscarPornormbre(nombre).getNombre().equalsIgnoreCase(nombre)){
                throw new Exception ("Error, el nombre de la editorial esta repetido");
            }
        
            editorial.setNombre(nombre);
            System.out.println("Indique V o F si quiere dar de alta al Autor");
            String resp = sc.next();
            if (resp.equalsIgnoreCase("v") ) {
                System.out.println("Se indico el Alta");
                editorial.setAlta(true);
            }
            if (resp.equalsIgnoreCase("f")) {
                System.out.println("Se nego el alta");
                editorial.setAlta(false);
            }
            dao.guardar(editorial);
            return editorial;
        } catch (Exception e) {
            throw e;
        }
    }
    public void eliminarEditorial(String nombre) throws Exception{
        try {
            Editorial editorial = dao.buscarporNombre(nombre);
            if (editorial == null) {
                throw new Exception ("No se consigno un objeto corrrecto");
            }else{
                System.out.println("Editorial " + editorial.getNombre() + " eliminado");
                dao.eliminar(editorial);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    public Editorial editarAltaoBaja(String nombre) throws Exception{
        try {
            Editorial editorial = dao.buscarporNombre(nombre);
             if (editorial == null) {
                throw new Exception ("No se consigno un objeto corrrecto");
            }else{
                 System.out.println("Se cambia el alta o baja");
                 if(editorial.isAlta() == true){
                     editorial.setAlta(false);
                 }else{
                     editorial.setAlta(true);
                 }
             }
             System.out.println("El estado actual de Alta o Baja es: " + editorial.isAlta());
             dao.editar(editorial);
             return editorial;
             
        } catch (Exception e) {
            throw e;
        }
    }
    public List<Editorial> listarEditoriales() throws Exception{
        try {
            List <Editorial> editoriales = dao.listarTodos();
            if(editoriales == null){
                throw new Exception ("La lista confeccionada es incorrecta");
                
            }else{
            return editoriales;
            }
        } catch (Exception e) {
            throw e;
        }
    }
    public Editorial buscarPornormbre(String nombre) throws Exception{
        try {
            return dao.buscarporNombre(nombre);
        } catch (Exception e) {
            throw e;
        }
    }
    
}
