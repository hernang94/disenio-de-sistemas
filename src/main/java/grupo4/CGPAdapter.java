package grupo4;

import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.LocalTime;

import externo.CentroDTO;
import externo.ServicioDTO;

public class CGPAdapter{
	private ComponenteCGPS componente;
	
	public List<CGP> solicitarCGPs (String valor){
		return adaptarObjetos(this.componente.buscarCGPs(valor));
	}

	private List<CGP> adaptarObjetos(List<CentroDTO> listaExterna) {
		return listaExterna.stream().map(unCentro->adaptar(unCentro)).collect(Collectors.toList());	
	}

	private CGP adaptar(CentroDTO unCentro) {
		CGP aux = new CGP(null);
		aux.setNombre(Integer.toString(unCentro.getComuna()));
		aux.setCalle(unCentro.getDomicilio());
		unCentro.getServiciosDTO().stream().forEach(servicio->aux.addServicio(adaptarServicio(servicio)));
		return aux;
		
		
	}
	private Servicio adaptarServicio(ServicioDTO unServicio){
		Servicio servAux= new Servicio(unServicio.getNombre());
		unServicio.getRangos().stream().forEach(rango-> servAux.cargarHorario(rango.getDia(), new LocalTime(rango.getHoraDesde(), rango.getMinutoDesde()).toString(), new LocalTime(rango.getHoraHasta(), rango.getMinutoHasta()).toString()));
		return servAux;
	}
	
}
