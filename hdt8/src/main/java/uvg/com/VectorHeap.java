<<<<<<< HEAD
/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estrcuturas de Datos
 * Ing. Douglas Barrios / Aux: Cristian Túnchez
 * @author: Adrian Penagos y Andrés Ismalej
*/

package uvg.com;

import java.util.Vector;

/**
 * Implementación de una cola con prioridad basada en un heap.
 * Esta implementación utiliza un Vector como estructura subyacente.
 * @param <E> Tipo de elementos en la cola
 */
public class VectorHeap<E extends Comparable<E>> implements PriorityQueue<E> {
    
    // Vector para almacenar los elementos del heap
    protected Vector<E> datos;
    
    /**
     * Constructor por defecto. Crea un heap vacío.
     */
    public VectorHeap() {
        datos = new Vector<E>();
    }
    
    /**
     * Obtiene el índice del padre de un nodo.
     * @param i Índice del nodo
     * @return Índice del padre
     */
    protected int padre(int i) {
        return (i - 1) / 2;
    }
    
    /**
     * Obtiene el índice del hijo izquierdo de un nodo.
     * @param i Índice del nodo
     * @return Índice del hijo izquierdo
     */
    protected int hijoIzquierdo(int i) {
        return 2 * i + 1;
    }
    
    /**
     * Obtiene el índice del hijo derecho de un nodo.
     * @param i Índice del nodo
     * @return Índice del hijo derecho
     */
    protected int hijoDerecho(int i) {
        return 2 * i + 2;
    }
    
    /**
     * Restablece la propiedad del heap moviendo un valor hacia arriba.
     * @param hoja Índice de la hoja a mover hacia arriba
     */
    protected void moverArriba(int hoja) {
        int indPadre = padre(hoja);
        E valor = datos.get(hoja);
        
        // Mientras no llegue a la raíz y el valor tenga mayor prioridad que su padre
        while (hoja > 0 && valor.compareTo(datos.get(indPadre)) < 0) {
            // Mover el padre hacia abajo
            datos.set(hoja, datos.get(indPadre));
            hoja = indPadre;
            indPadre = padre(hoja);
        }
        
        // Colocar el valor en su posición final
        datos.set(hoja, valor);
    }
    
    /**
     * Restablece la propiedad del heap moviendo un valor hacia abajo.
     * @param raiz Índice de la raíz del subárbol
     */
    protected void moverAbajo(int raiz) {
        int hijoMenor;
        int tamanoHeap = datos.size();
        E valor = datos.get(raiz);
        
        while (hijoIzquierdo(raiz) < tamanoHeap) {
            hijoMenor = hijoIzquierdo(raiz);
            
            // Si tiene hijo derecho y es menor que el izquierdo
            if (hijoDerecho(raiz) < tamanoHeap && 
                datos.get(hijoDerecho(raiz)).compareTo(datos.get(hijoMenor)) < 0) {
                hijoMenor = hijoDerecho(raiz);
            }
            
            // Si el valor tiene mayor o igual prioridad que el hijo menor, terminamos
            if (valor.compareTo(datos.get(hijoMenor)) <= 0) {
                break;
            }
            
            // Mover el hijo menor hacia arriba
            datos.set(raiz, datos.get(hijoMenor));
            raiz = hijoMenor;
        }
        
        // Colocar el valor en su posición final
        datos.set(raiz, valor);
    }
    
    /**
     * Agrega un elemento a la cola con prioridad.
     * @param elemento Elemento a agregar
     */
    @Override
    public void add(E elemento) {
        // Añadir el elemento al final
        datos.add(elemento);
        // Mover el elemento hacia arriba para mantener la propiedad del heap
        moverArriba(datos.size() - 1);
    }
    
    /**
     * Obtiene y elimina el elemento con mayor prioridad.
     * @return El elemento con mayor prioridad
     * @throws IndexOutOfBoundsException si la cola está vacía
     */
    @Override
    public E remove() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap está vacío");
        }
        
        E resultado = datos.get(0); // Obtener el elemento de mayor prioridad
        
        // Mover el último elemento a la raíz
        E ultimo = datos.remove(datos.size() - 1);
        
        if (!isEmpty()) {
            datos.set(0, ultimo);
            moverAbajo(0); // Restablecer la propiedad del heap
        }
        
        return resultado;
    }
    
    /**
     * Consulta el elemento con mayor prioridad sin eliminarlo.
     * @return El elemento con mayor prioridad
     * @throws IndexOutOfBoundsException si la cola está vacía
     */
    @Override
    public E getFirst() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap está vacío");
        }
        return datos.get(0);
    }
    
    /**
     * Verifica si la cola está vacía.
     * @return true si la cola está vacía, false en caso contrario
     */
    @Override
    public boolean isEmpty() {
        return datos.isEmpty();
    }
    
    /**
     * Obtiene el tamaño de la cola.
     * @return Número de elementos en la cola
     */
    @Override
    public int size() {
        return datos.size();
    }
    
    /**
     * Elimina todos los elementos de la cola.
     */
    @Override
    public void clear() {
        datos.clear();
    }
}
=======
package uvg.com;

import java.util.ArrayList;

public class VectorHeap<E extends Comparable<E>> {
    private ArrayList<E> heap;

    public VectorHeap() {
        heap = new ArrayList<>();
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index).compareTo(heap.get(parent)) < 0) {
                E temp = heap.get(index);
                heap.set(index, heap.get(parent));
                heap.set(parent, temp);
            }
            index = parent;
        }
    }

    private void siftDown(int index) {
        int size = heap.size();
        while (index * 2 + 1 < size) {
            int leftChild = index * 2 + 1;
            int rightChild = index * 2 + 2;
            int min = leftChild;
            if (rightChild < size && heap.get(rightChild).compareTo(heap.get(leftChild)) < 0) {
                min = rightChild;
            }
            if (heap.get(index).compareTo(heap.get(min)) <= 0) {
                break;
            }
            E temp = heap.get(index);
            heap.set(index, heap.get(min));
            heap.set(min, temp);
            index = min;
        }
    }

    public void insert(E item) {
        heap.add(item);
        siftUp(heap.size() - 1);
    }

    public E remove() {
        if (heap.isEmpty()) return null;
        E min = heap.get(0);
        E last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            siftDown(0);
        }
        return min;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
>>>>>>> 5cf561d48c861c7a83697018814a2cbf3b1d9011
