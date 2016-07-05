package grupo4.Procesos;

import java.time.LocalDateTime;
import java.util.List;

import grupo4.Acciones.ObserverAlmacenador;
import grupo4.Acciones.ObserverNotificador;
import grupo4.Acciones.ObserverReporter;
import grupo4.Acciones.Observers;
import grupo4.Acciones.Usuario;
import grupo4.ComponentesExternos.EmailSender;
import grupo4.Repositorios.RepositorioDeBusquedas;;

public class ProcesoGestionarAcciones implements Proceso {
	private LocalDateTime horaYFecha;
	private long periodicidad;
	private Accion accion;

	public ProcesoGestionarAcciones(LocalDateTime horaYFecha, long periodicidad, Accion accion) {
		this.horaYFecha = horaYFecha;
		this.periodicidad = periodicidad;
		this.accion = accion;
	}

	public void ejecutar() {
		accion.ejecutar();
	}

}
