package grupo4.Acciones;

import grupo4.Repositorios.ResultadoDeBusqueda;

public abstract class ObserverDeBusqueda {

	public abstract void realizarAccion(ResultadoDeBusqueda resultado);

	public abstract IdObserver getId();
}
