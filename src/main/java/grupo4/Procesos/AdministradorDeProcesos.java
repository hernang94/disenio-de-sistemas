package grupo4.Procesos;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AdministradorDeProcesos {
	private static AdministradorDeProcesos instancia = new AdministradorDeProcesos();
	private ScheduledExecutorService scheduler;

	private AdministradorDeProcesos() {
		this.scheduler = Executors.newScheduledThreadPool(1);
	}

	public static AdministradorDeProcesos getInstancia() {
		return instancia;
	}

	public Runnable ejecutarProceso(Proceso proceso) throws Exception {
		proceso.run();
		return proceso;
	}

	public void crearProcesoNuevo(Accion accion, LocalDateTime fechaEjecucion, long periodicidad) throws Exception {
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
