package grupo4.Procesos;

import java.time.LocalDateTime;
import java.util.List;
import grupo4.Acciones.ObserverDeBusqueda;
import grupo4.Acciones.Usuario;
import grupo4.Repositorios.RepositorioDeResultadosDeEjecucion;
import grupo4.Repositorios.ResultadosDeEjecucion;

public class AccionAgregarObserver implements Accion {
	private ObserverDeBusqueda observer;
	private Criterio criterioSeleccion;

	public AccionAgregarObserver(ObserverDeBusqueda observer, Criterio criterioSeleccion) {
		this.observer = observer;
		this.criterioSeleccion = criterioSeleccion;
	}

	public ResultadosDeEjecucion ejecutar() {
		List<Usuario> usuarios = criterioSeleccion.obtenerLista();
		usuarios.stream().forEach(usuario -> usuario.agregarObserver(observer));
		return new ResultadosDeEjecucion(usuarios.size(), LocalDateTime.now(), usuarios.size() + " Usuarios afectados");
	}
}
