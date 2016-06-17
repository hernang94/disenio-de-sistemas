package grupo4;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.ResultadosDeBusquedas;

public class AlmacenadorDeBusquedas implements Observers {
	RepositorioDeBusquedas almacen;

	public AlmacenadorDeBusquedas(RepositorioDeBusquedas almacen) {
		this.almacen = almacen;
	}

	public void notificar(PrintWriter writer){
		
	}

	public void agregarBusqueda(long diferencia, String criterio, LocalDateTime tiempoInicio, int size){
		ResultadosDeBusquedas newResult=new ResultadosDeBusquedas(diferencia, criterio, tiempoInicio, size);
		almacen.agregarBusqueda(newResult);
	}

	public void reporteTotalPorFecha(PrintWriter writer){
		
	}
	public void reporteParcial(PrintWriter writer){
		
	}
	public void reporteTotal(PrintWriter writer){
	
	}
}
