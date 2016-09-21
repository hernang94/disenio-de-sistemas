package grupo4.Procesos;

import java.time.LocalDateTime;

public class Proceso implements Runnable {
	private LocalDateTime horaYFecha;
	private long periodicidad;
	private Accion accion;

	public Proceso(LocalDateTime horaYFecha, long periodicidad, Accion accion) {
		this.horaYFecha = horaYFecha;
		this.periodicidad = periodicidad;
		this.accion = accion;
	}

	public void ejecutar() throws Exception {
		try {
			accion.ejecutar();
		} catch (Exception e) {
			throw new Exception("No se pudo ejecutar el proceso");
		}
	}

	@Override
	public void run() {
		// this.ejecutar();
	}

	public LocalDateTime getHoraYFecha() {
		return horaYFecha;
	}

	public long getPeriodicidad() {
		return periodicidad;
	}

}
