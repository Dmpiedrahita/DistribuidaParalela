package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SalariosInterface extends Remote{
    //int calculate(int numEmpleados, int numMeses, int choice) throws RemoteException;
    void llenarMatriz(int empleados, int meses) throws RemoteException;
    int[] totalPorEmpleado() throws RemoteException;
    double[] promedioPorMes() throws RemoteException;
    int totalGeneral() throws RemoteException;
}
