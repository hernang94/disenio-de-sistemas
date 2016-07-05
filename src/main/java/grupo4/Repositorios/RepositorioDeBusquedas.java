package grupo4.Repositorios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import grupo4.Acciones.FechaCantReporte;

public class RepositorioDeBusquedas {
	private List<ResultadosDeBusquedas> listaBusquedas = new ArrayList<>();;
	private Map<String, List<FechaCantReporte>> busquedasDeCadaTerminal = new HashMap<>();
	private static RepositorioDeBusquedas instancia = new RepositorioDeBusquedas();

	private RepositorioDeBusquedas() {
	}

	public static RepositorioDeBusquedas getInstancia() {
		return instancia;
	}

	public void reset() {
		listaBusquedas.clear();
		busquedasDeCadaTerminal.clear();
	}

	public void agregarBusqueda(ResultadosDeBusquedas newResult) {
		listaBusquedas.add(newResult);
		actualizarHashTerminales(newResult);
	}

	private void actualizarHashTerminales(ResultadosDeBusquedas newResult) {
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

	public List<Integer> getlistaBusquedas() {
		List<Integer> lista = new ArrayList<>();
		listaBusquedas.forEach(busqueda -> lista.add(busqueda.getCantidadDeResultados()));
		return lista;
	}

	public FechaCantReporte cantidadPorFecha(LocalDate fecha) {
		return new FechaCantReporte(fecha, listaBusquedas.stream()
				.filter(busqueda -> busqueda.getFechaDeBusqueda().equals(fecha)).collect(Collectors.toList()).size());
	}

	public List<FechaCantReporte> getListaFechaCant(String terminal) {
		return busquedasDeCadaTerminal.get(terminal);
	}

	public Map<String,Integer> reporteTotal() {
		Map<String,Integer> hashARetornar=new HashMap<>();
		busquedasDeCadaTerminal.forEach((terminal,lista)->hashARetornar.put(terminal, cantidadTotalDeBusquedas(lista)));
		return hashARetornar;
	}
	
	public Integer cantidadTotalDeBusquedas(List<FechaCantReporte> lista){
		return lista.stream().mapToInt(elemento->elemento.getCantidad()).sum();
	}

}