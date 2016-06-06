package grupo4;

import java.time.LocalDateTime;
import java.util.List;

public interface Busqueda {
	public List<Poi> busquedaLibre(String criterio);
	public long calcularDiferencia(LocalDateTime tiempoinicio, LocalDateTime tiempofin);
}
