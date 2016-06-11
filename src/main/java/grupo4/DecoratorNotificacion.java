package grupo4;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DecoratorNotificacion extends Decorator{

	long tiempoEstipulado;
	
	public DecoratorNotificacion(long tiempoEstipulado, Busqueda decorado) {
		super(decorado);
		this.tiempoEstipulado = tiempoEstipulado;
	}

	public List<Poi> busquedaLibre (String criterio){
		List<Poi> listaAux=new ArrayList<>();
		long diferencia;
		LocalDateTime tiempoinicio = LocalDateTime.now();
		LocalDateTime tiempofin = LocalDateTime.now();
		diferencia=calcularDiferencia(tiempoinicio, tiempofin);
		if(diferencia>tiempoEstipulado){
			notificarAlAdministrador();
		}
		listaAux=decorado.busquedaLibre(criterio);
		return listaAux;
	}
	
	public String notificarAlAdministrador() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		System.out.print("Mail enviado al adminisitrador");
		return outContent.toString();
	}

}

