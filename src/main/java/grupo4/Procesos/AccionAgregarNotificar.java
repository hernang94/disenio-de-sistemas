package grupo4.Procesos;

import java.util.List;

import grupo4.Acciones.ObserverNotificador;
import grupo4.Acciones.Observers;
import grupo4.Acciones.Usuario;
import grupo4.ComponentesExternos.EmailSender;

public class AccionAgregarNotificar implements Accion {

	private Criterio criterio;
	private Long tiempoEstipulado;
	private EmailSender notificador;
	public AccionAgregarNotificar(Criterio criterio,long tiempoEstipulado,EmailSender notificador) {
		this.criterio = criterio;
		this.tiempoEstipulado=tiempoEstipulado;
		this.notificador=notificador;
	}



	public void ejecutar() {
		ObserverNotificador observer = new ObserverNotificador(tiempoEstipulado, notificador);
		agregarAccion(observer, criterio.obtenerLista());
	}

	private void agregarAccion(Observers objeto,List<Usuario> lista){
		lista.stream().forEach(usuario -> usuario.agregarObserver(objeto));
	}
}
