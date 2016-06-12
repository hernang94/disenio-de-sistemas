package grupo4;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Reporter implements Observers{
	RepositorioDeBusquedas almacen;
	
	public Reporter(RepositorioDeBusquedas almacen) {
		this.almacen = almacen;
	}

	public void notificar(){
		
	}

	public void agregarBusqueda(long diferencia, String criterio, LocalDateTime tiempoInicio, int size){
		
	}

	public void reporteTotalPorFecha(){
		System.out.println("Fecha\t\tCantidad Total");
		List<ResultadosDeBusquedas>listaResulAux=almacen.getlistaBusquedas();
		Set<LocalDate>listaFechasAux=almacen.getlistaFechas();
		listaFechasAux.stream().forEach(fecha->imprimirReporteFecha(fecha,listaResulAux));
	}

	private void imprimirReporteFecha(LocalDate fecha,List<ResultadosDeBusquedas>listaResulAux) {
		List<ResultadosDeBusquedas>auxiliar=listaResulAux.stream().filter(resultado->resultado.getFechaDeBusqueda().toLocalDate().equals(fecha)).collect(Collectors.toList());
		int cantidadTotal=auxiliar.stream().mapToInt(resultado->resultado.getCantidadDeResultados()).sum();
		System.out.println(fecha.toString()+"     \t\t"+cantidadTotal);
	}
	public void reporteParcial(){
		System.out.println("Cantidad Resultados Parciales");
		List<ResultadosDeBusquedas>listaResulAux=almacen.getlistaBusquedas();
		listaResulAux.stream().forEach(resultado->System.out.println(resultado.getCantidadDeResultados()));
	}
	public void reporteTotal(){
		List<ResultadosDeBusquedas>listaResulAux=almacen.getlistaBusquedas();
		int cantidadTotal=listaResulAux.stream().mapToInt(resultado->resultado.getCantidadDeResultados()).sum();
		System.out.print(cantidadTotal);
	}

}
