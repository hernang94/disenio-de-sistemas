package grupo4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepositorioDeBusquedas {
	private List <ResultadosDeBusquedas> listaBusquedas;
	private Set<LocalDate>listafechas;
	
	public RepositorioDeBusquedas() {
		listaBusquedas = new ArrayList<>();
		listafechas= new HashSet<>();
	}

	public void agregarBusqueda(ResultadosDeBusquedas newResult) {
		listaBusquedas.add(newResult);	
		listafechas.add(newResult.getFechaDeBusqueda().toLocalDate());
	}
	public List <ResultadosDeBusquedas> getlistaBusquedas(){
		return listaBusquedas;
	}
	public Set<LocalDate> getlistaFechas(){
		return listafechas;
	}
	
}
