import javax.xml.transform.sax.SAXSource;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

public class Admin implements Database{
    String name;
    String age;
    String id;
    Admin()throws RemoteException{

    }

    @Override
    public String store(String name, String age, String id) throws RemoteException, ClassNotFoundException, SQLException {
        Connection con=null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/java_tuto";
        String username="natnaeln4d";
        String pwd="natty@123";
        con= DriverManager.getConnection(url,username,pwd);
        Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        String sql="insert into Person1(name,age,id) values('"+name+"','"+age+"','"+id+"')";
        int i=stmt.executeUpdate(sql);
        if (i==1)
        {
            System.out.println("created!");
        }

        return null;
    }

    @Override
    public String fetch(String name, String age, String id) throws RemoteException, ClassNotFoundException, SQLException {
        Connection con=null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/java_tuto";
        String username="natnaeln4d";
        String pwd="natty@123";
        con= DriverManager.getConnection(url,username,pwd);
        Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String sql="select * from Person1";
        ResultSet resultSet=stmt.executeQuery(sql);
        if (resultSet.next())
        {
            System.out.println("name:"+resultSet.getString("name"));
            System.out.println("age:"+resultSet.getString("age"));
            System.out.println("id:"+resultSet.getString("id"));
        }

        return name+":"+age+":"+id;

    }

    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
        Admin admin=new Admin();
       Database stub=(Database)UnicastRemoteObject.exportObject(admin,0);
        Registry registry= LocateRegistry.getRegistry();
        registry.rebind("StudentName",stub);
        System.out.println("server is started...");


    }
}
