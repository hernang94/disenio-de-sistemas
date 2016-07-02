package Tests;

import java.io.IOException;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import DTOexterno.BancoExterno;
import DTOexterno.CentroDTO;
import DTOexterno.RangoServicioDTO;
import DTOexterno.ServicioDTO;
import grupo4.ComponentesExternos.BancoTransformer;
import grupo4.ComponentesExternos.CGPAdapter;
import grupo4.ComponentesExternos.ComponenteBanco;
import grupo4.ComponentesExternos.ComponenteCGPS;
import grupo4.ComponentesExternos.Http;
import grupo4.POIs.Banco;
import grupo4.POIs.CGP;
import grupo4.POIs.Horario;
import grupo4.POIs.LocalComercial;
import grupo4.POIs.Parada;
import grupo4.POIs.Poi;
import grupo4.POIs.Rubro;
import grupo4.POIs.Servicio;
import grupo4.Repositorios.RepositorioDePois;

public class ComponentesExternosTest {
	private List<CentroDTO> listaCentroAAdaptar;
	private RepositorioDePois dispositivoTactil;
	private Parada parada114;
	private Servicio timbrado;
	private CentroDTO centroPrueba;
	private ServicioDTO servicioPrueba;
	private RangoServicioDTO rangoPrueba;
	private Banco banco;
	private Banco banco2;
	private CGP cgp;
	private LocalComercial local;
	private Rubro rubro;
	private ComponenteCGPS componente;
	private CGPAdapter adaptador;
	private ComponenteBanco componenteBanco;
	private BancoTransformer optimus;
	private BancoTransformer megatron;
	private Map<DayOfWeek, Horario> hashMapBanco;
	private Horario horarioBanco;
	private Map<DayOfWeek, Horario> hashMapLocalComercialManiana;
	private Map<DayOfWeek, Horario> hashMapLocalComercialTarde;
	private Map<DayOfWeek, Horario> hashMapServicio;
	private List<String> palabrasClavesBanco;
	private List<String> palabrasClavesCGP;
	private List<String> palabrasClavesParada;
	private List<String> palabrasClavesLocalComercial;
	private BancoExterno bancoExterno;
	private Http http;

	@SuppressWarnings("static-access")

	@Before
	public void init() {
		listaCentroAAdaptar = new ArrayList<>();

		rangoPrueba = new RangoServicioDTO(DayOfWeek.MONDAY, 9, 0, 18, 0);

		servicioPrueba = new ServicioDTO("Prueba");
		servicioPrueba.agregarRango(rangoPrueba);
		
		megatron = Mockito.mock(BancoTransformer.class);
		megatron.setComponente(componenteBanco);

		centroPrueba = new CentroDTO(9, "Mataderos,Parque Avellaneda", "Mauro Corvaro", "Calle Falsa 123", "4597-9684");
		centroPrueba.agregarServicio(servicioPrueba);
		listaCentroAAdaptar.add(centroPrueba);

		componente = Mockito.mock(ComponenteCGPS.class);
		adaptador = new CGPAdapter();
		adaptador.setComponente(componente);

		componenteBanco = Mockito.mock(ComponenteBanco.class);
		optimus = new BancoTransformer();
		optimus.setComponente(componenteBanco);

		dispositivoTactil = RepositorioDePois.getInstancia();
		dispositivoTactil.agregarAdaptador(adaptador);
		dispositivoTactil.agregarAdaptador(megatron);
		horarioBanco = new Horario("10:00", "15:00");

		hashMapBanco = new HashMap<>();
		hashMapBanco.put(DayOfWeek.MONDAY, horarioBanco);
		hashMapBanco.put(DayOfWeek.TUESDAY, horarioBanco);
		hashMapBanco.put(DayOfWeek.WEDNESDAY, horarioBanco);
		hashMapBanco.put(DayOfWeek.THURSDAY, horarioBanco);
		hashMapBanco.put(DayOfWeek.FRIDAY, horarioBanco);
		palabrasClavesBanco = new ArrayList<>();
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

		palabrasClavesParada = new ArrayList<>();
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
		palabrasClavesLocalComercial = new ArrayList<>();
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
		palabrasClavesCGP = new ArrayList<>();
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

		/*dispositivoTactil.agregarPoi(banco);
		dispositivoTactil.agregarPoi(banco2);
		dispositivoTactil.agregarPoi(parada114);
		dispositivoTactil.agregarPoi(local);
		dispositivoTactil.agregarPoi(cgp);
		 */

		http = new Http("http://private-96b476-ddsutn.apiary-mock.com/banks?banco=banco&servicio=servicio");

	}

	@Test
	public void instanciacionBancoExterno() {
		List<String> listaServicios = new ArrayList<>();
		listaServicios.add("caja de ahorro");
		bancoExterno = new BancoExterno("Banquito", -34.637468, -58.476936, "General Rodriguez", "Jose Lopez",
				listaServicios);
		Assert.assertTrue(bancoExterno.getBanco().equalsIgnoreCase("Banquito"));
	}

	@Test
	public void busquedaExterna() {
		dispositivoTactil.busquedaLibre("Patagonia");
		Mockito.verify(megatron).buscarPois("Patagonia");
	}

	@Test
	public void pruebaAdaptador() {
		Assert.assertTrue(adaptador.adaptarObjetos(listaCentroAAdaptar).get(0).getNombre().equalsIgnoreCase("9"));
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test	
	public void pruebaConvertirJson() throws ParseException, IOException {
		List<Poi> listAux = new ArrayList<>();
		listAux.addAll(optimus.convertirJson(http.obtenerString()));
		Assert.assertEquals("Banco de la Plaza", listAux.get(0).getNombre());
	}
/*	@Test
	public void pruebaConvertirJsonFalla() throws org.apache.http.ParseException, IOException{
		Http falla = new Http("");
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Error IOException");
		thrown.expectMessage("Error ClientProtocolException");
		List<Poi> listAux = new ArrayList<>();
		listAux.addAll(optimus.convertirJson(falla.obtenerString()));
		
	}
	*/
	
}
