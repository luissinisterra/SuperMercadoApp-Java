/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Productos.Factory;

import Models.Productos.ProductoPerecedero;

/**
 *
 * @author Simón David Cruz S
 */
public class FarmaceuticosPerecedero extends ProductoPerecedero implements Farmaceuticos{
    
    public FarmaceuticosPerecedero(int codigoProduto, String nombreProducto, double precio, int stock, String fechaCaducidad, int id_proveedor) {
        super(codigoProduto, nombreProducto, precio, stock, fechaCaducidad, id_proveedor);
    }
    
}