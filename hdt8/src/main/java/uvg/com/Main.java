/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estrcuturas de Datos
 * Ing. Douglas Barrios / Aux: Cristian Túnchez
 * @author: Adrian Penagos y Andrés Ismalej
<<<<<<< HEAD
*/
package uvg.com;
=======
 */

package uvg.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.List;
>>>>>>> 5cf561d48c861c7a83697018814a2cbf3b1d9011

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Clase principal del sistema de emergencias
 * Permite al usuario elegir la implementación a utilizar
*/
public class Main {
<<<<<<< HEAD
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("-----Sala de emergencias-----");
        System.out.println("1. VectorHeap");
        System.out.println("2. Java Collection Framework PriorityQueue");
        System.out.print("\nElija la implementación de cola a utilizar: ");
        
        int opcion = 0;
        
        // Validar la entrada del usuario
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                if (opcion == 1 || opcion == 2) {
                    entradaValida = true;
                } else {
                    System.out.print("Opción no válida. Intente nuevamente (1 o 2): ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Error de entrada... ");
            }
        }
        
        try {
            // Crear la cola de pacientes según la opción elegida usando la Factory
            ColaFactory.ColaPacientes colaPacientes = ColaFactory.crearColaPacientes(opcion);
            
            // Usar el lector para cargar los pacientes en la cola
            Lector lector = new Lector("hdt8\\src\\main\\java\\uvg\\com\\pacientes.txt");
            
            // Cargamos todos los pacientes en la cola
            for (Paciente paciente : lector.leerPacientes()) {
                colaPacientes.agregarPaciente(paciente);
            }
            
            System.out.println("\nUsando: " + colaPacientes.getTipoImplementacion());
            System.out.println("\nPor favor, llamar a los siguientes pacientes:");
            System.out.println("---------------------------------");
            
            // Atender a los pacientes según su prioridad
            int contador = 1;
            while (colaPacientes.hayPacientes()) {
                Paciente paciente = colaPacientes.siguientePaciente();
                System.out.println(contador + ". " + paciente);
                contador++;
            }
            
        } catch (IOException e) {
            System.err.println("\nError al leer el archivo de pacientes: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("\nError: " + e.getMessage());
        } finally {
            scanner.close();
=======
    public static void main(String[] args) {
        Lector lector = new Lector("pacientes.txt");
        List<Paciente> pacientes = lector.leerPacientes();

        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes en la lista.");
            return;
        }

        VectorHeap<Paciente> colaPrioridad = new VectorHeap<>();

        // Insertar los pacientes en la cola de prioridad
        for (Paciente paciente : pacientes) {
            colaPrioridad.insert(paciente);
        }

        // Atender pacientes en orden de prioridad
        System.out.println("Atendiendo pacientes en orden de prioridad:");
        while (!colaPrioridad.isEmpty()) {
            System.out.println(colaPrioridad.remove());
>>>>>>> 5cf561d48c861c7a83697018814a2cbf3b1d9011
        }
    }
}
