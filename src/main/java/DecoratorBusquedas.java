import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import grupo4.Busqueda;
import grupo4.Decorator;
import grupo4.Poi;
import grupo4.ResultadosDeBusquedas;

public class DecoratorBusquedas extends Decorator {
	
	List<ResultadosDeBusquedas> listaDeResultados;
	
	public DecoratorBusquedas(Busqueda decorado, List<ResultadosDeBusquedas> listaDeResultados) {
		super(decorado);
		this.listaDeResultados = listaDeResultados;
	}

	public List<Poi> busquedaLibre (String criterio){
		List<Poi> listaAux=new ArrayList<>();
		long diferencia;
		LocalDateTime tiempoInicio = LocalDateTime.now();
		listaAux=decorado.busquedaLibre(criterio);
		LocalDateTime tiempoFin = LocalDateTime.now();
		diferencia=calcularDiferencia(tiempoInicio, tiempoFin);
		cargarResultados(diferencia,criterio,tiempoInicio,listaAux.size());
		return listaAux;
	}
	
	public long calcularDiferencia(LocalDateTime tiempoInicio, LocalDateTime tiempoFin) {
		long diferencia = ChronoUnit.SECONDS.between(tiempoInicio, tiempoFin);
		return diferencia;
	}
	
	private void cargarResultados(long tiempoDeBusqueda, String fraseBuscada, LocalDateTime fechaDeBusqueda,
			int cantidadDeResultados) {
		ResultadosDeBusquedas resultado = new ResultadosDeBusquedas(tiempoDeBusqueda, fraseBuscada, fechaDeBusqueda,
				cantidadDeResultados);
		listaDeResultados.add(resultado);
	}
}
