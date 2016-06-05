package grupo4;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.geodds.Point;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class RepositorioDePois {
	private long tiempoEstipulado;
	private String nombre;
	private List<Poi> listaDePois = new ArrayList<>();
	private List<Adaptadores> listaAdaptadores = new ArrayList<>();
	private List<ResultadosDeBusquedas> listaDeResultados = new ArrayList<>();

	public RepositorioDePois(String unNombre,long tiemp) {
		this.nombre = unNombre;
		this.tiempoEstipulado=tiemp;
	}

	public String getNombre() {
		return nombre;
	}

	public void agregarAdaptador(Adaptadores adaptador) {
		listaAdaptadores.add(adaptador);
	}

	public void quitarAdaptador(Adaptadores adaptador) {
		listaAdaptadores.remove(adaptador);
	}

	public void agregarPoi(Poi unPoi) {
		if (!repositorioContienePoi(unPoi.getNombre())) {
			listaDePois.add(unPoi);
		}
	}

	public void bajaPoi(Poi unPoi) {
		if (repositorioContienePoi(unPoi.getNombre())) {
			listaDePois.remove(unPoi);
		}
	}

	public void modificarPoi(Poi unPoi) {
		if (repositorioContienePoi(unPoi.getNombre())) {
			Poi poiAux = listaDePois.stream().filter(poi -> poi.getNombre().equalsIgnoreCase(unPoi.getNombre()))
					.findFirst().get();
			bajaPoi(poiAux);
			agregarPoi(unPoi);
		}
	}

	private boolean repositorioContienePoi(String nombre) {
		return listaDePois.stream().anyMatch(unPoi -> unPoi.getNombre().equalsIgnoreCase(nombre));
	}

	public List<Poi> busquedaLibre(String criterio) {
		LocalDateTime tiempoinicio = LocalDateTime.now();
		List<Poi> listaAux = new ArrayList<>();
		listaAux = filtrarPorCriterio(criterio);
		listaDePois.addAll(listaAux);
		LocalDateTime tiempofin = LocalDateTime.now();
		long diferencia = calcularDiferenciaYNotificar(tiempoinicio, tiempofin);
		cargarResultados(diferencia, criterio, tiempoinicio, listaAux.size());
		return listaAux;
	}

	private void cargarResultados(long tiempoDeBusqueda, String fraseBuscada, LocalDateTime fechaDeBusqueda,
			int cantidadDeResultados) {
		ResultadosDeBusquedas resultado = new ResultadosDeBusquedas(tiempoDeBusqueda, fraseBuscada, fechaDeBusqueda,
				cantidadDeResultados);
		listaDeResultados.add(resultado);

	}

	public long calcularDiferenciaYNotificar(LocalDateTime tiempoinicio, LocalDateTime tiempofin) {
		long diferencia = ChronoUnit.SECONDS.between(tiempoinicio, tiempofin);
		if (diferencia > tiempoEstipulado) {
			notificarAlAdministrador();
		}
		return diferencia;
	}

	public String notificarAlAdministrador() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		System.out.print("Mail enviado al adminisitrador");
		return outContent.toString();
	}

	public boolean consultaDisponibilidad(LocalDateTime fecha, String criterio) {
		Poi poiAux;
		poiAux = obtenerSegunCriterio(criterio);
		return poiAux.estaDisponible(fecha);
	}

	public boolean consultaDisponibilidad(LocalDateTime fecha, Servicio servicio) {
		Poi poiAux = obtenerSegunCriterio(servicio.getNombre());
		return poiAux.estaDisponible(fecha, servicio);
	}

	public boolean consultaDisponibilidad(LocalDateTime fecha) {
		return encontrarCGPS().stream().anyMatch(unCGP -> unCGP.estaDisponible(fecha));
	}

	public List<Poi> encontrarCGPS() {// Consideramos que
		// todos los CGP por defecto tiene un formato de nombre de tipo:
		// "CGP(Nro de CGP)"
		return listaDePois.stream().filter(unPoi -> unPoi.getNombre().contains("CGP")).collect(Collectors.toList());
	}

	public List<Poi> filtrarPorCriterio(String criterio) {
		List<Poi> listaFiltrada = listaDePois.stream().filter(unPoi -> unPoi.encuentraNombre(criterio))
				.collect(Collectors.toList());
		if (listaFiltrada.isEmpty()) {
			listaAdaptadores.stream()
					.forEach(unComponente -> listaFiltrada.addAll((unComponente.buscarPois(criterio))));
		}
		return listaFiltrada;
	}

	public boolean consultaCercania(String criterio, Point ubicacionSolicitada) {
		Poi poi_aux;
		poi_aux = obtenerSegunCriterio(criterio);
		return poi_aux.estaCerca(ubicacionSolicitada);
	}

	public Poi obtenerSegunCriterio(String criterio) {
		return listaDePois.stream().filter(unPoi -> unPoi.encuentraNombre(criterio)).findFirst().get();
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