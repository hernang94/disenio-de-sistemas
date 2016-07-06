package grupo4.Procesos;

import java.time.LocalDateTime;

public class Proceso {
	private LocalDateTime horaYFecha;
	private long periodicidad;
	private Accion accion;
	public Proceso(LocalDateTime horaYFecha, long periodicidad,Accion accion) {
		super();
		this.horaYFecha = horaYFecha;
		this.periodicidad = periodicidad;
		this.accion=accion;
	}
	public void ejecutar(){
		accion.ejecutar();
	}
	public LocalDateTime getHoraYFecha() {
		return horaYFecha;
	}
	public long getPeriodicidad(){
		return periodicidad;
	}
	
}
