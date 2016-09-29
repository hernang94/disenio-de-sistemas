package grupo4.Repositorios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mongodb.morphia.query.Query;

import com.mongodb.client.model.CreateCollectionOptions;

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
		InstanciadorMorphia.getDb().save(newResult,ResultadoDeBusqueda.class);
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getlistaBusquedas(String unTerminal) {
		Query<Integer> queryListaBusquedas = InstanciadorMorphia.getDb().createQuery(Integer.class).field("terminalDeLaBusqueda").equal(unTerminal).retrievedFields(true, "cantidadDeResultados");
				//.filter("terminalDeLaBusqueda", unTerminal);
		List<Integer> resultadosObtenidos = queryListaBusquedas.asList();
		return resultadosObtenidos;
		//return resultadosObtenidos.stream().map(unResultado->unResultado.getCantidadDeResultados()).collect(Collectors.toList());
		/*return (List<Integer>) entityManager()
				.createQuery(
						"SELECT cantidadDeResultados FROM ResultadoDeBusqueda WHERE terminalDeLaBusqueda=:nombreTerminal")
				.setParameter("nombreTerminal", unTerminal).getResultList();*/
	}

	public FechaCantReporte cantidadPorFecha(LocalDate fecha) {
		return new FechaCantReporte(fecha, listaBusquedas.stream()
				.filter(busqueda -> busqueda.getFechaDeBusqueda().equals(fecha)).collect(Collectors.toList()).size());
	}

	public List<FechaCantReporte> getListaFechaCant(String terminal) {
		List<FechaCantReporte> listaADevolver = new ArrayList<>();
		@SuppressWarnings("unchecked")
		/*List<Object[]> listaDeObjetos = (List<Object[]>) entityManager()
				.createQuery(
						"select fechaDeBusqueda, sum(cantidadDeResultados) from ResultadoDeBusqueda where terminalDeLaBusqueda=:terminal group by fechaDeBusqueda")
				.setParameter("terminal", terminal).getResultList();*/
		listaDeObjetos.stream().forEach(elemento -> listaADevolver
				.add(new FechaCantReporte((LocalDate) elemento[0], ((Long) elemento[1]).intValue())));
		return listaADevolver;
	}

	public Map<String, Integer> reporteTotal() {
		Map<String, Integer> hashARetornar = new HashMap<>();
		@SuppressWarnings("unchecked")
		List<Object[]> listaObjetos = (List<Object[]>) entityManager()
				.createQuery(
						"select distinct terminalDeLaBusqueda, sum(cantidadDeResultados) from ResultadoDeBusqueda group by terminalDeLaBusqueda")
				.getResultList();
		listaObjetos.stream()
				.forEach(elemento -> hashARetornar.put((String) elemento[0], ((Long) elemento[1]).intValue()));
		return hashARetornar;
	}

	public Integer cantidadTotalDeBusquedas(List<FechaCantReporte> lista) {
		return lista.stream().mapToInt(elemento -> elemento.getCantidad()).sum();
	}

}