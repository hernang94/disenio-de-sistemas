package grupo4.Repositorios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import grupo4.Acciones.FechaCantReporte;

public class RepositorioDeBusquedas {
	//Aca directamente la lista y el map vuelan, porque si yo tengo la tabla de Resultados de Busqueda,
	//la traigo completa y en caso de querer los de un terminal en especifico hago una query y traigo
	//los de ese usuario/terminal y los devuelvo para el reporte
	//No se si hay que hacer desaparecer el objeto FechaCantReporte y que devuela la busqueda completa
	//y uso de ese objeto lo que necesito para hacer el reporte
	private List<ResultadoDeBusqueda> listaBusquedas = new ArrayList<>();;
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
		listaBusquedas.add(newResult);
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

	public List<Integer> getlistaBusquedas(String unTerminal) {
		List<Integer> lista = new ArrayList<>();
		List<ResultadoDeBusqueda> listaFiltrada = listaBusquedas.stream()
				.filter(busqueda -> busqueda.esDeTerminal(unTerminal)).collect(Collectors.toList());
		listaFiltrada.forEach(busqueda -> lista.add(busqueda.getCantidadDeResultados()));
		return lista;
	}

	public FechaCantReporte cantidadPorFecha(LocalDate fecha) {
		return new FechaCantReporte(fecha, listaBusquedas.stream()
				.filter(busqueda -> busqueda.getFechaDeBusqueda().equals(fecha)).collect(Collectors.toList()).size());
	}

	public List<FechaCantReporte> getListaFechaCant(String terminal) {
		return busquedasDeCadaTerminal.get(terminal);
	}

	public Map<String, Integer> reporteTotal() {
		Map<String, Integer> hashARetornar = new HashMap<>();
		busquedasDeCadaTerminal
				.forEach((terminal, lista) -> hashARetornar.put(terminal, cantidadTotalDeBusquedas(lista)));
		return hashARetornar;
	}

	public Integer cantidadTotalDeBusquedas(List<FechaCantReporte> lista) {
		return lista.stream().mapToInt(elemento -> elemento.getCantidad()).sum();
	}

}