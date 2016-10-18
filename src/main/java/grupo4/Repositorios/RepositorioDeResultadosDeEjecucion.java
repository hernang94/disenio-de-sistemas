package grupo4.Repositorios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDeResultadosDeEjecucion {
	private List<ResultadosDeEjecucion> listaDeResultados = new ArrayList<>();
	private static RepositorioDeResultadosDeEjecucion instancia = new RepositorioDeResultadosDeEjecucion();

	public static RepositorioDeResultadosDeEjecucion getInstancia() {
		return instancia;
	}
	
	public List<ResultadosDeEjecucion> getlistaDeResultados() {
		return listaDeResultados;
	}

	public void agregarResultado(ResultadosDeEjecucion resultado) {
		listaDeResultados.add(resultado);
	}

}
