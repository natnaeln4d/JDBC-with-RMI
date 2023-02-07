import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface Database extends Remote {
    public String store(String name,String age,String  id) throws RemoteException, ClassNotFoundException, SQLException;
    public String fetch(String name,String age,String id) throws RemoteException, ClassNotFoundException, SQLException;
}
