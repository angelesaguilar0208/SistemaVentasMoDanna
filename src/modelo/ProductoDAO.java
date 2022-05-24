
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ProductoDAO implements CRUD {
    int r;
    
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion acceso = new Conexion();
    
    
    Producto pro = new Producto();
    
    public int  actualizarStock(int cantidad, int idp){
       String sql= "update producto set stock=? where idProducto=?"; 
        try {
            con= acceso.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1,cantidad);
            ps.setInt(2,idp);
            rs = ps.executeQuery();
        } catch (Exception e) {
        }
        return r;
    }
    public Producto listarID(int id){
        Producto p=new Producto();
        String sql= "SELECT * FROM producto where IdProducto=?";
        try {
           con= acceso.Conectar();
           ps = con.prepareStatement(sql);
           ps.setInt(1,id);
           rs = ps.executeQuery();
           while(rs.next()){
               p.setIdproducto(rs.getInt(1));
               p.setNombres(rs.getString(2));
               p.setPrecio(rs.getDouble(3));
               p.setStock(rs.getInt(4));
               p.setEstado(rs.getString(5));
           }
        } catch (Exception e) {
        }
        return p;
    }
   
    @Override
    public List listar() {
        String sql= "SELECT * FROM producto";
       List<Producto> listaprod = new ArrayList<>();
       try {
            Conexion con = new Conexion();
            Connection acceso;
           acceso=con.Conectar();
           ps = acceso.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {
               Producto p = new Producto();
               p.setIdproducto(rs.getInt(1));
               p.setNombres(rs.getString(2));
               p.setPrecio(rs.getDouble(3));
               p.setStock(rs.getInt(4));
               p.setEstado(rs.getString(5));
               listaprod.add(p);
               
           }
       } catch (Exception e) {
           System.out.println(e.toString());
       }
       return listaprod;
    }

    @Override
    public int add(Object[] o) {
       int r=0;
       String sql= "INSERT INTO producto (nombres,precio, stock,estado)VALUES (?,?,?,?)";
       try {
            con= acceso.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            
            r=ps.executeUpdate();
        } catch (Exception e) {
           System.out.println(e.toString());
        }
       return r;
    }

    @Override
    public int actualizar(Object[] o) {
       int r=0;
       String sql="update producto set nombres=?,precio=?,stock=?,estado=? where IdProducto=?"; 
        try {
            con= acceso.Conectar();
            ps = con.prepareStatement(sql);
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
        String sql="delete from producto where IdProducto=?"; 
         try {
            con= acceso.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        
    }
    
}
