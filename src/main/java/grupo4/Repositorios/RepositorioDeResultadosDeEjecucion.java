package grupo4.Repositorios;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeResultadosDeEjecucion {
private List<ResultadosDeEjecucion>listaDeResultados=new ArrayList<>();
private static RepositorioDeResultadosDeEjecucion instancia= new RepositorioDeResultadosDeEjecucion();

private RepositorioDeResultadosDeEjecucion(){
	
}

public static RepositorioDeResultadosDeEjecucion getInstancia() {
	return instancia;
}

public void agregarResultado(ResultadosDeEjecucion resultado){
	listaDeResultados.add(resultado);
}
public List<ResultadosDeEjecucion>getlistaDeResultados(){
	return listaDeResultados;
}

}
