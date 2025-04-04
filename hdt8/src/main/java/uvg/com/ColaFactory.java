/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estrcuturas de Datos
 * Ing. Douglas Barrios / Aux: Cristian Túnchez
 * @author: Adrian Penagos y Andrés Ismalej
*/

package uvg.com;
/**
 * Factory para crear diferentes implementaciones de colas de pacientes
 * Implementa el patrón de diseño Factory
*/
public class ColaFactory {
    
    // Tipos de colaS
    public static final int TIPO_VECTORHEAP = 1;
    public static final int TIPO_JCF = 2;
    
    /**
     * Crea la implementación de cola de pacientes según el tipo solicitado
     * @param tipo Tipo de cola
     * @return Un objeto que puede manejar colas de pacientes 
     */
    public static ColaPacientes crearColaPacientes(int tipo) {
        switch (tipo) {
            case TIPO_VECTORHEAP:
                return new ColaPacientesVector();
            case TIPO_JCF:
                return new ColaPacientesJCF();
            default:
                throw new IllegalArgumentException("Tipo de cola no válido: " + tipo);
        }
    }
    
    /**
     * Interfaz para las diferentes implementaciones de colas de pacientes
    */
    public interface ColaPacientes {
        /**
         * Agrega un paciente a la cola
         * @param paciente Paciente a agregar
        */
        void agregarPaciente(Paciente paciente);
        
        /**
         * Obtiene y elimina el siguiente paciente a ser atendido
         * @return El siguiente paciente a atender o null si la cola está vacía
        */
        Paciente siguientePaciente();
        
        /**
         * Verifica si hay más pacientes en la cola
         * @return true si hay pacientes, false si está vacía
        */
        boolean hayPacientes();
        
        /**
         * Obtiene el tipo de implementación que se está usando
         * @return Descripción del tipo de implementación
        */
        String getTipoImplementacion();
    }
    
    /**
     * Implementación que usa VectorHeap
    */
    private static class ColaPacientesVector implements ColaPacientes {
        private VectorHeap<Paciente> cola;
        
        public ColaPacientesVector() {
            cola = new VectorHeap<>();
        }
        
        @Override
        public void agregarPaciente(Paciente paciente) {
            cola.add(paciente);
        }
        
        @Override
        public Paciente siguientePaciente() {
            if (cola.isEmpty()) {
                return null;
            }
            return cola.remove();
        }
        
        @Override
        public boolean hayPacientes() {
            return !cola.isEmpty();
        }
        
        @Override
        public String getTipoImplementacion() {
            return "Implementación VectorHeap";
        }
    }
    
    /**
     * Implementación que usa Java Collection Framework
    */
    private static class ColaPacientesJCF implements ColaPacientes {
        private java.util.PriorityQueue<Paciente> cola;
        
        public ColaPacientesJCF() {
            cola = new java.util.PriorityQueue<>();
        }
        
        @Override
        public void agregarPaciente(Paciente paciente) {
            cola.add(paciente);
        }
        
        @Override
        public Paciente siguientePaciente() {
            return cola.poll();
        }
        
        @Override
        public boolean hayPacientes() {
            return !cola.isEmpty();
        }
        
        @Override
        public String getTipoImplementacion() {
            return "Implementación JCF PriorityQueue";
        }
    }
}
