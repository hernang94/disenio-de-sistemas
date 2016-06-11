package grupo4;

import java.time.LocalDateTime;

public class Reporter implements Observers{
	RepositorioDeBusquedas almacen;
	
	public Reporter(RepositorioDeBusquedas almacen) {
		this.almacen = almacen;
	}

	public void notificar(){
		
	}

	public void agregarBusqueda(long diferencia, String criterio, LocalDateTime tiempoInicio, int size){
		
	}

	public void reporteTotalPorFecha(){
		
	}

}
