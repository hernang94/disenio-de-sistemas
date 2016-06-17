package grupo4.Repositorios;

import java.time.LocalDateTime;

public class ResultadosDeBusquedas {

	private long tiempoDeBusqueda;
	private String fraseBuscada;
	private LocalDateTime fechaDeBusqueda;
	private int cantidadDeResultados;
	
	
	public ResultadosDeBusquedas(long tiempoDeBusqueda, String fraseBuscada, LocalDateTime fechaDeBusqueda,
			int cantidadDeResultados) {
		super();
		this.tiempoDeBusqueda = tiempoDeBusqueda;
		this.fraseBuscada = fraseBuscada;
		this.fechaDeBusqueda = fechaDeBusqueda;
		this.cantidadDeResultados = cantidadDeResultados;
	}


	public long getTiempoDeBusqueda() {
		return tiempoDeBusqueda;
	}


	public String getFraseBuscada() {
		return fraseBuscada;
	}


	public LocalDateTime getFechaDeBusqueda() {
		return fechaDeBusqueda;
	}


	public int getCantidadDeResultados() {
		return cantidadDeResultados;
	}
	
	
	
}
