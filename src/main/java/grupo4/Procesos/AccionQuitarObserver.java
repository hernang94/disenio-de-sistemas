package grupo4.Procesos;

import java.time.LocalDateTime;
import java.util.List;
import grupo4.Acciones.EnumObservers;
import grupo4.Acciones.Observers;
import grupo4.Acciones.Usuario;
import grupo4.Repositorios.RepositorioDeResultadosDeEjecucion;
import grupo4.Repositorios.ResultadosDeEjecucion;

public class AccionQuitarObserver implements Accion {

	private Criterio criterioSeleccion;
	private EnumObservers observerAQuitar;

	public AccionQuitarObserver(Criterio criterioSeleccion, EnumObservers observerAQuitar) {
		this.criterioSeleccion = criterioSeleccion;
		this.observerAQuitar = observerAQuitar;
	}

	public boolean ejecutar() {
		try {
			List<Usuario> usuarios = criterioSeleccion.obtenerLista();
			usuarios.stream()
					.forEach(usuario -> usuario.quitarObserver(obtenerObserverSegunTipo(observerAQuitar, usuario)));
			RepositorioDeResultadosDeEjecucion.getInstancia().agregarResultado(new ResultadosDeEjecucion(
					usuarios.size(), LocalDateTime.now(), usuarios.size() + " Usuarios afectados"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private Observers obtenerObserverSegunTipo(EnumObservers observerId, Usuario usuario) {
		return usuario.getObservers().stream().filter(observer -> observerId.equals(observer.getId())).findFirst()
				.get();
	}
}
