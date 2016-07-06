package grupo4.Procesos;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AdministradorDeProcesos {
	private static AdministradorDeProcesos instancia = new AdministradorDeProcesos();
	private ScheduledThreadPoolExecutor scheduler;

	private AdministradorDeProcesos() {
		this.scheduler = new ScheduledThreadPoolExecutor(1);
	}

	public static AdministradorDeProcesos getInstancia() {
		return instancia;
	}

	private Runnable ejecutarProceso(Proceso proceso) {
		proceso.ejecutar();
		return null;
	}

	public void crearProcesoNuevo(Accion accion, LocalDateTime fechaEjecucion, long periodicidad) {
		Proceso proceso = new Proceso(fechaEjecucion, periodicidad, accion);
		if (periodicidad == 0) {
			scheduler.schedule(this.ejecutarProceso(proceso),
					ChronoUnit.SECONDS.between(LocalDateTime.now(), fechaEjecucion), TimeUnit.SECONDS);
		} else {
			scheduler.scheduleAtFixedRate(this.ejecutarProceso(proceso),
					ChronoUnit.SECONDS.between(LocalDateTime.now(), fechaEjecucion), periodicidad, TimeUnit.SECONDS);
		}
	}
}
