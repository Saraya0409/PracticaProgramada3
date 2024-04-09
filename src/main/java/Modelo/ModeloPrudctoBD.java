/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;

/**
 *
 * @author yagoa
 */
public class ModeloPrudctoBD {

    private ConexionBDModelo conexion;

    public ModeloPrudctoBD() {
        
    }

    public ResultSet consultarProductos() {
        try {
            conexion.establecerConexion();
            PreparedStatement statement = conexion.obtenerConexion().prepareStatement("SELECT * FROM productos");
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet consultarProductoPorId(int id) {
        try {
            conexion.establecerConexion();
            PreparedStatement statement = conexion.obtenerConexion().prepareStatement("SELECT * FROM productos WHERE id = ?");
            statement.setInt(1, id);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertarProducto(String nombre, double precio, int cantidad) {
        try {
            conexion.establecerConexion();
            PreparedStatement statement = conexion.obtenerConexion().prepareStatement("INSERT INTO productos(nombre, precio, cantidad) VALUES (?, ?, ?)");
            statement.setString(1, nombre);
            statement.setDouble(2, precio);
            statement.setInt(3, cantidad);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conexion.cerrarRecursos();
        }
    }

    public boolean actualizarCantidadProducto(int id, int nuevaCantidad) {
        try {
            conexion.establecerConexion();
            PreparedStatement statement = conexion.obtenerConexion().prepareStatement("UPDATE productos SET cantidad = ? WHERE id = ?");
            statement.setInt(1, nuevaCantidad);
            statement.setInt(2, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conexion.cerrarRecursos();
        }
    }
}

