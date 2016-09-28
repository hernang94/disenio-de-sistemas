package grupo4.Acciones;

import java.util.Map;

public class ReporteTotal implements Reporte {
	private String terminalQueSolicita;
	private Map<String, Integer> datos;

	public String getTerminalQueSolicita() {
		return terminalQueSolicita;
	}

	public Map<String, Integer> getDatos() {
		return datos;
	}

	public ReporteTotal(String terminalQueSolicita, Map<String, Integer> datos) {
		this.terminalQueSolicita = terminalQueSolicita;
		this.datos = datos;
	}

}
