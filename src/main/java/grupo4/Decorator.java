package grupo4;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public abstract class Decorator implements Busqueda{
	protected Busqueda decorado;
	
	public Decorator(Busqueda decorado) {
		this.decorado = decorado;
	}

	public abstract List<Poi> busquedaLibre (String criterio);
	
	public long calcularDiferencia(LocalDateTime tiempoInicio, LocalDateTime tiempoFin) {
		long diferencia = ChronoUnit.SECONDS.between(tiempoInicio, tiempoFin);
		return diferencia;
	}
}
