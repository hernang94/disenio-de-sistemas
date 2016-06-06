package grupo4;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DecoratorBusquedas extends Decorator {
	
	List<ResultadosDeBusquedas> listaDeResultados;
	
	public DecoratorBusquedas(Busqueda decorado) {
		super(decorado);
		this.listaDeResultados = new ArrayList<>();
	}

	public List<Poi> busquedaLibre (String criterio){
		List<Poi> listaAux=new ArrayList<>();
		long diferencia;
		LocalDateTime tiempoInicio = LocalDateTime.now();
		listaAux=decorado.busquedaLibre(criterio);
		LocalDateTime tiempoFin = LocalDateTime.now();
		diferencia=calcularDiferencia(tiempoInicio, tiempoFin);
		cargarResultados(diferencia,criterio,tiempoInicio,listaAux.size());
		return listaAux;
	}
	
	private void cargarResultados(long tiempoDeBusqueda, String fraseBuscada, LocalDateTime fechaDeBusqueda,
			int cantidadDeResultados) {
		ResultadosDeBusquedas resultado = new ResultadosDeBusquedas(tiempoDeBusqueda, fraseBuscada, fechaDeBusqueda,
				cantidadDeResultados);
		listaDeResultados.add(resultado);
	}
	
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

	public int cantidadTotalDeResultados(){
		return listaDeResultados.stream().mapToInt(resultado->resultado.getCantidadDeResultados()).sum();
	}

	public void ReportarBusquedasPorTipo(PrintWriter writer) {
		writer.println("Cantidad Busquedas Parciales");
		listaDeResultados.stream().forEach(resultado->reportarCantidad(resultado,writer));
	}

	private void reportarCantidad(ResultadosDeBusquedas resultado,PrintWriter writer) {
		writer.println(resultado.getCantidadDeResultados());
	}
	
	private PrintWriter crearArchivo(){
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("ReporteBusquedas.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return writer;
	}
	
}
