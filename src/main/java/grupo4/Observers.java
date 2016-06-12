package grupo4;

import java.time.LocalDateTime;

public interface Observers {

	public void notificar();

	public void agregarBusqueda(long diferencia, String criterio, LocalDateTime tiempoInicio, int size);

	public void reporteTotalPorFecha();
	
	public void reporteParcial();
	
	public void reporteTotal();
}
