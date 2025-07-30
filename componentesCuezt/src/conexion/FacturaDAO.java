package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import logico.Combo;
import logico.Componente;
import logico.Factura;


public class FacturaDAO {

	public static boolean agregarFactura(Factura factura) {
	    Connection conn = Conexion.getInstance().getConexion();
	    boolean resultado = false;

	    try {
	        conn.setAutoCommit(false);

	        String sql = "INSERT INTO facturas (id_cliente, fechaPedido, precioTotal) VALUES (?,?,?)";
	        PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	        ps.setInt(1, factura.getCliente().getId_cliente());
	        ps.setDate(2, new java.sql.Date(factura.getFechaPedido()));
	        ps.setDouble(3, factura.getPrecioTotal());
	        ps.executeUpdate();

	        ResultSet rs = ps.getGeneratedKeys();
	        if (rs.next()) {
	            int idFacturaGenerada = rs.getInt(1);

	            String sqlDetalle = "INSERT INTO venta_detalle (id_factura, id_componente, id_combo, cantidad) VALUES (?,?,?,?)";
	            PreparedStatement psDetalle = conn.prepareStatement(sqlDetalle);

	            String sqlActualizarStock = "UPDATE componente SET cantidadDisponible = cantidadDisponible - ? WHERE id = ?";
	            PreparedStatement psStock = conn.prepareStatement(sqlActualizarStock);

	            for (Componente c : factura.getComponentesVendidos()) {
	                psDetalle.setInt(1, idFacturaGenerada);
	                psDetalle.setInt(2, c.getId());         
	                psDetalle.setNull(3, Types.INTEGER);
	                psDetalle.setInt(4, c.getCantidad());
	                psDetalle.addBatch();

	                psStock.setInt(1, c.getCantidad());
	                psStock.setInt(2, c.getId());
	                psStock.addBatch();
	            }

	           
	            for (Combo combo : factura.getCombosVendidos()) {
	                psDetalle.setInt(1, idFacturaGenerada);
	                psDetalle.setNull(2, Types.INTEGER);    
	                psDetalle.setInt(3, combo.getId());      
	                psDetalle.setInt(4, 1);                  
	                psDetalle.addBatch();
	            }

	            psDetalle.executeBatch();
	            psStock.executeBatch();

	            psDetalle.close();
	            psStock.close();
	        }

	        rs.close();
	        ps.close();

	        conn.commit();
	        resultado = true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            conn.rollback();
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	    } finally {
	        try {
	            conn.setAutoCommit(true);
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }

	    return resultado;
	}
	
	


}
