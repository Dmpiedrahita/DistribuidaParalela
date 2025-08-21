package Implement;

import Interface.SalariosInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class SalariosImplement extends UnicastRemoteObject implements SalariosInterface {

    private int[][] matrizSalarios;
    private int empleados;
    private int meses;

    public SalariosImplement() throws RemoteException {
        super();
    }

    @Override
    public void llenarMatriz(int empleados, int meses) throws RemoteException {
        this.empleados = empleados;
        this.meses = meses;
        matrizSalarios = new int[empleados][meses];

        Random rnd = new Random();
        for (int i = 0; i < empleados; i++) {
            for (int j = 0; j < meses; j++) {
                matrizSalarios[i][j] = rnd.nextInt(1000000) + 1; // salarios aleatorios
            }
        }
        System.out.println("Matriz generada con " + empleados + " empleados y " + meses + " meses.");
    }

    @Override
    public int[] totalPorEmpleado() throws RemoteException {
        int[] totales = new int[empleados];
        for (int i = 0; i < empleados; i++) {
            int suma = 0;
            for (int j = 0; j < meses; j++) {
                suma += matrizSalarios[i][j];
            }
            totales[i] = suma;
        }
        return totales;
    }

    @Override
    public double[] promedioPorMes() throws RemoteException {
        double[] promedios = new double[meses];
        for (int j = 0; j < meses; j++) {
            int suma = 0;
            for (int i = 0; i < empleados; i++) {
                suma += matrizSalarios[i][j];
            }
            promedios[j] = (double) suma / empleados;
        }
        return promedios;
    }

    @Override
    public int totalGeneral() throws RemoteException {
        int total = 0;
        for (int i = 0; i < empleados; i++) {
            for (int j = 0; j < meses; j++) {
                total += matrizSalarios[i][j];
            }
        }
        return total;
    }
}
