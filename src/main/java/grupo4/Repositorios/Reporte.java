package grupo4.Repositorios;

import java.util.Map;

public class Reporte {
	private String terminal;
	private Map<String,Integer>listaDeResultados;

	public Reporte(String terminal,Map<String, Integer> listaDeResultados) {
		super();
		this.terminal=terminal;
		this.listaDeResultados = listaDeResultados;
	}
	
	public void cargarReporte(String campo1,Integer campo2 ){
		listaDeResultados.put(campo1, campo2);
	}

	public String getTerminal() {
		return terminal;
	}

	public Map<String, Integer> getListaDeResultados() {
		return listaDeResultados;
	}
	
	
	
}
