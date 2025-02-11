/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Dao.ClienteDAO;
import Exceptions.CamposVaciosException;
import Exceptions.EmailInvalidoException;
import Helpers.HelperFunctions;
import java.util.ArrayList;
import Models.Cliente;
import Models.DetalleVenta;
import Models.Venta;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Luis Sinisterra, Simon Cruz, Leonardo Argoty
 */
public class ClienteService implements IClienteService {

    private ClienteDAO clienteDAO;

    public ClienteService() {
        clienteDAO = ClienteDAO.getInstancia();
    }

    public ArrayList<Cliente> getClientes() {
        return clienteDAO.getClientes();
    }

    // Agrega cliente default
    public void agregarCliente(Cliente cliente) throws EmailInvalidoException, CamposVaciosException {
        // Valida campos de cliente
        if (isVacioCampo(cliente)) {
            throw new CamposVaciosException("FALTAN CAMPOS");
        }
        
        if (!HelperFunctions.esEmailValido(cliente.getEmail())) {
            throw new EmailInvalidoException("El email debe ser de un formato válido");
        }

        clienteDAO.agregarCliente(cliente);
    }

    // Elimina cliente
    public void eliminarCliente(int idCliente) {
        clienteDAO.eliminarCliente(idCliente);
    }

    // Edita el cliente, segun la busqueda del id de este mismo
    public void editarCliente(Cliente cliente) throws CamposVaciosException, EmailInvalidoException {
        // VALIDA LOS CAMPOS
        if (isVacioCampo(cliente)) {
            throw new CamposVaciosException("FALTAN CAMPOS");
        }
        
        if (!HelperFunctions.esEmailValido(cliente.getEmail())) {
            throw new EmailInvalidoException("El email debe ser de un formato válido");
        }

        clienteDAO.editarCliente(cliente);
    }

    // Busca Cliente Por Id
    public Cliente buscarClientePorId(int idCliente) {
        return clienteDAO.buscarClienteId(idCliente);
    }

    public boolean isVacioCampo(Cliente cliente) {
        return cliente.getEmail().trim().isEmpty()
                || cliente.getNombreCompleto().trim().isEmpty() || cliente.getTelefono().trim().isEmpty();
    }
    
    public int buscarIdCliente(String texto){
        String regex = "^(\\d+)\\.\\s.*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);
        
        if (matcher.find()) {
            int idCliente = Integer.parseInt(matcher.group(1));
            return idCliente;
        }
        return -1;
    }
    
    // Obtener compras que ha hecho ese cliente
    public ArrayList<Venta> getVentas(int id_cliente) {
        return clienteDAO.getVentas(id_cliente);
    }
    public ArrayList<DetalleVenta> getDetallesVenta(int idVenta) {
        return clienteDAO.getDetallesVenta(idVenta);
    }
    public Venta getVentaPorId(int id_venta) {
        return clienteDAO.getVentaPorId(id_venta);
    }
}
