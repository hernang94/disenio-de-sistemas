package grupo4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.joda.time.LocalTime;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import externo.CentroDTO;
import externo.ServicioDTO;

public class CGPAdapter {

	private ComponenteCGPS componente;

	public void setComponente(ComponenteCGPS componente) {
		this.componente = componente;
	}

	public List<CGP> solicitarCGPs(String valor) {
		return adaptarObjetos(this.componente.buscarCGPs(valor));
	}

	private List<CGP> adaptarObjetos(List<CentroDTO> listaExterna) {
		return listaExterna.stream().map(unCentro -> adaptar(unCentro)).collect(Collectors.toList());
	}

	private CGP adaptar(CentroDTO unCentro) {
		Map<Integer, Polygon> hashComunas = new HashMap<>();
		hashComunas = inicializarHashComunas();
		CGP aux = new CGP(hashComunas.get(unCentro.getComuna()));
		aux.setNombre(Integer.toString(unCentro.getComuna()));
		unCentro.getServiciosDTO().stream().forEach(servicio -> aux.addServicio(adaptarServicio(servicio)));
		return aux;

	}

	private Servicio adaptarServicio(ServicioDTO unServicio) {
		Servicio servAux = new Servicio(unServicio.getNombre());
		unServicio.getRangos().stream()
				.forEach(rango -> servAux.cargarHorario(rango.getDia(),
						new LocalTime(rango.getHoraDesde(), rango.getMinutoDesde()).toString(),
						new LocalTime(rango.getHoraHasta(), rango.getMinutoHasta()).toString()));
		return servAux;
	}

	public Map<Integer, Polygon> inicializarHashComunas() {
		Map<Integer, Polygon> hashComunas = new HashMap<>();
		hashComunas.put(1,
				new Polygon(Arrays.asList(new Point(-34.5684496, -58.3836877), new Point(-34.599593, -58.392975),
						new Point(-34.634127, -58.390370), new Point(-34.629440, -58370666),
						new Point(-34.632416, -58.349632), new Point(-34.627089, -58.335212),
						new Point(-34.602283, -58.347233))));

		hashComunas.put(2,
				new Polygon(Arrays.asList(new Point(-34.569949, -58.399233), new Point(-34.573236, -58.401832),
						new Point(-34.578239, -38.396410), new Point(-34.584431, -58.400514),
						new Point(-34.583516, -58.406187), new Point(-34.597835, -58.415902),
						new Point(-34.599313, -58.386872), new Point(-34.591695, -58.387929),
						new Point(-34.579471, -58.376668))));

		hashComunas.put(3,
				new Polygon(Arrays.asList(new Point(-34.620654, -58.412508), new Point(-34.608184, -58.414697),
						new Point(-34.598018, -58.411890), new Point(-34.599638, -58.392959),
						new Point(-34.618135, -58.391683))));

		hashComunas.put(4, new Polygon(Arrays.asList(new Point(-34.662045, -58.424107),
				new Point(-34.653168, -58.434391), new Point(-34.655834, -58.438091), new Point(-34.650375, -58.445106),
				new Point(-34.645130, -58.432145), new Point(-34.641741, -58.432360), new Point(-34.638069, -58.411246),
				new Point(-34.630389, -58.411788), new Point(-34.627216, -58.391221), new Point(-34.634117, -58.390379),
				new Point(-34.626652, -58.371048), new Point(-34.629418, -58.370657), new Point(-34.625008, -58.360808),
				new Point(-34.633662, -58.352931), new Point(-34.644915, -58.357779), new Point(-34.653859, -58.370029),
				new Point(-34.661775, -58.3975418))));

		hashComunas.put(5,
				new Polygon(Arrays.asList(new Point(-34.640147, -58.423472), new Point(-34.615428, -58.430077),
						new Point(-34.605943, -58.430698), new Point(-34.602694, -58.433316),
						new Point(-34.597748, -58.423403), new Point(-34.597995, -58.411987),
						new Point(-34.623011, -58.412077), new Point(-34.635126, -58.410525),
						new Point(-34.638036, -58.411283))));

		hashComunas.put(6,
				new Polygon(Arrays.asList(new Point(-34.630569, -58.451597), new Point(-34.626826, -58.426878),
						new Point(-34.615384, -58.430053), new Point(-34.605706, -58.430740),
						new Point(-34.602597, -58.433315), new Point(-34.607542, -58.446104),
						new Point(-34.604363, -58.458463), new Point(-34.607260, -58.462755),
						new Point(-34.630781, -58.451683))));

		hashComunas.put(7,
				new Polygon(Arrays.asList(new Point(-34.622367, -58.477772), new Point(-34.613932, -58.459477),
						new Point(-34.630623, -58.451569), new Point(-34.626996, -58.426780),
						new Point(-34.640212, -58.423512), new Point(-34.641754, -58.432333),
						new Point(-34.645175, -58.432119), new Point(-34.650868, -58.444693),
						new Point(-34.650908, -58.453813), new Point(-34.656826, -58.460363),
						new Point(-34.651735, -58.466623))));

		hashComunas.put(8,
				new Polygon(Arrays.asList(new Point(-34.705011, -58.461450), new Point(-34.662240, -58.424200),
						new Point(-34.652920, -58.434499), new Point(-34.656027, -58.437933),
						new Point(-34.650943, -58.444971), new Point(-34.651085, -58.454069),
						new Point(-34.664781, -58.469518), new Point(-34.658286, -58.479131),
						new Point(-34.674417, -58.502477))));

		hashComunas.put(9, new Polygon(Arrays.asList(new Point(-34.634553, -58.530041),
				new Point(-34.634411, -58.511502), new Point(-34.639779, -58.509785), new Point(-34.645569, -58.502575),
				new Point(-34.643168, -58.497082), new Point(-34.636954, -58.478714), new Point(-34.636671, -58.471676),
				new Point(-34.646275, -58.462578), new Point(-34.651641, -58.466355), new Point(-34.656442, -58.460690),
				new Point(-34.664632, -58.469960), new Point(-34.657713, -58.478886), new Point(-34.674427, -58.502600),
				new Point(-34.654434, -58.529412), new Point(-34.634665, -58.529942))));

		hashComunas.put(10,
				new Polygon(Arrays.asList(new Point(-34.634738, -58.530290), new Point(-34.611219, -58.529371),
						new Point(-34.620123, -58.517429), new Point(-34.609203, -58.500281),
						new Point(-34.624491, -58.482929), new Point(-34.622475, -58.477825),
						new Point(-34.636585, -58.471497), new Point(-34.639188, -58.476192),
						new Point(-34.637005, -58.478540), new Point(-34.644815, -58.495382),
						new Point(-34.645822, -58.502220), new Point(-34.639944, -58.510080),
						new Point(-34.634738, -58.510896), new Point(-34.634570, -58.530290))));

		hashComunas.put(11,
				new Polygon(Arrays.asList(new Point(-34.581145, -58.515432), new Point(-34.594714, -58.502726),
						new Point(-34.597922, -58.483023), new Point(-34.600180, -58.476744),
						new Point(-34.605348, -58.474073), new Point(-34.601903, -58.469021),
						new Point(-34.604517, -58.458628), new Point(-34.607368, -58.462886),
						new Point(-34.614021, -58.459422), new Point(-34.624712, -58.482662),
						new Point(-34.609127, -58.500263), new Point(-34.620217, -58.517257),
						new Point(-34.610469, -58.529442))));

		hashComunas.put(12,
				new Polygon(Arrays.asList(new Point(-34.581205, -58.515301), new Point(-34.549705, -58.500722),
						new Point(-34.538647, -58.475749), new Point(-34.548991, -58.468532),
						new Point(-34.552320, -58.474595), new Point(-34.561593, -58.467377),
						new Point(-34.593866, -58.503468), new Point(-34.566704, -58.473584),
						new Point(-34.572766, -58.467955))));

		hashComunas.put(13,
				new Polygon(Arrays.asList(new Point(-34.583122, -58.444426), new Point(-34.578525, -58.439996),
						new Point(-34.575377, -58.443880), new Point(-34.571978, -58.440906),
						new Point(-34.567730, -58.448978), new Point(-34.558769, -58.441464),
						new Point(-34.549318, -58.428256), new Point(-34.526948, -58.459243),
						new Point(-34.534585, -58.465337), new Point(-34.538571, -58.475915),
						new Point(-34.548744, -58.468361), new Point(-34.552191, -58.474690),
						new Point(-34.561942, -58.467035), new Point(-34.566649, -58.473567),
						new Point(-34.578152, -58.460534), new Point(-34.583149, -58.444390))));

		hashComunas.put(14,
				new Polygon(Arrays.asList(new Point(-34.597597, -58.416041), new Point(-34.583465, -58.406256),
						new Point(-34.584596, -58.400763), new Point(-34.578236, -58.396643),
						new Point(-34.573147, -58.401793), new Point(-34.569931, -58.399262),
						new Point(-34.572249, -58.394354), new Point(-34.565355, -58.397096),
						new Point(-34.550792, -58.430546), new Point(-34.558890, -58.441470),
						new Point(-34.567986, -58.448996), new Point(-34.575182, -58.444019),
						new Point(-34.584377, -58.445112))));
		hashComunas.put(15,
				new Polygon(Arrays.asList(new Point(-34.572384, -58.467278), new Point(-34.583178, -58.444458),
						new Point(-34.597767, -58.423580), new Point(-34.607558, -58.446157),
						new Point(-34.605261, -58.473954), new Point(-34.594358, -58.503027))));

		return hashComunas;
	}
}
