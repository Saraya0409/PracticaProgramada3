/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ConexionBDModelo;
import Modelo.ModeloProducto;
import Vista.VistaProductos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yagoa
 */
public class ControladorProducto {

    private ConexionBDModelo conexionDB;

    public ControladorProducto() {
        this.conexionDB = new ConexionBDModelo(); 
    }

    
    public boolean añadirProducto(ModeloProducto producto) {
        Connection conn = conexionDB.conectar();
        String sql = "INSERT INTO Producto (nombre, precio) VALUES (?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecio());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    
    public List<ModeloProducto> listarProductos() {
        List<ModeloProducto> productos = new ArrayList<>();
        Connection conn = conexionDB.conectar();
        String sql = "SELECT * FROM Producto";

        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ModeloProducto producto = new ModeloProducto(
                        resultSet.getInt("id_producto"),
                        resultSet.getString("nombre"),
                        resultSet.getDouble("precio"),
                        resultSet.getInt("cantidad")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return productos;
    }

    public DefaultTableModel obtenerProductos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void agregarProductoACarrito(int filaSeleccionada, VistaProductos aThis) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
