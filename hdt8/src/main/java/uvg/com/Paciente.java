/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estrcuturas de Datos
 * Ing. Douglas Barrios / Aux: Cristian Túnchez
 * @author: Adrian Penagos y Andrés Ismalej
<<<<<<< HEAD
*/

 package uvg.com;

/**
 * Clase del paciente con su información e implementación de Comparable para ordenar los pacientes.
*/
public class Paciente implements Comparable<Paciente> {
    private String nombre;
    private String sintoma;
    private char clasificacion;

    
    /**
     * Constructor de la clase
     * @param nombre Nombre del paciente
     * @param sintoma Sintoma del paciente
     * @param clasificacion clasificacion de emergencia del paciente
    */
    public Paciente(String nombre, String sintoma, char clasificacion) {
        this.nombre = nombre;
        this.sintoma = sintoma;
        this.clasificacion = clasificacion;
    }

    /**
     * Obtiene el nombre del paciente
     * @return Nombre
    */
=======
 */

package hdt8.src.main.java.uvg.com;

public class Paciente implements Comparable<Paciente> {
    private String nombre;
    private String sintoma;
    private char prioridad; // A es la más urgente, E la menos urgente

    public Paciente(String nombre, String sintoma, char prioridad) {
        this.nombre = nombre;
        this.sintoma = sintoma;
        this.prioridad = prioridad;
    }

>>>>>>> 5cf561d48c861c7a83697018814a2cbf3b1d9011
    public String getNombre() {
        return nombre;
    }

<<<<<<< HEAD
    /**
     * Obtiene el sintoma del paciente 
     * @return Sintoma
    */
=======
>>>>>>> 5cf561d48c861c7a83697018814a2cbf3b1d9011
    public String getSintoma() {
        return sintoma;
    }

<<<<<<< HEAD
    /**
     * Obtiene la clasificación del paciente 
     * @return Clasificacion
    */
    public char getClasificacion() {
        return clasificacion;
    }

    /**
     * Compara la clasificación de los pacientes
     * @param pacienteX Paciente a comparar
     * @return valor negativo si Paciente tiene mayor prioridad
     *         valor positivo si tiene menor prioridad o cero si son iguales.
    */
    @Override 
    public int compareTo(Paciente pacienteX) {
        return this.clasificacion - pacienteX.clasificacion;
    }
    
    /**
     * Información en texto del paciente
     * @return Texto
     */
    @Override
    public String toString() {
        return nombre + ", " + sintoma + ", " + clasificacion;
=======
    public char getPrioridad() {
        return prioridad;
    }

    @Override
    public int compareTo(Paciente otro) {
        return Character.compare(this.prioridad, otro.prioridad);
    }

    @Override
    public String toString() {
        return nombre + ", " + sintoma + ", " + prioridad;
>>>>>>> 5cf561d48c861c7a83697018814a2cbf3b1d9011
    }
}
