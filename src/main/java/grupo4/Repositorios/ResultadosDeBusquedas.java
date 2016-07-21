package grupo4.Repositorios;

import java.time.LocalDate;

public class ResultadosDeBusquedas {

	private String terminalDeLaBusqueda;
	private long tiempoDeBusqueda;
	private String fraseBuscada;
	private LocalDate fechaDeBusqueda;
	private int cantidadDeResultados;

	public ResultadosDeBusquedas(String terminal, long tiempoDeBusqueda, String fraseBuscada, LocalDate fechaDeBusqueda,
			int cantidadDeResultados) {
		this.terminalDeLaBusqueda = terminal;
		this.tiempoDeBusqueda = tiempoDeBusqueda;
		this.fraseBuscada = fraseBuscada;
		this.fechaDeBusqueda = fechaDeBusqueda;
		this.cantidadDeResultados = cantidadDeResultados;
	}

	public String getTerminalDeLaBusqueda() {
		return terminalDeLaBusqueda;
	}

	public long getTiempoDeBusqueda() {
		return tiempoDeBusqueda;
	}

	public String getFraseBuscada() {
		return fraseBuscada;
	}

	public LocalDate getFechaDeBusqueda() {
		return fechaDeBusqueda;
	}

	public int getCantidadDeResultados() {
		return cantidadDeResultados;
	}

	public boolean esDeTerminal(String unTerminal) {
		return unTerminal.equalsIgnoreCase(terminalDeLaBusqueda);
	}

}
