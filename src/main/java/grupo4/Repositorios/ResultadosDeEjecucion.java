package grupo4.Repositorios;

import java.time.LocalDateTime;

public class ResultadosDeEjecucion {
	private int cantidadDeElementosAfectados;
	private LocalDateTime fecha;
	private String resultado;

	public ResultadosDeEjecucion(int cantidadDeElementosAfectados, LocalDateTime fecha, String resultado) {
		super();
		this.cantidadDeElementosAfectados = cantidadDeElementosAfectados;
		this.fecha = fecha;
		this.resultado = resultado;
	}

	public int getCantidadDeElementosAfectados() {
		return cantidadDeElementosAfectados;
	}

	public LocalDateTime getDescripcion() {
		return fecha;
	}

	public String getResultado() {
		return resultado;
	}

}
