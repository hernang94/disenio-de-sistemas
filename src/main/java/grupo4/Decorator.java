package grupo4;

import java.util.List;

public abstract class Decorator implements Busqueda{
	protected Busqueda decorado;
	
	public Decorator(Busqueda decorado) {
		this.decorado = decorado;
	}

	public abstract List<Poi> busquedaLibre (String criterio);
}
