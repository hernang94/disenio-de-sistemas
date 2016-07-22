package grupo4.Acciones;

import java.util.Map;

public class ReporteTotal implements Reporte {
	private String terminalQueSolicita;
	private Map<String, Integer> datos;

	public ReporteTotal(String terminalQueSolicita, Map<String, Integer> datos) {
		this.terminalQueSolicita = terminalQueSolicita;
		this.datos = datos;
	}

	// public void reportar(){}

}
