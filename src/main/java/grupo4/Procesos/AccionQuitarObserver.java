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
	private DecoratorFalla decorator;
	
	public AccionQuitarObserver(Criterio criterioSeleccion, EnumObservers observerAQuitar, DecoratorFalla decorator) {
		this.criterioSeleccion = criterioSeleccion;
		this.observerAQuitar = observerAQuitar;
		this.decorator=decorator;
		
	}
	
	public void ejecutar(){
		List<Usuario> usuarios= criterioSeleccion.obtenerLista();
		usuarios.stream().forEach(usuario -> usuario.quitarObserver(obtenerObserverSegunTipo(observerAQuitar, usuario)));
		RepositorioDeResultadosDeEjecucion.getInstancia().agregarResultado(
				new ResultadosDeEjecucion(usuarios.size(), LocalDateTime.now(), usuarios.size() + " Usuarios afectados"));
	}
	
	private Observers obtenerObserverSegunTipo(EnumObservers observerId, Usuario usuario) {
		return usuario.getObservers().stream().filter(observer -> observerId.equals(observer.getId())).findFirst().get();
	}
}


