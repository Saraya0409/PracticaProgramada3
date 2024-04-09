/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author yagoa
 */
public class FuncionarioModelo {
   private String nombreUsuario;
   private String Contraseña;

    public FuncionarioModelo(String nombreUsuario, String Contraseña) {
        this.nombreUsuario = nombreUsuario;
        this.Contraseña = Contraseña;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }
   
   
   
}
