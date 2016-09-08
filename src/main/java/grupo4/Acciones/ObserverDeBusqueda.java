package grupo4.Acciones;

import grupo4.Repositorios.ResultadoDeBusqueda;

public interface ObserverDeBusqueda {

	public void realizarAccion(ResultadoDeBusqueda resultado);

	public IdObserver getId();
}
