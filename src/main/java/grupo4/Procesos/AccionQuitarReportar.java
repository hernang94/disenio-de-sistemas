package grupo4.Procesos;

import java.time.LocalDateTime;
import java.util.List;

import grupo4.Acciones.ObserverReporter;
import grupo4.Acciones.Observers;
import grupo4.Acciones.Usuario;
import grupo4.Repositorios.RepositorioDeResultadosDeEjecucion;
import grupo4.Repositorios.ResultadosDeEjecucion;

public class AccionQuitarReportar implements Accion{

	private Criterio criterio;
	@Override
	public void ejecutar() {
		quitarAccion(ObserverReporter.class, criterio.obtenerLista());
	}
	private void quitarAccion(Object objeto,List<Usuario> lista){
		lista.stream().forEach(usuario -> usuario.quitarObserver(obtenerObserverSegunTipo(objeto,usuario)));
		RepositorioDeResultadosDeEjecucion.getInstancia().agregarResultado(
		new ResultadosDeEjecucion(lista.size(), LocalDateTime.now(), lista.size() + " Usuarios afectados"));
	}
	
	private Observers obtenerObserverSegunTipo(Object objeto,Usuario usuario){
		return usuario.getObservers().stream()
				.filter(observer -> observer.getClass().equals(objeto)).findFirst().get();
	}
	
}
