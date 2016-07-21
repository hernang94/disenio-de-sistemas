package grupo4.Acciones;

import java.util.List;

public class ReportePorFecha implements Reporte {
	
	private String terminalQueLoSolicita;
	private List<FechaCantReporte> datosReporte;
	
	public ReportePorFecha(String terminalQueLoSolicita, List<FechaCantReporte> datosReporte) {
		this.terminalQueLoSolicita = terminalQueLoSolicita;
		this.datosReporte = datosReporte;
	}
	
	public void reportar(){
		
	}
}
