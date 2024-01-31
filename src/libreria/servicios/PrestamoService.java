/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.servicios;

import java.util.Scanner;
import libreria.entidades.Cliente;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;
import libreria.persistencia.prestamoDAO;

/**
 *
 * @author juans
 */
public class PrestamoService {
    Scanner sc = new Scanner(System.in);
    private prestamoDAO dao;
    private LibroService libroservice;
    private ClienteService clienteservice;

    public PrestamoService() {
        this.dao = new prestamoDAO();

    }
    public void setServicios (LibroService libroservice, ClienteService clienteservice){
        this.libroservice = new LibroService();
        this.clienteservice = new ClienteService();
    }
    public Prestamo crearPrestamo(long documento) throws Exception{
        setServicios(libroservice, clienteservice);
        System.out.println("Indique nombre del libro");
        String nombreLibro = sc.nextLine();
        System.out.println(nombreLibro);
        
        try {
           if(verif(documento, nombreLibro)){
               System.out.println("Ya fue prestado ese libro a ese cliente");
               return null;
           }
           Libro libroprestamo = libroservice.busquedaporTitulo(nombreLibro);
           if(libroprestamo == null){
               System.out.println("Libro a prestar no encontrado");
               return null;
           }
           Cliente clienteprestamo = clienteservice.buscarporDNI(documento);
           if(clienteprestamo == null){
               System.out.println("Cliente no encontrado");
               return null; 
           }
           Prestamo prestamo = new Prestamo();
           prestamo.setLibro(libroprestamo);
           prestamo.setCliente(clienteprestamo);
           libroservice.editarEjemplares(libroprestamo);
            System.out.println("Libro Prestado correctamente");
            dao.guardar(prestamo);
           return prestamo; 
        } catch (Exception e) {
            throw e;
        }
        
       
           
       } 
      public Prestamo buscarPrestamo(long documento){
          try {
              return dao.buscarprestamosDNI(documento);
          } catch (Exception e) {
              return null;
          }
    }
      public boolean verif(long documento, String titulo){
          boolean vbl = false;
          if(buscarPrestamo(documento) != null){
              vbl = true;
          }else{
              return vbl;
          }
          if (buscarPrestamo(documento).getLibro().getTitle().equalsIgnoreCase(titulo) ){
              vbl = true;
          }
          return vbl;
      }
}
