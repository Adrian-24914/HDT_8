/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estrcuturas de Datos
 * Ing. Douglas Barrios / Aux: Cristian Túnchez
 * @author: Adrian Penagos y Andrés Ismalej
*/
package uvg.com;

<<<<<<< HEAD
/**
 * Interfaz para una cola con prioridad genérica.
 * @param <E> Tipo de elementos que se almacenarán en la cola
*/
public interface PriorityQueue<E extends Comparable<E>> {
    
    /**
     * Agrega un elemento a la cola con prioridad.
     * @param elemento Elemento a agregar
     */
    void add(E elemento);
    
    /**
     * Obtiene y elimina el elemento con mayor prioridad.
     * @return El elemento con mayor prioridad
     */
    E remove();
    
    /**
     * Consulta el elemento con mayor prioridad sin eliminarlo.
     * @return El elemento con mayor prioridad
     */
    E getFirst();
    
    /**
     * Verifica si la cola está vacía.
     * @return true si la cola está vacía, false en caso contrario
     */
    boolean isEmpty();
    
    /**
     * Obtiene el tamaño de la cola.
     * @return Número de elementos en la cola
     */
    int size();
    
    /**
     * Elimina todos los elementos de la cola.
     */
    void clear();
}
=======
package hdt8.src.main.java.uvg.com;
import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PriorityQueue {
    public static void main(String[] args) {
        PriorityQueue<Paciente> cola = new PriorityQueue<>();
        
        try (Scanner scanner = new Scanner(new File("pacientes.txt"))) {
            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(", ");
                cola.add(new Paciente(datos[0], datos[1], datos[2].charAt(0)));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo pacientes.txt no encontrado.");
            return;
        }

        System.out.println("Atendiendo pacientes en orden de prioridad:");
        while (!cola.isEmpty()) {
            System.out.println(cola.poll());
        }
    }
}

>>>>>>> 5cf561d48c861c7a83697018814a2cbf3b1d9011
