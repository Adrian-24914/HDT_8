/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estrcuturas de Datos
 * Ing. Douglas Barrios / Aux: Cristian Túnchez
 * @author: Adrian Penagos y Andrés Ismalej
*/
package uvg.com;

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
