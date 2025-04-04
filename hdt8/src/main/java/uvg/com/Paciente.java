/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estrcuturas de Datos
 * Ing. Douglas Barrios / Aux: Cristian Túnchez
 * @author: Adrian Penagos y Andrés Ismalej
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
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el sintoma del paciente 
     * @return Sintoma
    */
    public String getSintoma() {
        return sintoma;
    }

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
    }
}
