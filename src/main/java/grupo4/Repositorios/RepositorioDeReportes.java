package grupo4.Repositorios;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeReportes {
	private List<Reporte> listaDeReportes=new ArrayList<>();
	private static RepositorioDeReportes instancia = new RepositorioDeReportes();

	public static RepositorioDeReportes getInstancia() {
		return instancia;
	}
	
	public void agregarReporte(Reporte unReporte){
		listaDeReportes.add(unReporte);
	}

	public List<Reporte> getListaDeReportes() {
		return listaDeReportes;
	}
	
	
}
