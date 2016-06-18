package grupo4.Acciones;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.ResultadosDeBusquedas;

public class ObserverReporter implements Observers{
	RepositorioDeBusquedas almacen;
	public ObserverReporter(RepositorioDeBusquedas almacen, PrintWriter writer) {
		this.almacen = almacen;
	}

	public void notificar(PrintWriter writer){
		
	}

	public void agregarBusqueda(long diferencia, String criterio, LocalDateTime tiempoInicio, int size){
		
	}

	public void reporteTotalPorFecha(PrintWriter writer){
		writer.println("Fecha\t\tCantidad Total");
		List<ResultadosDeBusquedas>listaResulAux=almacen.getlistaBusquedas();
		Set<LocalDate>listaFechasAux=almacen.getlistaFechas();
		listaFechasAux.stream().forEach(fecha->imprimirReporteFecha(fecha,listaResulAux,writer));
	}

	private void imprimirReporteFecha(LocalDate fecha,List<ResultadosDeBusquedas>listaResulAux,PrintWriter writer) {
		List<ResultadosDeBusquedas>auxiliar=listaResulAux.stream().filter(resultado->resultado.getFechaDeBusqueda().toLocalDate().equals(fecha)).collect(Collectors.toList());
		int cantidadTotal=auxiliar.stream().mapToInt(resultado->resultado.getCantidadDeResultados()).sum();
		writer.println(fecha.toString()+"     \t\t"+cantidadTotal);
	}
	public void reporteParcial(PrintWriter writer){
		writer.println("Cantidad Resultados Parciales");
		List<ResultadosDeBusquedas>listaResulAux=almacen.getlistaBusquedas();
		listaResulAux.stream().forEach(resultado->writer.println(resultado.getCantidadDeResultados()));
	}
	public void reporteTotal(PrintWriter writer){
		List<ResultadosDeBusquedas>listaResulAux=almacen.getlistaBusquedas();
		int cantidadTotal=listaResulAux.stream().mapToInt(resultado->resultado.getCantidadDeResultados()).sum();
		writer.print(cantidadTotal);
	}

}
