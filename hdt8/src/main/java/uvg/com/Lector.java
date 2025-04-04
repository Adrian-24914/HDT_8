/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estrcuturas de Datos
 * Ing. Douglas Barrios / Aux: Cristian Túnchez
 * @author: Adrian Penagos y Andrés Ismalej
*/

package uvg.com;

<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase encargada de leer archivos CSV de pacientes.
*/
public class Lector {
    
    private String rutaArchivo;
    
    /**
     * Constructor de la clase Lector
     * @param rutaArchivo Ruta del archivo CSV a leer
    */
    public Lector(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }
    
    /**
     * Lee el archivo CSV y crea una lista de pacientes
     * @return Lista de pacientes leídos del archivo
     * @throws IOException Si ocurre algún error durante la lectura
    */
    public ArrayList<Paciente> leerPacientes() throws IOException {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            
            // Leer cada línea del archivo
            while ((linea = lector.readLine()) != null) {
                // Dividir la línea por comas, considerando que puede haber más de 2 comas
                String[] partes = linea.split(", ", 3);
                
                if (partes.length >= 3) {
                    String nombre = partes[0];
                    String sintoma = partes[1];
                    char codigoEmergencia = partes[2].charAt(0);
                    
                    // Crear un paciente y agregarlo a la lista
                    Paciente paciente = new Paciente(nombre, sintoma, codigoEmergencia);
                    pacientes.add(paciente);
                } else {
                    System.err.println("Formato inválido en línea: " + linea);
                }
            }
        }
        
        return pacientes;
    }
    
     /**
     * Lee el archivo CSV y añade los pacientes directamente a una cola de prioridad implementada con VectorHeap
     * @param cola Cola de prioridad donde se añadirán los pacientes
     * @throws IOException Si ocurre algún error durante la lectura
     */
    public void cargarPacientesEnVectorHeap(VectorHeap<Paciente> cola) throws IOException {
        ArrayList<Paciente> pacientes = leerPacientes();
        for (Paciente paciente : pacientes) {
            cola.add(paciente);
        }
    }
    
    /**
     * Lee el archivo CSV y añade los pacientes directamente a una cola de prioridad de Java Collection Framework
     * @param cola Cola de prioridad de Java Collection Framework
     * @throws IOException Si ocurre algún error durante la lectura
     */
    public void cargarPacientesEnJCF(java.util.PriorityQueue<Paciente> cola) throws IOException {
        ArrayList<Paciente> pacientes = leerPacientes();
        for (Paciente paciente : pacientes) {
            cola.add(paciente);
        }
    }
    
    /**
     * Carga los pacientes en una cola creada por la Factory
     * @param cola Cola de pacientes creada por la Factory
     * @throws IOException Si ocurre algún error durante la lectura
     */
    public void cargarPacientesEnCola(ColaFactory.ColaPacientes cola) throws IOException {
        ArrayList<Paciente> pacientes = leerPacientes();
        
        for (Paciente paciente : pacientes) {
            cola.agregarPaciente(paciente);
        }
    }
}
=======
 import java.io.*;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Scanner;
 
 public class Lector {
     private String filePath;
 
     public Lector(String filePath) {
         this.filePath = filePath;
     }
 
     public List<Paciente> leerPacientes() {
         List<Paciente> pacientes = new ArrayList<>();
         try (Scanner scanner = new Scanner(new File(filePath))) {
             while (scanner.hasNextLine()) {
                 String[] datos = scanner.nextLine().split(", ");
                 if (datos.length == 3) { // Asegurar que el formato es correcto
                     pacientes.add(new Paciente(datos[0], datos[1], datos[2].charAt(0)));
                 }
             }
         } catch (FileNotFoundException e) {
             System.err.println("Archivo " + filePath + " no encontrado.");
         }
         return pacientes;
     }
 }
 
>>>>>>> 5cf561d48c861c7a83697018814a2cbf3b1d9011
