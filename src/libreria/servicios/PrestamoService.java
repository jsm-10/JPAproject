/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    private EntityManager em;

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
           if(verif(documento, nombreLibro) == 2){
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
      public List<Prestamo> buscarPrestamo(long documento) throws Exception{
          try {
              List <Prestamo> prestamos = dao.buscarprestamosDNI(documento);
                  if(prestamos == null){
                      System.out.println("Lista Invalida"); 
                  }
                  for (Prestamo prestamo : prestamos) {
                      System.out.println("---------------------------------------------");
                      System.out.println("Cliente: " + prestamo.getCliente().getNombre() + " " + prestamo.getCliente().getTelefono());
                      System.out.println(prestamo.getLibro().getTitle());
                      System.out.println("----------------------------------------------");
              }
             return prestamos;     
          } catch (NoResultException e) {
              return null;
          }
    }
      
      public Libro devolucionEjemplar(long documento){
          setServicios(libroservice, clienteservice);
        try {
            List <Prestamo> prestamos = buscarPrestamo(documento);
            if(prestamos == null){
                System.out.println("Lista Inexistente o incorrecta");
                return null;
            }
       
         System.out.println("Seleccione el titulo que desea devolver: "); 
         Scanner sc = new Scanner (System.in);
         String resp = sc.nextLine();
            System.out.println(resp);
         eliminarPrestamo(prestamos, resp);
         Libro libro = libroservice.busquedaporTitulo(resp);
            if(libro.getEjemplaresPrestados() > 0){
                libro.setEjemplaresPrestados(libro.getEjemplaresPrestados()-1);
                libro.setEjemplares(libro.getEjemplares()+1);
                libro.setEjemplaresRestantes(libro.getEjemplaresRestantes()+1);
                System.out.println("Libro devuelto");
            }else{
                System.out.println("Ese libro no fue prestado");
            }
            libroservice.guardarCambios(libro);
            return libro;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
      public int verif(long documento, String titulo) throws Exception {
    int vbl = 0;
    List <Prestamo> prestamos = buscarPrestamo(documento);

    if (prestamos != null) {
        vbl += 1; // Increment vbl if a préstamo is found
        
        for (Prestamo prestamo : prestamos) {
            Libro libro = prestamo.getLibro();
            if (libro != null && libro.getTitle().equalsIgnoreCase(titulo)) {
            vbl += 1; // Increment vbl if the libro title matches
        } else {
            System.out.println("El cliente no tiene el libro '" + titulo + "' en préstamo.");
        }
    } 
    }else {
        System.out.println("El cliente no tiene libros en prestamo.");
    }
        return vbl;
        }
   public void eliminarPrestamo(List <Prestamo> prestamo, String title) throws Exception{
        try {
            for (Prestamo prestamo1 : prestamo) {
               if(prestamo1.getLibro().getTitle().equals(title)){
                   dao.eliminar(prestamo1);
                   System.out.println("Prestamo eliminado");
                   return;
               } 
            }       
            throw new NoResultException ("No se encontro el prestamo apra el libro con el titulo " + title);
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Ocurrio un error al eliminar el prestamo");
        }
    }  
  
    

}

