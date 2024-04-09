/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ModeloPrudctoBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import Vista.VistaProductos;

/**
 *
 * @author yagoa
 */
public class ControladorProducto {

    private ModeloPrudctoBD modelo;

    public ControladorProducto() {
        this.modelo = new ModeloPrudctoBD();
    }

    public ResultSet consultarProductos() {
        // Consultar productos utilizando el modelo
        return modelo.consultarProductos();
    }

    public DefaultTableModel obtenerProductos() {
        ResultSet rs = modelo.consultarProductos();
        DefaultTableModel modeloTabla = new DefaultTableModel();

        try {
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int id = rs.getInt("id");
                int cantidad = rs.getInt("cantidad");
                modeloTabla.addRow(new Object[]{nombre, precio, id, cantidad});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modeloTabla;
    }

    public void agregarProductoACarrito(int filaSeleccionadaIndex, VistaProductos vista) {
        Object[] filaSeleccionada = vista.obtenerFilaSeleccionada();
        if (filaSeleccionada != null) {
            String nombre = (String) filaSeleccionada[0];
            double precio = (double) filaSeleccionada[1];
            int id = (int) filaSeleccionada[2];
            int cantidad = (int) filaSeleccionada[3];

            if (cantidad <= 0) {
                vista.mostrarMensaje("El producto seleccionado está agotado.");
                return;
            }

            if (modelo.actualizarCantidadProducto(id, cantidad - 1)) {
                vista.mostrarMensaje("Se ha agregado '" + nombre + "' al carrito.");
                vista.llenarTablaProductos();
            } else {
                vista.mostrarMensaje("Error al actualizar la cantidad del producto en la base de datos.");
            }
        } else {
            vista.mostrarMensaje("Por favor seleccione un producto.");
        }
    }
}
