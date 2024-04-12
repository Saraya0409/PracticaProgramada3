/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author armi8
 */
public class CompraModelo {
    private int idCompra;
    private Date fechaHora;
    private int idFuncionario; 
    private double total;
    private List<ModeloProducto> productos; 

    public CompraModelo(int idCompra, Date fechaHora, int idFuncionario, double total) {
        this.idCompra = idCompra;
        this.fechaHora = fechaHora;
        this.idFuncionario = idFuncionario;
        this.total = total;
    }

    
    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<ModeloProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<ModeloProducto> productos) {
        this.productos = productos;
    }
}
