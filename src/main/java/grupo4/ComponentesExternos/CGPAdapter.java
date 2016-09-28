package grupo4.ComponentesExternos;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.joda.time.LocalTime;

import DTOexterno.CentroDTO;
import DTOexterno.LocalComercialExterno;
import DTOexterno.RangoServicioDTO;
import DTOexterno.ServicioDTO;
import grupo4.HerramientasExternas.Poligono;
import grupo4.POIs.CGP;
import grupo4.POIs.Horario;
import grupo4.POIs.Poi;
import grupo4.POIs.Servicio;

public class CGPAdapter implements BuscadorDePois {

	private Map<Integer, Poligono> hashComunas;
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
		Comunas comunaAux = new Comunas();
		hashComunas = comunaAux.inicializarHashComunas();
		List<String> palabrasClavesAux = new ArrayList<>();
		CGP aux = new CGP(hashComunas.get(unCentro.getComuna()), Integer.toString(unCentro.getComuna()),
				palabrasClavesAux,hashComunas.get(unCentro.getComuna()).getPuntosPoligono().get(0));
		unCentro.getServiciosDTO().stream().forEach(servicio -> aux.addServicio(adaptarServicio(servicio)));
		return aux;

	}

	private Servicio adaptarServicio(ServicioDTO unServicio) {
		return new Servicio(unServicio.getNombre(), adaptarHorarios(unServicio.getRangos()));
	}

	private void cargarHorario(DayOfWeek dia, String horaDesde, String horaHasta,
			Map<DayOfWeek, Horario> hashHorariosAdaptados) {
		Horario horario = new Horario(horaDesde, horaHasta);
		hashHorariosAdaptados.put(dia, horario);
	}

	private Map<DayOfWeek, Horario> adaptarHorarios(List<RangoServicioDTO> listaRangos) {
		Map<DayOfWeek, Horario> hashHorariosAdapter = new HashMap<>();
		listaRangos.stream()
				.forEach(rango -> cargarHorario(rango.getDia(),
						new LocalTime(rango.getHoraDesde(), rango.getMinutoDesde()).toString(),
						new LocalTime(rango.getHoraHasta(), rango.getMinutoHasta()).toString(), hashHorariosAdapter));
		return hashHorariosAdapter;
	}

	public List<LocalComercialExterno> obtenerLocalesExternos() {
		return null;
	}

}