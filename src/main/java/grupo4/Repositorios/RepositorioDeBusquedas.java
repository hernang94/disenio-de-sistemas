package grupo4.Repositorios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mongodb.morphia.query.Query;


import grupo4.Acciones.FechaCantReporte;
import grupo4.HerramientasExternas.InstanciadorMorphia;

public class RepositorioDeBusquedas {
	private List<ResultadoDeBusqueda> listaBusquedas = new ArrayList<>();
	private Map<String, List<FechaCantReporte>> busquedasDeCadaTerminal = new HashMap<>();
	private static RepositorioDeBusquedas instancia = new RepositorioDeBusquedas();

	public static RepositorioDeBusquedas getInstancia() {
		return instancia;
	}

	public void reset() {
		listaBusquedas.clear();
		busquedasDeCadaTerminal.clear();
	}

	public void agregarBusqueda(ResultadoDeBusqueda newResult) {
		InstanciadorMorphia.getDb().save(newResult);
	}

	 public List<Integer> getlistaBusquedas(String unTerminal) {
		@SuppressWarnings("deprecation")
		Query<ResultadoDeBusqueda> queryListaBusquedas = InstanciadorMorphia.getDb().createQuery(ResultadoDeBusqueda.class).field("terminalDeLaBusqueda").equal(unTerminal).retrievedFields(true, "cantidadDeResultados");
		List<ResultadoDeBusqueda> resultadosObtenidos = queryListaBusquedas.asList();
		return resultadosObtenidos.stream().map(unResultado->unResultado.getCantidadDeResultados()).collect(Collectors.toList());
		}

	public FechaCantReporte cantidadPorFecha(LocalDate fecha) {
		return new FechaCantReporte(fecha, listaBusquedas.stream()
				.filter(busqueda -> busqueda.getFechaDeBusqueda().equals(fecha)).collect(Collectors.toList()).size());
	}

	public List<FechaCantReporte> getListaFechaCant(String terminal) {
		List<FechaCantReporte> listaADevolver = new ArrayList<>();
		List<ResultadoDeBusqueda> queryListaBusquedas = InstanciadorMorphia.getDb()
				.createQuery(ResultadoDeBusqueda.class).field("terminalDeLaBusqueda").equal(terminal).asList();
		Map<LocalDate,Integer>mapAuxiliar=new HashMap<>();
		mapAuxiliar=armarHash(queryListaBusquedas);
		mapAuxiliar.forEach((fecha,cantidad)->listaADevolver.add(new FechaCantReporte(fecha,cantidad)));
		return listaADevolver;
	}

	private Map<LocalDate, Integer> armarHash(List<ResultadoDeBusqueda> queryListaBusquedas) {
		Map<LocalDate,Integer>mapAuxiliar=new HashMap<>();
		queryListaBusquedas.stream().forEach(elemento->mapAuxiliar.compute(elemento.getFechaDeBusqueda(), (k,v)->(v==null)? elemento.getCantidadDeResultados():v+elemento.getCantidadDeResultados()));
		return mapAuxiliar;
	}

	public Map<String, Integer> reporteTotal() {
		Map<String, Integer> hashARetornar = new HashMap<>();
		List<ResultadoDeBusqueda> queryListaBusquedas = InstanciadorMorphia.getDb().createQuery(ResultadoDeBusqueda.class).asList();
		queryListaBusquedas.stream().forEach(resultado->hashARetornar.compute(resultado.getTerminalDeLaBusqueda(),(k,v)->(v==null)? resultado.getCantidadDeResultados():v+resultado.getCantidadDeResultados()));
		return hashARetornar;
	}

	public Integer cantidadTotalDeBusquedas(List<FechaCantReporte> lista) {
		return lista.stream().mapToInt(elemento -> elemento.getCantidad()).sum();
	}

}