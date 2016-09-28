package grupo4.Repositorios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import grupo4.Acciones.FechaCantReporte;

public class RepositorioDeBusquedas implements WithGlobalEntityManager {
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
		entityManager().persist(newResult);
		actualizarHashTerminales(newResult);
	}

	private void actualizarHashTerminales(ResultadoDeBusqueda newResult) {
		if (busquedasDeCadaTerminal.containsKey(newResult.getTerminalDeLaBusqueda())) {
			FechaCantReporte fechaAux = busquedasDeCadaTerminal.get(newResult.getTerminalDeLaBusqueda()).stream()
					.filter(reporte -> reporte.getFecha().equals(newResult.getFechaDeBusqueda())).findFirst().get();
			if (fechaAux == null) {
				FechaCantReporte auxAAgregar = new FechaCantReporte(newResult.getFechaDeBusqueda(),
						newResult.getCantidadDeResultados());
				busquedasDeCadaTerminal.get(newResult.getTerminalDeLaBusqueda()).add(auxAAgregar);
			} else {
				fechaAux.aumentarCantidad(newResult.getCantidadDeResultados());
			}
		} else {
			List<FechaCantReporte> listaAAgregar = new ArrayList<>();
			FechaCantReporte auxAAgregar = new FechaCantReporte(newResult.getFechaDeBusqueda(),
					newResult.getCantidadDeResultados());
			listaAAgregar.add(auxAAgregar);
			busquedasDeCadaTerminal.put(newResult.getTerminalDeLaBusqueda(), listaAAgregar);
		}

	}

	// hago que el método devuelva List<Integer> o solo List para que matchee
	// con lo que devuelve la Query del EM?
	@SuppressWarnings("unchecked")
	public List<Integer> getlistaBusquedas(String unTerminal) {
		/*
		 * List<Integer> lista = new ArrayList<>(); List<ResultadoDeBusqueda>
		 * listaFiltrada = listaBusquedas.stream() .filter(busqueda ->
		 * busqueda.esDeTerminal(unTerminal)).collect(Collectors.toList());
		 * listaFiltrada.forEach(busqueda ->
		 * lista.add(busqueda.getCantidadDeResultados())); return lista;
		 */
		return (List<Integer>) entityManager()
				.createQuery(
						"SELECT cantidadDeResultados FROM ResultadoDeBusqueda WHERE terminalDeLaBusqueda=:nombreTerminal")
				.setParameter("nombreTerminal", unTerminal).getResultList();
	}

	public FechaCantReporte cantidadPorFecha(LocalDate fecha) {
		return new FechaCantReporte(fecha, listaBusquedas.stream()
				.filter(busqueda -> busqueda.getFechaDeBusqueda().equals(fecha)).collect(Collectors.toList()).size());
	}

	public List<FechaCantReporte> getListaFechaCant(String terminal) {
		List<FechaCantReporte> listaADevolver = new ArrayList<>();
		@SuppressWarnings("unchecked")
		List<Object[]> listaDeObjetos = (List<Object[]>) entityManager()
				.createQuery(
						"select distinct fechaDeBusqueda, sum(cantidadDeResultados) from ResultadoDeBusqueda where terminalDeLaBusqueda=:terminal group by fechaDeBusqueda")
				.setParameter("terminal", terminal).getResultList();
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