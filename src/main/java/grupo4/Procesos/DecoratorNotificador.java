package grupo4.Procesos;

import grupo4.ComponentesExternos.EmailSender;

public class DecoratorNotificador implements Accion {
	private Accion decorado;
	private EmailSender notificador;

	public DecoratorNotificador(Accion decorado, EmailSender notificador) {
		this.decorado = decorado;
		this.notificador = notificador;
	}

	@Override
	public void ejecutar() { //lo cambio a void y meto un try and catch, y en el catch mando el mail
		try{
			decorado.ejecutar();
		}
		catch (Exception e) {
			notificador.enviarMail("Fallo el sistema");// Por ahora lo dejo asi porque no se si se
														// quiere que se sepa qué fallo
		}
	}
}
