package grupo4.Acciones;

import grupo4.Repositorios.ResultadosDeBusquedas;

public interface Observers {

	public void realizarAccion(ResultadosDeBusquedas resultado);

	public EnumObservers getId();
}
