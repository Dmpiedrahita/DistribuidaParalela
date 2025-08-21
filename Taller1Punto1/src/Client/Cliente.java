/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Interface.SalariosInterface;

import java.rmi.Naming;
import java.util.Scanner;


public class Cliente {
    public static void main(String[] args) {
        try {
            SalariosInterface calc = (SalariosInterface) Naming.lookup("rmi://localhost/SalariosService");
            Scanner sc = new Scanner(System.in);

            System.out.print("Ingrese número de empleados: ");
            int empleados = sc.nextInt();
            System.out.print("Ingrese número de meses: ");
            int meses = sc.nextInt();

            // Generar matriz de salarios en el servidor
            calc.llenarMatriz(empleados, meses);

            // Consultar resultados
            int[] totales = calc.totalPorEmpleado();
            double[] promedios = calc.promedioPorMes();
            int totalGeneral = calc.totalGeneral();

            System.out.println("\n--- Totales por Empleado ---");
            for (int i = 0; i < empleados; i++) {
                System.out.println("Empleado " + (i+1) + ": $" + totales[i]);
            }

            System.out.println("\n--- Promedio por Mes ---");
            for (int j = 0; j < meses; j++) {
                System.out.println("Mes " + (j+1) + ": $" + promedios[j]);
            }

            System.out.println("\nTotal General: $" + totalGeneral);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}