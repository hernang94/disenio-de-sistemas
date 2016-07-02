package grupo4.Acciones;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.ResultadosDeBusquedas;

public class ObserverReporter implements Observers {

	private RepositorioDeBusquedas almacen;

	public ObserverReporter(RepositorioDeBusquedas almacen) {
		this.almacen = almacen;
	}
	
	public void evaluarNotificacion(long diferencia){
		
	}
	
	public void agregarBusqueda(String criterio, long diferencia, LocalDateTime tiempoInicio, int size) {

	}

	public void reporteTotalPorFecha() {
		almacen.getListaFechaCant();
	}

	public void reporteParcial() {
		almacen.getlistaBusquedas();
	}

	public void reporteTotal() {
		
	}

}
