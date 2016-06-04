package grupo4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.joda.time.LocalTime;
import org.uqbar.geodds.Polygon;

import externo.CentroDTO;
import externo.RangoServicioDTO;
import externo.ServicioDTO;

public class CGPAdapter implements Adaptadores{

	private Map<Integer, Polygon> hashComunas;
	private ComponenteCGPS componente;
	public void setComponente(ComponenteCGPS componente) {
		this.componente = componente;
	}

	public List<Poi> buscarPois(String valor) {
		return adaptarObjetos(this.componente.buscarCGPs(valor));
	}

	public List<Poi> adaptarObjetos(List<CentroDTO> listaExterna) {
		return listaExterna.stream().map(unCentro -> adaptar(unCentro)).collect(Collectors.toList());
	}

	private Poi adaptar(CentroDTO unCentro) {
		Comunas comunaAux= new Comunas();
		hashComunas= comunaAux.inicializarHashComunas();
		CGP aux = new CGP(hashComunas.get(unCentro.getComuna()),Integer.toString(unCentro.getComuna()));
		unCentro.getServiciosDTO().stream().forEach(servicio -> aux.addServicio(adaptarServicio(servicio)));
		return aux;

	}

	private Servicio adaptarServicio(ServicioDTO unServicio) {
		Servicio servAux = new Servicio(unServicio.getNombre(),adaptarHorarios(unServicio.getRangos()));
		return servAux;
	}

	private void cargarHorario(int dia,String horaDesde, String horaHasta,Map<Integer,Horario> hashHorariosAdaptados){
		Horario horario= new Horario(horaDesde, horaHasta);
		hashHorariosAdaptados.put(dia, horario);
		}
	
	private Map<Integer,Horario> adaptarHorarios(List<RangoServicioDTO> listaRangos) {
		Map<Integer,Horario> hashHorariosAdapter= new HashMap<>();
		listaRangos.stream()
		.forEach(rango -> cargarHorario(rango.getDia(),
				new LocalTime(rango.getHoraDesde(), rango.getMinutoDesde()).toString(),
				new LocalTime(rango.getHoraHasta(), rango.getMinutoHasta()).toString(),hashHorariosAdapter));
		return hashHorariosAdapter;
	}
	
}