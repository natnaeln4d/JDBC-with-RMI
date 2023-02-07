import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.Scanner;

public class Student {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException, SQLException, ClassNotFoundException {
      String url="localhost";
      Registry registry= LocateRegistry.getRegistry(url);
        Database stub=(Database)registry.lookup("StudentName");
        String name;
        String age;
        String id;
        Scanner in=new Scanner(System.in);
        System.out.println("enter ur name:\n");
        name=in.nextLine();
        System.out.println("enter ur age:\n");
        age=in.nextLine();
        System.out.println("enter ur id:\n");
        id=in.nextLine();
        stub.store(name,age,id);
      System.out.println(stub.fetch(name,age,id));

}
}
