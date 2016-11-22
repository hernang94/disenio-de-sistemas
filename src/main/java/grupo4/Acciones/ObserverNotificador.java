package grupo4.Acciones;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import grupo4.ComponentesExternos.EmailSender;
import grupo4.Repositorios.ResultadoDeBusqueda;

@Entity
@DiscriminatorValue("NOTIFICADOR")
public class ObserverNotificador extends ObserverDeBusqueda {

	private long tiempoEstipulado;
	@Transient
	private EmailSender notificador;

	@SuppressWarnings("unused")
	private ObserverNotificador(){
		id = IdObserver.NOTIFICADOR;
	}
	
	public ObserverNotificador(long tiempoEstipulado, EmailSender notificador) {
		this.tiempoEstipulado = tiempoEstipulado;
		this.notificador = notificador;
		id = IdObserver.NOTIFICADOR;
	}

	public void evaluarNotificacion(long diferencia) {
		if (diferencia > tiempoEstipulado) {
			notificador.enviarMail("Tiempo de busqueda mayor al estipulado");
		}
	}

	public IdObserver getId() {
		return id;
	}

	@Override
	public void realizarAccion(ResultadoDeBusqueda resultado) {
		evaluarNotificacion(resultado.getTiempoDeBusqueda());
	}
	public String getTipo(){
		return "Notificador";
	}
}
