package grupo4.Repositorios;

import java.util.List;

public class RepositorioDeResultadosDeEjecucion {
private List<ResultadosDeEjecucion>listaDeResultados;
private static RepositorioDeResultadosDeEjecucion instancia= new RepositorioDeResultadosDeEjecucion();

private RepositorioDeResultadosDeEjecucion(){
	
}

public static RepositorioDeResultadosDeEjecucion getInstancia() {
	return instancia;
}

}
