package grupo4.Acciones;

import java.time.LocalDateTime;

import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.ResultadosDeBusquedas;

public class ObserverAlmacenador implements Observers {
	private RepositorioDeBusquedas almacen;

	public ObserverAlmacenador(RepositorioDeBusquedas almacen) {
		this.almacen = almacen;
	}

	public void evaluarNotificacion(long diferencia) {

	}

	public void agregarBusqueda(String criterio, long diferencia, LocalDateTime tiempoInicio, int size) {
		ResultadosDeBusquedas newResult = new ResultadosDeBusquedas(diferencia, criterio, tiempoInicio, size);
		almacen.agregarBusqueda(newResult);
	}

	public void reporteTotalPorFecha() {

	}

	public void reporteParcial() {

	}

	public void reporteTotal() {

	}
}
