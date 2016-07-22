package grupo4.Procesos;

import java.time.LocalDateTime;
import java.util.List;
import grupo4.Acciones.Observers;
import grupo4.Acciones.Usuario;
import grupo4.Repositorios.RepositorioDeResultadosDeEjecucion;
import grupo4.Repositorios.ResultadosDeEjecucion;

public class AccionAgregarObserver implements Accion {
	private Observers observer;
	private Criterio criterioSeleccion;

	public AccionAgregarObserver(Observers observer, Criterio criterioSeleccion) {
		this.observer = observer;
		this.criterioSeleccion = criterioSeleccion;
	}

	public boolean ejecutar() {
		try {
			List<Usuario> usuarios = criterioSeleccion.obtenerLista();
			usuarios.stream().forEach(usuario -> usuario.agregarObserver(observer));
			RepositorioDeResultadosDeEjecucion.getInstancia().agregarResultado(new ResultadosDeEjecucion(
					usuarios.size(), LocalDateTime.now(), usuarios.size() + " Usuarios afectados"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
