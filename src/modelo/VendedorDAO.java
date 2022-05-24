
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class VendedorDAO implements CRUD {
    PreparedStatement ps;
    ResultSet rs;
    
    EntidadVendedor ev= new EntidadVendedor();
    Conexion con= new Conexion();
    Connection acceso;
    
    public EntidadVendedor ValidarVendedor(String dni,String user){
        String sql="select * from vendedor where dni=? and user=?";
        try {
            acceso=con.Conectar();
            ps=acceso.prepareStatement(sql);
            
            ps.setString(1, dni);
            ps.setString(2, user);
            rs=ps.executeQuery();
            while(rs.next()){
                ev.setId(rs.getInt(1));
                ev.setDni(rs.getString(2));
                ev.setNombre(rs.getString(3));
                ev.setTelefono(rs.getString(4));
                ev.setEstado(rs.getString(5));
                ev.setUser(rs.getString(6));
               
            }
        } catch (Exception e) {
        }
    return ev;
    }

    @Override
    public List listar() {
        String sql= "SELECT * FROM vendedor";
       List<EntidadVendedor> listaven = new ArrayList<>();
       try {
           acceso=con.Conectar();
           ps = acceso.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {
               EntidadVendedor v = new EntidadVendedor();
               v.setId(rs.getInt(1));
               v.setDni(rs.getNString(2));
               v.setNombre(rs.getString(3));
               v.setTelefono(rs.getString(4));
               v.setEstado(rs.getString(5));
               v.setUser(rs.getString(6));
               listaven.add(v);
               
           }
       } catch (Exception e) {
           System.out.println(e.toString());
       }
       return listaven;
    }

    @Override
    public int add(Object[] o) {
        int r=0;
       String sql= "INSERT INTO vendedor (dni,nombres,telefono,estado, user)VALUES (?,?,?,?,?)";
       try {
            acceso=con.Conectar();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            r=ps.executeUpdate();
        } catch (Exception e) {
           System.out.println(e.toString());
        }
       return r;
    }

    @Override
    public int actualizar(Object[] o) {
       int r=0;
       String sql="update vendedor set Dni=?,nombres=?,telefono=?,estado=?,user=? where IdCliente=?"; 
        try {
            acceso=con.Conectar();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            ps.executeUpdate();
            r=ps.executeUpdate(); 
       } catch (Exception e) {
        }
       return r;
    }

    @Override
    public void eliminar(int id) {
         String sql="delete from vendedor where IdVendedor=?"; 
         try {
            acceso=con.Conectar();
            ps = acceso.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
