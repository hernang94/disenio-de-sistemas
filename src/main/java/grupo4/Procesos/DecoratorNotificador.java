package grupo4.Procesos;

import grupo4.ComponentesExternos.EmailSender;

public class DecoratorNotificador implements DecoratorFalla {
	private DecoratorNotificador decorado;
	private EmailSender notificador;

	public DecoratorNotificador(DecoratorNotificador decorado, EmailSender notificador) {
		this.decorado = decorado;
		this.notificador = notificador;
	}
	
	@Override
	public void actuarFrenteAFalla(Accion accionQueFallo) {
		decorado.actuarFrenteAFalla(accionQueFallo);
		notificador.enviarMail("Fallo una acción");
	}
}
