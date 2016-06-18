package grupo4.Acciones;

import java.io.PrintWriter;
import java.time.LocalDateTime;

public interface Observers {

	public void notificar(PrintWriter writer);

	public void agregarBusqueda(long diferencia, String criterio, LocalDateTime tiempoInicio, int size);

	public void reporteTotalPorFecha(PrintWriter writer);
	
	public void reporteParcial(PrintWriter writer);
	
	public void reporteTotal(PrintWriter writer);
}
