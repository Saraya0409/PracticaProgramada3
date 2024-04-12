/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.CompraModelo;
import Modelo.ConexionBDModelo;
import Modelo.ModeloProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author armi8
 */
public class ControladorCompra {
     private ConexionBDModelo conexionDB;


    public ControladorCompra() {
        this.conexionDB = new ConexionBDModelo(); 
    }

    
    public boolean crearCompra(CompraModelo compra) {
        Connection conn = conexionDB.conectar();
        String sqlCompra = "INSERT INTO Compra (fecha_hora, id_funcionario, total) VALUES (NOW(), ?, ?)";
        String sqlCompraProducto = "INSERT INTO Compra_Producto (id_compra, id_producto, cantidad) VALUES (?, ?, ?)";

        try {
            conn.setAutoCommit(false); 

            
            try (PreparedStatement statementCompra = conn.prepareStatement(sqlCompra, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statementCompra.setInt(1, compra.getIdFuncionario());
                statementCompra.setDouble(2, compra.getTotal());

                int affectedRows = statementCompra.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating compra failed, no rows affected.");
                }

                try (ResultSet generatedKeys = statementCompra.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        compra.setIdCompra(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Creating compra failed, no ID obtained.");
                    }
                }
            }

            
            try (PreparedStatement statementCompraProducto = conn.prepareStatement(sqlCompraProducto)) {
                for (ModeloProducto producto : compra.getProductos()) {
                    statementCompraProducto.setInt(1, compra.getIdCompra());
                    statementCompraProducto.setInt(2, producto.getIdProducto());
                    statementCompraProducto.setInt(3, producto.getCantidad()); // Asume que Producto tiene un campo 'cantidad'

                    statementCompraProducto.executeUpdate();
                }
            }

            conn.commit(); 
            return true;
        } catch (SQLException e) {
            try {
                conn.rollback(); 
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true); 
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    // Método para listar las compras realizadas por un funcionario
    public List<CompraModelo> listarComprasPorFuncionario(int idFuncionario) {
        List<CompraModelo> compras = new ArrayList<>();
        Connection conn = conexionDB.conectar();
        String sql = "SELECT * FROM Compra WHERE id_funcionario = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, idFuncionario);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CompraModelo compra = new CompraModelo(
                        resultSet.getInt("id_compra"),
                        resultSet.getDate("fecha_hora"),
                        resultSet.getInt("id_funcionario"),
                        resultSet.getDouble("total")
                );
                
                compras.add(compra);
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
        return compras;
    }
}
