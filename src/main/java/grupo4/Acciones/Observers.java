package grupo4.Acciones;

import java.time.LocalDateTime;

public interface Observers {

	//Usar un ENUM y tener los criterios/quitar herencia
	//Usar un metodo polimorfico unico y que cada uno lo redefina
	
	public void evaluarNotificacion(long diferencia);

	public void agregarBusqueda(String terminal, String criterio, long diferencia, LocalDateTime tiempoInicio,
			int size);

	public void reporteTotalPorFecha(String terminal);

	public void reporteParcial();

	public void reporteTotal();
}
