package grupo4.Repositorios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import grupo4.Acciones.FechaCantReporte;

public class RepositorioDeBusquedas {
	private List<ResultadosDeBusquedas> listaBusquedas;
	private Set<LocalDate> listafechas;

	public RepositorioDeBusquedas() {
		listaBusquedas = new ArrayList<>();
		listafechas = new HashSet<>();
	}

	public void agregarBusqueda(ResultadosDeBusquedas newResult) {
		listaBusquedas.add(newResult);
		listafechas.add(newResult.getFechaDeBusqueda().toLocalDate());
	}

	public List<Integer> getlistaBusquedas() {
		List<Integer> lista=new ArrayList<>();
		listaBusquedas.forEach(busqueda->lista.add(busqueda.getCantidadDeResultados()));
		return lista;
	}

	public Set<LocalDate> getlistaFechas() {
		return listafechas;
	}
	
	public FechaCantReporte cantidadPorFecha(LocalDate fecha){
		return new FechaCantReporte(fecha,listaBusquedas.stream().filter(busqueda->busqueda.getFechaDeBusqueda().equals(fecha)).collect(Collectors.toList()).size());
	}
	
	public List<FechaCantReporte> getListaFechaCant(){
		List<FechaCantReporte> lista=new ArrayList<>();
		listafechas.stream().forEach(fecha->lista.add(cantidadPorFecha(fecha)));
		return lista;
	}
	
}