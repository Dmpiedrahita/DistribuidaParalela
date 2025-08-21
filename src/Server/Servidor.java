package Server;

import Implement.SalariosImplement;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            SalariosImplement calcImplement = new SalariosImplement();
            reg.rebind("SalariosService", calcImplement);
            System.out.println("Servidor iniciado...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
