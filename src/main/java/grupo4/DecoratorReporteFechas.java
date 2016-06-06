package grupo4;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DecoratorReporteFechas extends Decorator{
	
	private List<ResultadosDeBusquedas> listaDeResultados = new ArrayList<>();
	
	public List<Poi> busquedaLibre(String criterio){
		LocalDateTime tiempoinicio = LocalDateTime.now();
		decorado.busquedaLibre(criterio);
		LocalDateTime tiempofin = LocalDateTime.now();
	}
	public long calcularDiferencia(LocalDateTime tiempoinicio, LocalDateTime tiempofin);
	
	public void ReportarBusquedas(){
	PrintWriter writer=crearArchivo();
	writer.println("Fecha\t\tCantidad Búsquedas");
	listaDeResultados.stream().forEach(resultado->imprimirResultado(resultado,writer));
	writer.close();
	}

	private void imprimirResultado(ResultadosDeBusquedas resultado,PrintWriter writer) {
		writer.println(""+fechaFormateada(resultado.getFechaDeBusqueda())+"\t\t"+resultado.getCantidadDeResultados());
	}
	private String fechaFormateada(LocalDateTime fecha){
		return fecha.getDayOfMonth()+"/"+fecha.getMonthValue()+"/"+fecha.getYear();
	}
	
}
