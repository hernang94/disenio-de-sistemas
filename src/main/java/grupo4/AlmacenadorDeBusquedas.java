package grupo4;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AlmacenadorDeBusquedas implements Observers {
	RepositorioDeBusquedas almacen;

	public AlmacenadorDeBusquedas(RepositorioDeBusquedas almacen) {
		this.almacen = almacen;
	}

	public void notificar(){
		
	}

	public void agregarBusqueda(long diferencia, String criterio, LocalDateTime tiempoInicio, int size){
		ResultadosDeBusquedas newResult=new ResultadosDeBusquedas(diferencia, criterio, tiempoInicio, size);
		almacen.agregarBusqueda(newResult);
	}

	public void reporteTotalPorFecha(){
		
	}
	public void reporteParcial(){
		
	}
	public void reporteTotal(){
	
	}
}
