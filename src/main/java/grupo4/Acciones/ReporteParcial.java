package grupo4.Acciones;

import java.util.List;

public class ReporteParcial implements Reporte {
	private String terminalQueLoSolicito;
	private List<Integer> cantParciales;

	public ReporteParcial(String terminalQueLoSolicito, List<Integer> cantParciales) {
		this.terminalQueLoSolicito = terminalQueLoSolicito;
		this.cantParciales = cantParciales;
	}

	public String getTerminalQueLoSolicito() {
		return terminalQueLoSolicito;
	}

	public List<Integer> getCantParciales() {
		return cantParciales;
	}
}