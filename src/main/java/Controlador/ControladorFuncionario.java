/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ConexionBDModelo;
import Modelo.FuncionarioModelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author armi8
 */
public class ControladorFuncionario {
   
    private ConexionBDModelo conexionDB;

    public ControladorFuncionario() {
        this.conexionDB = new ConexionBDModelo(); 
    }

    // Método para verificar el inicio de sesión
    public FuncionarioModelo iniciarSesion(String correo, String contraseña) {
        FuncionarioModelo funcionario = null;
        Connection conn = conexionDB.conectar();
        String sql = "SELECT * FROM Funcionario WHERE correo = ? AND contraseña = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, correo);
            statement.setString(2, contraseña);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                funcionario = new FuncionarioModelo(
                        resultSet.getInt("id_funcionario"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("correo"),
                        resultSet.getString("contraseña")
                );
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
        return funcionario;
    }
}
