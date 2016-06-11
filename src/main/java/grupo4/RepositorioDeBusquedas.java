package grupo4;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeBusquedas {
	private List <ResultadosDeBusquedas> listaBusquedas;
	
	public RepositorioDeBusquedas() {
		listaBusquedas = new ArrayList<>();
	}

	public void agregarBusqueda(ResultadosDeBusquedas newResult) {
		listaBusquedas.add(newResult);		
	}
	
}
