/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estrcuturas de Datos
 * Ing. Douglas Barrios / Aux: Cristian Túnchez
 * @author: Adrian Penagos y Andrés Ismalej
 */

 package hdt8.src.main.java.uvg.com;

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
 
