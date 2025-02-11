/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Exceptions.EmailInvalidoException;
import Models.Cliente;
import Models.DetalleVenta;
import Models.Empleados.Cajero;
import Models.Productos.Producto;
import Models.Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Javier Argoty
 */
public class ClienteDAO {

    private DataBaseConnector dbc = new DataBaseConnector();
    private static ClienteDAO instancia;
    
    private ClienteDAO() {
    }
    
    public static ClienteDAO getInstancia(){
        instancia = instancia == null? new ClienteDAO():instancia;
        return instancia;
    }
    
    
    public void agregarCliente(Cliente cliente) throws EmailInvalidoException {
        String sql = "INSERT INTO Clientes (nombre_completo,telefono,email)"
                + " VALUES (?,?,?)";
                try (Connection con = dbc.connect(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getNombreCompleto());
            pstmt.setString(2, cliente.getTelefono());
            pstmt.setString(3, cliente.getEmail());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new EmailInvalidoException("El email ya existe en otro cliente");
        }
    }

        public ArrayList<Cliente> getClientes() {
            ArrayList<Cliente> clientes = new ArrayList<>();
            String sql = "SELECT * FROM Clientes";

            try (Connection con = dbc.connect(); PreparedStatement pstmt = con.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id_cliente");
                    String nombre = rs.getString("nombre_completo");
                    String telefono = rs.getString("telefono");
                    String email = rs.getString("email");

                    clientes.add(new Cliente(id, nombre, telefono, email));
                }
            } catch (SQLException e) {
                System.out.println("FALLO AL OBTENER CLIENTES" + e.getMessage());
            }
            return clientes;
        }

    public void eliminarCliente(int idCliente) {
        String sql = "DELETE FROM Clientes WHERE id_cliente = ?";

        try (Connection con = dbc.connect(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, idCliente);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("FALLO AL ELIMINAR CLIENTE" + e.getMessage());
        }
    }

    public void editarCliente(Cliente cliente) throws EmailInvalidoException {
        String sql = "UPDATE Clientes SET nombre_completo = ?, telefono = ?, email = ? WHERE id_cliente = ?";

        try (Connection con = dbc.connect(); PreparedStatement pstmt = con.prepareStatement(sql);) {

            pstmt.setString(1, cliente.getNombreCompleto());
            pstmt.setString(2, cliente.getTelefono());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setInt(4, cliente.getIdCliente());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new EmailInvalidoException("El email ya existe en otro cliente");
        }
    }

    public Cliente buscarClienteId(int idCliente) {
        Cliente cliente = null;
        String sql = "SELECT * FROM Clientes WHERE id_cliente = ?";

        try (Connection con = dbc.connect(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, idCliente);
            ResultSet rs = pstmt.executeQuery();

            String nombre = rs.getString("nombre_completo");
            String telefono = rs.getString("telefono");
            String email = rs.getString("email");

            cliente = new Cliente(idCliente, nombre, telefono, email);
        } catch (SQLException e) {
            System.out.println("FALLO EN BUSCAR CLIENTE POR ID " + e.getMessage());
        }
        return cliente;
    }

    public ArrayList<Venta> getVentas(int id_cliente) {
        ArrayList<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM Ventas WHERE id_cliente=?";

        try (Connection con = dbc.connect(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id_cliente);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int idVenta = rs.getInt("id_venta");
                String fecha = rs.getString("fecha");
                double total = rs.getDouble("total");
                int idCajero = rs.getInt("id_cajero");

                Cajero cajero = obtenerCajeroPorId(idCajero);

                // Crea un objeto de Venta y añádelo a la lista
                Venta venta = new Venta(null, cajero, total, new ArrayList<>());
                venta.setIdVenta(idVenta);
                venta.setFecha(fecha);

                ventas.add(venta);
            }
        } catch (SQLException e) {
            System.out.println("FALLO AL OBTENER VENTAS: " + e.getMessage());
        }

        return ventas;
    }
    
    public Venta getVentaPorId(int id_venta) {
        Venta venta = null;
        String sql = "SELECT * FROM Ventas WHERE id_venta=?";

        try (Connection con = dbc.connect(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id_venta);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String fecha = rs.getString("fecha");
                double total = rs.getDouble("total");
                int idCajero = rs.getInt("id_cajero");
                int idCliente = rs.getInt("id_cliente");

                Cajero cajero = obtenerCajeroPorId(idCajero);
                Cliente cliente = buscarClienteId(idCliente);

                // Crea un objeto de Venta y añádelo a la lista
                venta = new Venta(cliente, cajero, total, new ArrayList<>());
                venta.setFecha(fecha);
            }
        } catch (SQLException e) {
            System.out.println("FALLO AL OBTENER VENTAS: " + e.getMessage());
        }

        return venta;
    }
    
    public ArrayList<DetalleVenta> getDetallesVenta(int id_venta) {
        ArrayList<DetalleVenta> detallesVenta = new ArrayList<>();
        String sql = "SELECT * FROM DetallesVentas WHERE id_venta=?";

        try (Connection con = dbc.connect(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id_venta);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int idDetalleVenta = rs.getInt("id_detalle");
                int codProducto = rs.getInt("codigo_producto");
                int cantidad = rs.getInt("cantidad");
               
                Producto producto = ProductoDAO.getInstancia().buscarProductoCodigo(codProducto);

                DetalleVenta detalle = new DetalleVenta( producto, cantidad);
                detalle.setIdDetalle(idDetalleVenta);

                detallesVenta.add(detalle);
            }
        } catch (SQLException e) {
            System.out.println("FALLO AL OBTENER DETALLES DE VENTA: " + e.getMessage());
        }

        return detallesVenta;
    }

    
    private Cajero obtenerCajeroPorId(int idCajero) {
        String sql = "SELECT * FROM Empleados WHERE id_empleado = ? AND tipo_empleado = 'Cajero'";

        try (Connection con = dbc.connect(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, idCajero);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Extraer los datos del ResultSet
                String nombreCompleto = rs.getString("nombre_completo");
                String correo = rs.getString("correo");
                double salario = rs.getDouble("salario");
                String turno = rs.getString("turno");

                // Crear y devolver un objeto Cajero
                return new Cajero(idCajero, nombreCompleto, correo, salario, turno);
            }
        } catch (SQLException e) {
            System.out.println("FALLO AL BUSCAR EL CAJERO: " + e.getMessage());
        }
        return null;
    }

}
