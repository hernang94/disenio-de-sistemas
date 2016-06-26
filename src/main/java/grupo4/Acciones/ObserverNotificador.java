package grupo4.Acciones;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ObserverNotificador implements Observers {

	public void notificar(PrintWriter writer) {
		writer.println("Mail enviado al adminisitrador");
	}

	public void agregarBusqueda(long diferencia, String criterio, LocalDateTime tiempoInicio, int size) {

	}

	public void reporteTotalPorFecha(PrintWriter writer) {

	}

	public void reporteParcial(PrintWriter writer) {

	}

	public void reporteTotal(PrintWriter writer) {

	}
}
