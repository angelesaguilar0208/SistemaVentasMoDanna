
package modelo;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
public class Conexion {
    Connection con;
    String url="jdbc:mysql://localhost:3306/bdventasmodanna";
    String user="root";
    String pass="";
    public Connection Conectar(){
            try {
                   Class.forName("com.mysql.jdbc.Driver"); 
                   con= DriverManager.getConnection(url, user, pass);
            } catch (Exception e) {
            }
       return con;
    }
}
