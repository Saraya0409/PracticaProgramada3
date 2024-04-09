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

public class ConexionBDModelo {
    private Connection conexion = null;
    private PreparedStatement consulta = null;
    private ResultSet resultado = null;
    private final String url = "";
    private final String usuario = "";
    private final String contraseña = "";

    public void establecerConexion() {
        try {
            conexion = DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void prepararConsulta(String consultaSQL) {
        try {
            consulta = conexion.prepareStatement(consultaSQL);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public ResultSet ejecutarConsulta() {
        try {
            return consulta.executeQuery();
        } catch (SQLException error) {
            error.printStackTrace();
            return null;
        }
    }

    public PreparedStatement obtenerConsulta() {
        return consulta;
    }

    public Connection obtenerConexion() {
        return conexion;
    }

    public void cerrarRecursos() {
        if (resultado != null) {
            try {
                resultado.close();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        }
        if (consulta != null) {
            try {
                consulta.close();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        }
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        }
    }
}    

