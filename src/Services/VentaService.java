package Services;

import Dao.VentaDAO;
import Exceptions.ListaVaciaException;
import Models.DetalleVenta;
import Models.Venta;

import java.time.LocalDate;
import java.util.ArrayList;

public class VentaService {
    VentaDAO ventaDAO;

    public VentaService() {
        this.ventaDAO = VentaDAO.getInstancia();
    }

    public void agregarVenta(Venta venta, ArrayList<DetalleVenta> detalles) {
        ventaDAO.agregarVenta(venta, detalles);
    }

    public ArrayList<Venta> getVentas(){
        return this.ventaDAO.getVentas();
    }

    public ArrayList<Venta> filtrarVentas(String filtro) throws ListaVaciaException {
        ArrayList<Venta> ventas = this.getVentas();
        ArrayList<Venta> ventasFiltradas = new ArrayList<>();
        LocalDate fechaActual = LocalDate.now();

        switch (filtro) {
            case "Dia":
                LocalDate fechaFiltroDia = fechaActual.minusDays(2);
                for (Venta venta : ventas) {
                    LocalDate fechaVenta = LocalDate.parse(venta.getFecha());
                    if (fechaVenta.isAfter(fechaFiltroDia) && fechaVenta.isBefore(fechaActual)) {
                        ventasFiltradas.add(venta);
                    }
                }
                break;

            case "Semana":
                LocalDate fechaFiltroSemana = fechaActual.minusWeeks(1);
                for (Venta venta : ventas) {
                    LocalDate fechaVenta = LocalDate.parse(venta.getFecha());
                    if (fechaVenta.isAfter(fechaFiltroSemana) && fechaVenta.isBefore(fechaActual)) {
                        ventasFiltradas.add(venta);
                    }
                }
                break;

            case "Mes":
                LocalDate fechaFiltroMes = fechaActual.minusMonths(1);
                for (Venta venta : ventas) {
                    LocalDate fechaVenta = LocalDate.parse(venta.getFecha());
                    if (fechaVenta.isAfter(fechaFiltroMes) && fechaVenta.isBefore(fechaActual)) {
                        ventasFiltradas.add(venta);
                    }
                }
                break;
        }

        if(ventasFiltradas.isEmpty()){
            throw new ListaVaciaException("No se encontro ventas hechas en esa fecha");
        }

        return ventasFiltradas;
    }

}