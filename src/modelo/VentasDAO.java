
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VentasDAO {
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r=0;
    
    public String NroSerieVenta(){
       String serie="";
       String sql="SELECT max(NumeroVentas) FROM ventas";
        try {
            con=cn.Conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                serie= rs.getString(1);
            }
        } catch (Exception e) {
        }
        return serie;
    }
    public String IdVentas(){
        String idv="";
        String sql="SELECT max(IdVentas) FROM ventas";
        try {
            con=cn.Conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                idv=rs.getString(1);
            }
        } catch (Exception e) {
        }
        return idv;
    }
    
    public int GuardarVentas(Ventas v){
        Ventas ventas=new Ventas();
        String sql="INSERT INTO ventas(IdCliente,IdVendedor,NumeroVentas,fechaVentas,monto,estado)values(?,?,?,?,?,?)";
        try {
            con=cn.Conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, v.getIdCliente());
            ps.setInt(2, v.getIdVendedor());
            ps.setString(3, v.getNumVentas());
            ps.setString(4, v.getFecha());
            ps.setDouble(5, v.getMonto());
            ps.setString(6, v.getEstado());
            r=ps.executeUpdate();
        } catch (Exception e) {
        }
                
        return r;
    }
    
    public int GuardarDetalleVentas(DetalleVentas dv){
        String sql="INSERT INTO detalleventas(IdVentas,IdProducto,cantidad,PrecioVenta)values(?,?,?,?)";
        try {
            con=cn.Conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, dv.getIdVentas());
            ps.setInt(2, dv.getIdProducto());
            ps.setInt(3, dv.getCantidad());
            ps.setDouble(4, dv.getPreVenta());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
}
