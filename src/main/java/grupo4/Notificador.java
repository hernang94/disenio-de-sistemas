package grupo4;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

public class Notificador implements Observers{
	
	public void notificar(){
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		System.out.print("Mail enviado al adminisitrador");
	}
	
	public void agregarBusqueda(long diferencia, String criterio, LocalDateTime tiempoInicio, int size){
		
	}

	public void reporteTotalPorFecha(){
		
	}

}
