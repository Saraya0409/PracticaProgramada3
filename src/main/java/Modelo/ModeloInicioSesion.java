/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yagoa
 */
public class ModeloInicioSesion {

    private ConexionBDModelo conexion;

    public ModeloInicioSesion() {
        this.conexion = new ConexionBDModelo();
    }

    public boolean iniciarSesion(String usuario, String contraseña) {
        try {
            conexion.establecerConexion();
            PreparedStatement statement = conexion.obtenerConexion().prepareStatement("SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?");
            statement.setString(1, usuario);
            statement.setString(2, contraseña);
            ResultSet resultado = statement.executeQuery();
            return resultado.next(); 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conexion.cerrarRecursos();
        }
    }
}

