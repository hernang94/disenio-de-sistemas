package grupo4.Procesos;

import java.time.LocalDateTime;

import grupo4.Repositorios.RepositorioDeResultadosDeEjecucion;
import grupo4.Repositorios.ResultadosDeEjecucion;

public class Proceso implements Runnable {
	private LocalDateTime horaYFecha;
	private long periodicidad;
	private Accion accion;

	public Proceso(LocalDateTime horaYFecha, long periodicidad, Accion accion) {
		this.horaYFecha = horaYFecha;
		this.periodicidad = periodicidad;
		this.accion = accion;
	}
	
	@Override
	public void run() {
		ResultadosDeEjecucion resultado;
		try {
			resultado = accion.ejecutar();
		} catch (Exception e) {
			throw new RuntimeException("No se pudo ejecutar el proceso");
		}
		if(resultado==null){
			resultado = new ResultadosDeEjecucion(0, LocalDateTime.now(), "fallo el proceso");
		}
		RepositorioDeResultadosDeEjecucion.getInstancia().agregarResultado(resultado);
	}

	public LocalDateTime getHoraYFecha() {
		return horaYFecha;
	}

	public long getPeriodicidad() {
		return periodicidad;
	}

}
