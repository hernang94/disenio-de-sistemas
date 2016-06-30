package Tests;

import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import grupo4.POIs.Banco;
import grupo4.POIs.CGP;
import grupo4.POIs.Horario;
import grupo4.POIs.LocalComercial;
import grupo4.POIs.Parada;
import grupo4.POIs.Poi;
import grupo4.POIs.Rubro;
import grupo4.POIs.Servicio;
import grupo4.Repositorios.RepositorioDePois;

public class FuncionalidadesBasicasPoiTest {
	private RepositorioDePois dispositivoTactil;
	private Parada parada114;
	private Servicio pagoFacil;
	private Servicio timbrado;
	private Banco banco;
	private Banco banco2;
	private CGP cgp;
	private LocalComercial local;
	private Point unPuntoABuscar;
	private Rubro rubro;
	private Map<DayOfWeek, Horario> hashMapBanco;
	private Horario horarioBanco;
	private Map<DayOfWeek, Horario> hashMapLocalComercialManiana;
	private Map<DayOfWeek, Horario> hashMapLocalComercialTarde;
	private Map<DayOfWeek, Horario> hashMapServicio;
	private PrintWriter writer;

	@SuppressWarnings("static-access")

	@Before
	public void init() {
		// Arreglar los parametros
		dispositivoTactil = new RepositorioDePois("terminalAbasto", -1, writer);

		unPuntoABuscar = new Point(-34.638116, -58.4794967);
		horarioBanco = new Horario("10:00", "15:00");

		hashMapBanco = new HashMap<>();
		hashMapBanco.put(DayOfWeek.MONDAY, horarioBanco);
		hashMapBanco.put(DayOfWeek.TUESDAY, horarioBanco);
		hashMapBanco.put(DayOfWeek.WEDNESDAY, horarioBanco);
		hashMapBanco.put(DayOfWeek.THURSDAY, horarioBanco);
		hashMapBanco.put(DayOfWeek.FRIDAY, horarioBanco);
		List<String> palabrasClavesBanco = new ArrayList<>();
		palabrasClavesBanco.add("Santander");
		palabrasClavesBanco.add("Rio");
		palabrasClavesBanco.add("Fantino");
		palabrasClavesBanco.add("Banco");
		palabrasClavesBanco.add("Rojo");
		palabrasClavesBanco.add("Prestamo");
		palabrasClavesBanco.add("Cuenta corriente");
		palabrasClavesBanco.add("Cajero");
		banco = new Banco(hashMapBanco, "Santander Rio", palabrasClavesBanco);
		banco.setX(-34.6409182);
		banco.setY(-58.4758827);
		banco.setCoordenadas();

		List<String> palabrasClavesParada = new ArrayList<>();
		palabrasClavesParada.add("Bondi");
		palabrasClavesParada.add("UTN");
		palabrasClavesParada.add("Colectivo");
		palabrasClavesParada.add("Rojo");
		palabrasClavesParada.add("Vidrios polarizados");
		palabrasClavesParada.add("114");
		parada114 = new Parada("114", palabrasClavesParada);
		parada114.setX(-34.6417364);
		parada114.setY(-58.4792636);
		parada114.setCoordenadas();

		rubro = rubro.MUEBLERIA;
		// local = new LocalComercial(rubro, "09:00", "13:00", "14:00", "18:00",
		// 1, 6);
		hashMapLocalComercialManiana = new HashMap<>();
		hashMapLocalComercialManiana.put(DayOfWeek.MONDAY, new Horario("09:00", "13:00"));
		hashMapLocalComercialManiana.put(DayOfWeek.TUESDAY, new Horario("09:00", "13:00"));
		hashMapLocalComercialManiana.put(DayOfWeek.WEDNESDAY, new Horario("09:00", "13:00"));
		hashMapLocalComercialManiana.put(DayOfWeek.THURSDAY, new Horario("09:00", "13:00"));
		hashMapLocalComercialTarde = new HashMap<>();
		hashMapLocalComercialTarde.put(DayOfWeek.MONDAY, new Horario("14:00", "18:00"));
		hashMapLocalComercialTarde.put(DayOfWeek.TUESDAY, new Horario("14:00", "20:00"));
		hashMapLocalComercialTarde.put(DayOfWeek.THURSDAY, new Horario("14:00", "19:00"));
		List<String> palabrasClavesLocalComercial = new ArrayList<>();
		palabrasClavesLocalComercial.add("Muebles");
		palabrasClavesLocalComercial.add("Madera");
		palabrasClavesLocalComercial.add("Remaches");
		palabrasClavesLocalComercial.add("Carpintero");
		palabrasClavesLocalComercial.add("Mesa");
		palabrasClavesLocalComercial.add("Silla");
		local = new LocalComercial(rubro, hashMapLocalComercialManiana, hashMapLocalComercialTarde, "Blaisten",
				palabrasClavesLocalComercial);
		local.setX(-34.6383056);
		local.setY(-58.4814007);
		local.setCoordenadas();

		banco2 = new Banco(hashMapBanco, "HSBC", palabrasClavesBanco);
		banco2.setX(-34.6383669);
		banco2.setY(-58.4773822);
		banco2.setCoordenadas();

		hashMapServicio = new HashMap<>();
		hashMapServicio.put(DayOfWeek.THURSDAY, new Horario("12:00", "13:30"));
		hashMapServicio.put(DayOfWeek.FRIDAY, new Horario("12:00", "13:30"));
		Polygon comuna10 = new Polygon();
		comuna10.add(new Point(-34.637466, -58.476939));
		comuna10.add(new Point(-34.6350677, -58.4810659));
		comuna10.add(new Point(-34.6417364, -58.4792636));
		comuna10.add(new Point(-34.6409182, -58.4758827));
		comuna10.add(new Point(-34.6383056, -58.4814007));
		timbrado = new Servicio("timbrado", hashMapServicio);
		List<String> palabrasClavesCGP = new ArrayList<>();
		palabrasClavesCGP.add("10");
		palabrasClavesCGP.add("Floresta");
		palabrasClavesCGP.add("Monte Castro");
		palabrasClavesCGP.add("Velez");
		palabrasClavesCGP.add("Versalles");
		palabrasClavesCGP.add("Villa Luro");
		palabrasClavesCGP.add("Villa Real");
		palabrasClavesCGP.add("All Boys");
		cgp = new CGP(comuna10, "CGP10", palabrasClavesCGP);
		cgp.addServicio(timbrado);

		dispositivoTactil.agregarPoi(banco);
		dispositivoTactil.agregarPoi(banco2);
		dispositivoTactil.agregarPoi(parada114);
		dispositivoTactil.agregarPoi(local);
		dispositivoTactil.agregarPoi(cgp);

	}

	@Test
	public void cercaniaAParada() {
		Assert.assertFalse(dispositivoTactil.consultaCercania("114", unPuntoABuscar));
	}

	@Test
	public void cercaniaABanco() {
		Assert.assertTrue(dispositivoTactil.consultaCercania("santander rio", unPuntoABuscar));
	}

	@Test
	public void cercaniaALocal() {
		Assert.assertTrue(dispositivoTactil.consultaCercania("Blaisten", unPuntoABuscar));
	}

	@Test
	public void estaDisponibleColectivo() {
		Assert.assertTrue(dispositivoTactil.consultaDisponibilidad(LocalDateTime.of(2016, 04, 19, 11, 00), "114"));
	}

	@Test
	public void estaDisponibleBanco() {
		Assert.assertTrue(
				dispositivoTactil.consultaDisponibilidad(LocalDateTime.of(2016, 04, 19, 11, 00), "santander rio"));
	}

	@Test
	public void estaDisponibleCGP() {
		Assert.assertFalse(dispositivoTactil.consultaDisponibilidad(LocalDateTime.of(2016, 04, 29, 10, 00), timbrado));
	}

	@Test
	public void noEstaCercaLocalComercial() {
		Assert.assertFalse(local.estaCerca(new Point(2, 4)));
	}

	@Test
	public void noEncuentraNombreNiCriterioLocalComercial() {
		Assert.assertFalse(local.encuentraNombre("blah"));
	}

	@Test
	public void encuentraNombrePeroNoCriterioLocalComercial() {
		Assert.assertTrue(local.encuentraNombre("Blaisten"));
	}

	@Test
	public void estaDisponibleCGPSinServicio() {
		Assert.assertFalse(dispositivoTactil.consultaDisponibilidad(LocalDateTime.of(2016, 04, 29, 10, 00)));
	}

	@Test
	public void estaDisponibleLocal() {
		boolean a = dispositivoTactil.consultaDisponibilidad(LocalDateTime.of(2016, 04, 19, 17, 00), "Blaisten");
		Assert.assertTrue(a);
	}

	@Test
	public void pruebaHorario() {
		Horario nuevo = new Horario("09:00", "18:00");
		Assert.assertTrue(nuevo.estaEnHorario(LocalDateTime.of(2016, 04, 19, 17, 00)));
	}

	@Test
	public void pruebaBusquedaLibrexRubro() {
		Assert.assertTrue(coincideCon(dispositivoTactil.busquedaLibre("muebleria"), "Blaisten"));
	}

	@Test
	public void pruebaBusquedaLibrexLinea() {
		Assert.assertTrue(coincideCon(dispositivoTactil.busquedaLibre("114"), "114"));
	}

	@Test
	public void pruebaBusquedaLibrexCGPxServicio() {
		Assert.assertTrue(coincideCon(dispositivoTactil.busquedaLibre("timbrado"), "CGP10"));
	}

	@Test
	public void pruebaBusquedaLibrexBanco() {
		Assert.assertTrue(coincideCon(dispositivoTactil.busquedaLibre("santander rio"), "Santander rio"));
	}

	@Test
	public void pruebaPoligono() {
		Assert.assertTrue(cgp.estaCerca(new Point(-34.638116, -58.4794967)));
	}

	@Test
	public void pruebaServicioNoDisponible() {
		Assert.assertFalse(timbrado.estaDisponible(LocalDateTime.of(2016, 05, 8, 10, 00)));
	}

	@Test
	public void pruebaBusquedaDeServicioCuandoNoTiene() {
		Assert.assertFalse(banco.estaDisponible(LocalDateTime.of(2016, 04, 19, 11, 00), pagoFacil));
	}

	@Test
	public void consultarCercaniaABanco() {
		Assert.assertFalse(banco.estaCerca(new Point(-40.638116, -58.4794967)));
	}

	@Test
	public void bancoNoDisponible() {
		Assert.assertFalse(
				dispositivoTactil.consultaDisponibilidad(LocalDateTime.of(2016, 05, 8, 11, 00), "santander rio"));
	}

	@Test
	public void paradaCerca() {
		Assert.assertTrue(parada114.estaCerca(new Point(-34.6417164, -58.4792636)));
	}

	public boolean coincideCon(List<Poi> listaEncontrada, String criterio) {
		return listaEncontrada.stream().allMatch(unPoi -> unPoi.getNombre().equalsIgnoreCase(criterio));
	}

	public boolean coincideCon(List<Poi> listaEncontrada, Servicio servicio) {
		return listaEncontrada.stream().allMatch(unPoi -> unPoi.getNombre().equalsIgnoreCase(servicio.getNombre()));
	}

	@Test
	public void agregarPalabraClaveNoExistente() {
		banco.agregarPalabraClave("Ahorro");
		Assert.assertTrue(banco.getPalabrasClaves().contains("Ahorro"));
	}

	@Test
	public void agregarPalabraClaveExistente() {
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Palabra clave ya existente");
		banco.agregarPalabraClave("Fantino");
	}

	@Test
	public void quitarPalabraClaveExistente() {
		banco.quitarPalabraClave("Fantino");
		Assert.assertFalse(banco.getPalabrasClaves().contains("Fantino"));
	}

	@Test
	public void quitarPalabraClaveNoExistente() {
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Palabra clave no existente");
		banco.quitarPalabraClave("Ahorro");
	}

	@Test
	public void encontrarNombreParadaFalso() {
		Assert.assertFalse(parada114.encuentraNombre("101"));
	}

	@Test
	public void encontrarNombreParadaVerdadero() {
		Assert.assertTrue(parada114.encuentraNombre("114"));
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void agregarUnPoiExistente() {
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Poi ya existente");
		dispositivoTactil.agregarPoi(banco);
	}

	@Test
	public void quitarPoiNoExistente() {
		List<String> palabrasClaves = new ArrayList<>();
		palabrasClaves.add("Ramal Quilmes");
		Parada parada85 = new Parada("parada 85", palabrasClaves);
		dispositivoTactil.bajaPoi(parada85);
	}

	@Test
	public void fallaConsultaDisponibilidadPorCriterio() {
		Assert.assertFalse(dispositivoTactil.consultaDisponibilidad(LocalDateTime.of(2016, 06, 19, 10, 00), ""));
	}

	@Test
	public void fallaConsultaDisponibilidadPorSerivicio() {
		Servicio plomeria = new Servicio("plomeria", hashMapServicio);
		Assert.assertFalse(dispositivoTactil.consultaDisponibilidad(LocalDateTime.of(2016, 06, 19, 10, 00), plomeria));
	}

	@Test
	public void fallaConsultaDisponibilidadPorFecha() {
		Assert.assertFalse(dispositivoTactil.consultaDisponibilidad(LocalDateTime.of(2016, 06, 19, 10, 00)));
	}

	/*
	 * @Test public void pruebaModificacionPoi(){ List<String>
	 * palabrasClavesParada=new ArrayList<>();
	 * palabrasClavesParada.add("Bondi"); palabrasClavesParada.add("UTN");
	 * palabrasClavesParada.add("Colectivo"); palabrasClavesParada.add("Rojo");
	 * palabrasClavesParada.add("Vidrios polarizados");
	 * palabrasClavesParada.add("114"); palabrasClavesParada.add(
	 * "Aire Acondicionado"); Parada parada114x2 = new
	 * Parada("114",palabrasClavesParada); parada114x2.setX(-34.6417364);
	 * parada114x2.setY(-58.4792636); parada114x2.setCoordenadas();
	 * dispositivoTactil.modificarPoi(parada114x2);
	 * dispositivoTactil.filtrarPorCriterio(criterio)
	 * Assert.assertTrue(condition); }
	 */
}
