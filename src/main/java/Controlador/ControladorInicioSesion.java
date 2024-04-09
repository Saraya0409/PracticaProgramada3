/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ModeloInicioSesion;
import javax.swing.JOptionPane;
import Vista.VistaInicioSesion;

/**
 *
 * @author yagoa
 */
public class ControladorInicioSesion {

    private ModeloInicioSesion modelo;
    private VistaInicioSesion vista;

    public ControladorInicioSesion() {
        this.modelo = new ModeloInicioSesion();
    }

    public boolean iniciarSesion(String usuario, String contraseña) {
        
        boolean informacionValida = modelo.iniciarSesion(usuario, contraseña);
        
        if (informacionValida) {
            return true;
        }
        else{
            vista.mostrarMensajeError("Usuario o contraseña incorrectos, por favor ingrese sus datos nuevamente");
            return false;
        }
    }
}
