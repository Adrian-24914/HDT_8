/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estrcuturas de Datos
 * Ing. Douglas Barrios / Aux: Cristian Túnchez
 * @author: Adrian Penagos y Andrés Ismalej
 */

package uvg.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.List;

public class Main {
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
        }
    }
}
