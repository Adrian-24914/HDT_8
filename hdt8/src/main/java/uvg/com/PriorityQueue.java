/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estrcuturas de Datos
 * Ing. Douglas Barrios / Aux: Cristian Túnchez
 * @author: Adrian Penagos y Andrés Ismalej
 */

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

