package grupo4.Procesos;

import java.time.LocalDateTime;
import java.util.List;

import grupo4.Acciones.Observers;
import grupo4.Acciones.Usuario;
import grupo4.Repositorios.RepositorioDeResultadosDeEjecucion;
import grupo4.Repositorios.ResultadosDeEjecucion;

public abstract class AccionGestionObserver implements Accion {
	protected Criterio criterio;

	public AccionGestionObserver(Criterio criterio) {
		this.criterio = criterio;
	}

	public abstract void ejecutar();

	protected void agregarAccion(Observers objeto, List<Usuario> lista) {
		lista.stream().forEach(usuario -> usuario.agregarObserver(objeto));
		RepositorioDeResultadosDeEjecucion.getInstancia().agregarResultado(
				new ResultadosDeEjecucion(lista.size(), LocalDateTime.now(), lista.size() + " Usuarios afectados"));
	}

	protected void quitarAccion(Object objeto, List<Usuario> lista) {
		lista.stream().forEach(usuario -> usuario.quitarObserver(obtenerObserverSegunTipo(objeto, usuario)));
		RepositorioDeResultadosDeEjecucion.getInstancia().agregarResultado(
				new ResultadosDeEjecucion(lista.size(), LocalDateTime.now(), lista.size() + " Usuarios afectados"));
	}

	protected Observers obtenerObserverSegunTipo(Object objeto, Usuario usuario) {
		return usuario.getObservers().stream().filter(observer -> observer.getClass().equals(objeto)).findFirst().get();
	}
}
