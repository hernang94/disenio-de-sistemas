package grupo4.Procesos;

import java.time.LocalDateTime;
import java.util.List;
import grupo4.Acciones.IdObserver;
import grupo4.Acciones.ObserverDeBusqueda;
import grupo4.Acciones.Usuario;
import grupo4.Repositorios.RepositorioDeResultadosDeEjecucion;
import grupo4.Repositorios.ResultadosDeEjecucion;

public class AccionQuitarObserver implements Accion {

	private Criterio criterioSeleccion;
	private IdObserver observerAQuitar;

	public AccionQuitarObserver(Criterio criterioSeleccion, IdObserver observerAQuitar) {
		this.criterioSeleccion = criterioSeleccion;
		this.observerAQuitar = observerAQuitar;
	}

	public void ejecutar() {
			List<Usuario> usuarios = criterioSeleccion.obtenerLista();
			usuarios.stream()
					.forEach(usuario -> usuario.quitarObserver(obtenerObserverSegunTipo(observerAQuitar, usuario)));
			RepositorioDeResultadosDeEjecucion.getInstancia().agregarResultado(new ResultadosDeEjecucion(
					usuarios.size(), LocalDateTime.now(), usuarios.size() + " Usuarios afectados"));
	}

	private ObserverDeBusqueda obtenerObserverSegunTipo(IdObserver observerId, Usuario usuario) {
		return usuario.getObservers().stream().filter(observer -> observerId.equals(observer.getId())).findFirst()
				.get();
	}
}