package grupo4.Procesos;

import java.util.List;

import grupo4.Acciones.ObserverNotificador;
import grupo4.Acciones.Observers;
import grupo4.Acciones.Usuario;

public class AccionQuitarNotificar implements Accion{

	private Criterio criterio;
	@Override
	public void ejecutar() {
		quitarAccion(ObserverNotificador.class, criterio.obtenerLista());
	}
	private void quitarAccion(Object objeto,List<Usuario> lista){
		lista.stream().forEach(usuario -> usuario.quitarObserver(obtenerObserverSegunTipo(objeto,usuario)));
	}
	
	private Observers obtenerObserverSegunTipo(Object objeto,Usuario usuario){
		return usuario.getObservers().stream()
				.filter(observer -> observer.getClass().equals(objeto)).findFirst().get();
	}
	
}
