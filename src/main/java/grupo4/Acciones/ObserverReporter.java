package grupo4.Acciones;

import java.time.LocalDateTime;
import grupo4.Repositorios.RepositorioDeBusquedas;

public class ObserverReporter implements Observers {

	private RepositorioDeBusquedas almacen;

	public ObserverReporter(RepositorioDeBusquedas almacen) {
		this.almacen = almacen;
	}

	public void evaluarNotificacion(long diferencia) {

	}

	public void agregarBusqueda(String terminal,String criterio, long diferencia, LocalDateTime tiempoInicio, int size) {

	}

	public void reporteTotalPorFecha(String terminal) {
		almacen.getListaFechaCant(terminal);
	}

	public void reporteParcial() {
		almacen.getlistaBusquedas();
	}

	public void reporteTotal() {
		almacen.reporteTotal();
	}

}
