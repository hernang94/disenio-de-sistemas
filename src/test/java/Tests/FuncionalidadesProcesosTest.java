package Tests;

import grupo4.Acciones.IdObserver;
import grupo4.Acciones.ObserverAlmacenador;
import grupo4.Acciones.ObserverNotificador;
import grupo4.Acciones.Usuario;
import grupo4.ComponentesExternos.BajaPoiAdapter;
import grupo4.ComponentesExternos.BancoTransformer;
import grupo4.ComponentesExternos.CGPAdapter;
import grupo4.ComponentesExternos.ComponenteBanco;
import grupo4.ComponentesExternos.ComponenteCGPS;
import grupo4.ComponentesExternos.ComponenteLocalComercial;
import grupo4.ComponentesExternos.EmailSender;
import grupo4.ComponentesExternos.Http;
import grupo4.ComponentesExternos.LocalComercialAdapter;
import grupo4.HerramientasExternas.Cache;
import grupo4.HerramientasExternas.Poligono;
import grupo4.HerramientasExternas.Punto;
import grupo4.POIs.Banco;
import grupo4.POIs.CGP;
import grupo4.POIs.Horario;
import grupo4.POIs.LocalComercial;
import grupo4.POIs.Parada;
import grupo4.POIs.Rubro;
import grupo4.POIs.Servicio;
import grupo4.Procesos.AccionAgregarObserver;
import grupo4.Procesos.AccionAltaPalabrasClaves;
import grupo4.Procesos.AccionBajaPoi;
import grupo4.Procesos.AccionQuitarObserver;
import grupo4.Procesos.AdministradorDeProcesos;
import grupo4.Procesos.CriterioATodos;
import grupo4.Procesos.CriterioPorComuna;
import grupo4.Procesos.CriterioPorSeleccion;
import grupo4.Procesos.DecoratorNotificador;
import grupo4.Procesos.DecoratorReintentar;
import grupo4.Procesos.Proceso;
import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.RepositorioDePois;
import grupo4.Repositorios.RepositorioDeResultadosDeEjecucion;
import grupo4.Repositorios.RepositorioDeUsuarios;
import grupo4.Repositorios.ResultadosDeEjecucion;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import DTOexterno.BajaPoiExterna;
import DTOexterno.CentroDTO;
import DTOexterno.RangoServicioDTO;
import DTOexterno.ServicioDTO;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FuncionalidadesProcesosTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
	private List<CentroDTO> listaCentroAAdaptar;
	private RepositorioDePois repoDePois;
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
	private Map<DayOfWeek, Horario> hashMapBanco;
	private Horario horarioBanco;
	private Map<DayOfWeek, Horario> hashMapLocalComercialManiana;
	private Map<DayOfWeek, Horario> hashMapLocalComercialTarde;
	private Map<DayOfWeek, Horario> hashMapServicio;
	private ObserverNotificador notificador;
	private ObserverAlmacenador almacenador;
	private List<String> palabrasClavesBanco;
	private List<String> palabrasClavesCGP;
	private List<String> palabrasClavesParada;
	private List<String> palabrasClavesLocalComercial;
	private Usuario terminal;
	private EmailSender notificadorMail;
	private RepositorioDeBusquedas repositorioBusquedas;
	private ObserverNotificador notificador2;
	private Usuario terminal2;

	// NUEVO
	private AdministradorDeProcesos adminProcesos;
	private Proceso proceso;
	private RepositorioDeResultadosDeEjecucion repoResultadosEjecucion;
	private CriterioATodos criterioTodos;
	private CriterioPorComuna criterioComuna;
	private CriterioPorSeleccion criterioSeleccion;
	private CriterioPorSeleccion criterioSeleccionBad;
	private List<String> listaTerminales;
	private List<String> listaTerminalesNoExistentes;
	private BajaPoiAdapter adaptadorBajaPoiMockeado;
	private BajaPoiAdapter adaptadorBajaPoi;
	private ComponenteLocalComercial componenteLocalComercial;
	private LocalComercialAdapter adaptadorLocalComercialMockeado;
	private LocalComercialAdapter adaptadorLocalComercial;
	private File archivo;
	private Http httpBad;
	private Http http;
	private ObjectMapper objectMapper;
	private AccionAgregarObserver agregarObserver;
	private AccionAltaPalabrasClaves accionPalabras;
	private AccionQuitarObserver quitarObserver;
	private AccionBajaPoi accionBajaPoi;
	private AccionBajaPoi accionBajaPoiConMock;

	private DecoratorReintentar decoratorReintentar;
	private DecoratorNotificador decoratorNotificar;
	
	private String direccion;

	@SuppressWarnings("static-access")
	@Before
	public void init() throws IOException {
		direccion="Direccion de Prueba";
		Cache.getInstancia().activarCache();
		repoDePois = RepositorioDePois.getInstancia();
		notificadorMail = Mockito.mock(EmailSender.class);
		repositorioBusquedas = RepositorioDeBusquedas.getInstancia();

		notificador = new ObserverNotificador(-1, notificadorMail);// tiempoEstipulado=-1

		almacenador = new ObserverAlmacenador();

		listaCentroAAdaptar = new ArrayList<>();

		rangoPrueba = new RangoServicioDTO(DayOfWeek.MONDAY, 9, 0, 18, 0);

		servicioPrueba = new ServicioDTO("Prueba");
		servicioPrueba.agregarRango(rangoPrueba);

		centroPrueba = new CentroDTO(9, "Mataderos,Parque Avellaneda", "Mauro Corvaro", "Calle Falsa 123", "4597-9684");
		centroPrueba.agregarServicio(servicioPrueba);
		listaCentroAAdaptar.add(centroPrueba);

		componente = Mockito.mock(ComponenteCGPS.class);
		adaptador = new CGPAdapter();
		adaptador.setComponente(componente);

		componenteBanco = Mockito.mock(ComponenteBanco.class);
		optimus = new BancoTransformer();
		optimus.setComponente(componenteBanco);
		repoDePois.agregarOrigenExterno(adaptador);
		repoDePois.agregarOrigenExterno(optimus);

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
		banco = new Banco(hashMapBanco, "Santander Rio", direccion,palabrasClavesBanco,new Punto(-34.6409182,-58.4758827));

		palabrasClavesParada = new ArrayList<>();
		palabrasClavesParada.add("Bondi");
		palabrasClavesParada.add("UTN");
		palabrasClavesParada.add("Colectivo");
		palabrasClavesParada.add("Rojo");
		palabrasClavesParada.add("Vidrios polarizados");
		palabrasClavesParada.add("114");
		parada114 = new Parada("114",direccion, palabrasClavesParada,new Punto(-34.6417364,-58.479263));

		rubro = rubro.MUEBLERIA;
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
		local = new LocalComercial(direccion,rubro, hashMapLocalComercialManiana, hashMapLocalComercialTarde, "Blaisten",
				palabrasClavesLocalComercial,new Punto(-34.6383056,-58.4814007));

		banco2 = new Banco(hashMapBanco, "HSBC",direccion, palabrasClavesBanco,new Punto(-34.6383669,-58.4773822));

		hashMapServicio = new HashMap<>();
		hashMapServicio.put(DayOfWeek.THURSDAY, new Horario("12:00", "13:30"));
		hashMapServicio.put(DayOfWeek.FRIDAY, new Horario("12:00", "13:30"));
		List<Punto> coordenadasComuna10 = new ArrayList<>();
		coordenadasComuna10.add(new Punto(-34.637466, -58.476939));
		coordenadasComuna10.add(new Punto(-34.6350677, -58.4810659));
		coordenadasComuna10.add(new Punto(-34.6417364, -58.4792636));
		coordenadasComuna10.add(new Punto(-34.6409182, -58.4758827));
		coordenadasComuna10.add(new Punto(-34.6383056, -58.4814007));
		Poligono comuna10 = new Poligono(coordenadasComuna10);
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
		cgp = new CGP(comuna10, "CGP10",direccion, palabrasClavesCGP,comuna10.getPuntosPoligono().get(0));
		cgp.addServicio(timbrado);

		repoDePois.agregarPoi(banco);
		repoDePois.agregarPoi(banco2);
		repoDePois.agregarPoi(parada114);
		repoDePois.agregarPoi(local);
		repoDePois.agregarPoi(cgp);

		terminal = new Usuario("Terminal Abasto", 10);
		terminal.agregarObserver(notificador);
		terminal.agregarObserver(almacenador);

		terminal2 = new Usuario("Terminal Alto Palermo", 1);
		notificador2 = new ObserverNotificador(1, notificadorMail);
		terminal2.agregarObserver(notificador2);

		// NUEVO

		// CAMBIAR LA RUTA DEL ARCHIVO
		archivo = TestExtensions.readFileFromResources("ArchivoDePrueba.txt");

		componenteLocalComercial = Mockito.mock(ComponenteLocalComercial.class);
		adaptadorLocalComercialMockeado = Mockito.mock(LocalComercialAdapter.class);
		adaptadorLocalComercialMockeado.setComponente(componenteLocalComercial);
		accionPalabras = new AccionAltaPalabrasClaves(adaptadorLocalComercialMockeado, repoDePois);

		httpBad = new Http("http://demo3537367.mockable.io/trash/pois_bad");
		http = new Http("http://demo3537367.mockable.io/trash/pois");

		objectMapper = new ObjectMapper();
		adaptadorBajaPoi = new BajaPoiAdapter(objectMapper);

		accionBajaPoi = new AccionBajaPoi(adaptadorBajaPoi);

		adaptadorBajaPoiMockeado = Mockito.mock(BajaPoiAdapter.class);
		accionBajaPoiConMock = new AccionBajaPoi(adaptadorBajaPoiMockeado);

		adaptadorLocalComercial = new LocalComercialAdapter();
		adaptadorLocalComercial.setComponente(componenteLocalComercial);

		repoResultadosEjecucion = RepositorioDeResultadosDeEjecucion.getInstancia();
		adminProcesos = AdministradorDeProcesos.getInstancia();

		criterioTodos = new CriterioATodos();
		criterioComuna = new CriterioPorComuna(10);
		listaTerminales = new ArrayList<>();
		listaTerminales.add("Terminal Abasto");
		listaTerminales.add("Terminal Alto Palermo");
		criterioSeleccion = new CriterioPorSeleccion(listaTerminales);
		listaTerminalesNoExistentes = new ArrayList<>();
		listaTerminalesNoExistentes.add("Terminal Plaza Miserere");
		criterioSeleccionBad = new CriterioPorSeleccion(listaTerminalesNoExistentes);

		RepositorioDeUsuarios.getInstancia().agregarUsuario(terminal);
		RepositorioDeUsuarios.getInstancia().agregarUsuario(terminal2);

	}

	@After
	public void limpiarSingleton() {
		repoDePois.reset();
		repositorioBusquedas.reset();
		// repoUsuarios.reset();
		repoResultadosEjecucion.getlistaDeResultados().clear();
	}

	@Test
	public void accionBajaPoiMockeada() {
		accionBajaPoiConMock.ejecutar();
		Mockito.verify(adaptadorBajaPoiMockeado).obtenerPoisABajar();
	}

	@Rule
	public ExpectedException thrownPoiNoExiste = ExpectedException.none();

	@Test
	public void accionBajaPoi() throws ParseException, IOException {
		thrownPoiNoExiste.expect(RuntimeException.class);
		thrownPoiNoExiste.expectMessage("No existe el Poi");
		List<BajaPoiExterna> listAux = adaptadorBajaPoi.convertirJson(http.obtenerString());
		listAux.stream().forEach(poiABajar -> accionBajaPoi.bajarPoi(poiABajar));
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void evaluarYBajarPoiJsonBad() throws ParseException, IOException {
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Error 400");
		List<BajaPoiExterna> listAux = adaptadorBajaPoi.convertirJson(httpBad.obtenerString());
		accionBajaPoi.bajarPoi(listAux.stream().findFirst().get());
		Assert.assertEquals(1, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void accionAltaPalabrasClaves() {
		accionPalabras.ejecutar();
		Mockito.verify(adaptadorLocalComercialMockeado).obtenerLocalesExternos();
	}

	@Test
	public void obtenerObjetoLocalDeLocalComercialAdapter() {
		Assert.assertEquals("Carrousel",
				adaptadorLocalComercial.obtenerObjetoLocal("Carrousel;colegio escolar uniformes modas").getNombre());
	}

	@Test
	public void crearListaDeLocalesDeLocalComercialAdapter() throws IOException {
		Assert.assertEquals("Carrousel",
				adaptadorLocalComercial.crearListaDeLocales(archivo).stream().findFirst().get().getNombre());
	}

	@Test
	public void ejecutarProceso() throws Exception {
		agregarObserver = new AccionAgregarObserver(almacenador, criterioTodos);
		adminProcesos.crearProcesoNuevo(agregarObserver, LocalDateTime.now(), 0);
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void agregarAlmacenarATodos() {
		agregarObserver = new AccionAgregarObserver(almacenador, criterioTodos);
		ResultadosDeEjecucion resultado=agregarObserver.ejecutar();
		repoResultadosEjecucion.agregarResultado(resultado);
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void agregarAlmacenarPorComuna() {
		agregarObserver = new AccionAgregarObserver(almacenador, criterioComuna);
		ResultadosDeEjecucion resultado=agregarObserver.ejecutar();
		repoResultadosEjecucion.agregarResultado(resultado);
		Assert.assertEquals(1, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void agregarAlmacenarSeleccion() {
		agregarObserver = new AccionAgregarObserver(almacenador, criterioSeleccion);
		ResultadosDeEjecucion resultado=agregarObserver.ejecutar();
		repoResultadosEjecucion.agregarResultado(resultado);
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void procesoAgregarAlmacenarTodos() {
		agregarObserver = new AccionAgregarObserver(almacenador, criterioTodos);
		proceso = new Proceso(LocalDateTime.now(), 0, agregarObserver);
		proceso.run();
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void ejecutarProcesoPeriodicidadMayorCero() throws Exception {
		agregarObserver = new AccionAgregarObserver(almacenador, criterioTodos);
		adminProcesos.crearProcesoNuevo(agregarObserver, LocalDateTime.now(), 5);
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void quitarAlmacenarATodos() {
		quitarObserver = new AccionQuitarObserver(criterioTodos, almacenador.getId());
		agregarObserver = new AccionAgregarObserver(almacenador, criterioTodos);
		ResultadosDeEjecucion resultado1=agregarObserver.ejecutar();
		ResultadosDeEjecucion resultado2=quitarObserver.ejecutar();
		repoResultadosEjecucion.agregarResultado(resultado1);
		repoResultadosEjecucion.agregarResultado(resultado2);
		Assert.assertTrue(repoResultadosEjecucion.getlistaDeResultados().stream()
				.allMatch(x -> (x.getCantidadDeElementosAfectados()) == 2));
	}

	@Test
	public void quitarAlmacenarPorComuna() {
		quitarObserver = new AccionQuitarObserver(criterioComuna, IdObserver.ALMACENADOR);
		agregarObserver = new AccionAgregarObserver(almacenador, criterioComuna);
		ResultadosDeEjecucion resultado1=agregarObserver.ejecutar();
		ResultadosDeEjecucion resultado2=quitarObserver.ejecutar();
		repoResultadosEjecucion.agregarResultado(resultado1);
		repoResultadosEjecucion.agregarResultado(resultado2);
		Assert.assertTrue(repoResultadosEjecucion.getlistaDeResultados().stream()
				.allMatch(x -> (x.getCantidadDeElementosAfectados()) == 1));

	}

	@Test
	public void quitarAlmacenarSeleccion() {
		quitarObserver = new AccionQuitarObserver(criterioSeleccion, almacenador.getId());
		agregarObserver = new AccionAgregarObserver(almacenador, criterioSeleccion);
		ResultadosDeEjecucion resultado1=agregarObserver.ejecutar();
		ResultadosDeEjecucion resultado2=quitarObserver.ejecutar();
		repoResultadosEjecucion.agregarResultado(resultado1);
		repoResultadosEjecucion.agregarResultado(resultado2);
		Assert.assertTrue(repoResultadosEjecucion.getlistaDeResultados().stream()
				.allMatch(x -> (x.getCantidadDeElementosAfectados()) == 2));
	}

	@Test
	public void agregarNotificarATodos() {
		agregarObserver = new AccionAgregarObserver(notificador, criterioTodos);
		ResultadosDeEjecucion resultado=agregarObserver.ejecutar();
		repoResultadosEjecucion.agregarResultado(resultado);
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void agregarNotificarPorComuna() {
		agregarObserver = new AccionAgregarObserver(notificador, criterioComuna);
		ResultadosDeEjecucion resultado=agregarObserver.ejecutar();
		repoResultadosEjecucion.agregarResultado(resultado);
		Assert.assertEquals(1, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void agregarNotificarSeleccion() {
		agregarObserver = new AccionAgregarObserver(notificador, criterioSeleccion);
		ResultadosDeEjecucion resultado=agregarObserver.ejecutar();
		repoResultadosEjecucion.agregarResultado(resultado);
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void decoratorReintentarEjecutar() {
		agregarObserver = new AccionAgregarObserver(notificador, criterioTodos);
		decoratorReintentar = new DecoratorReintentar(2, agregarObserver);
		ResultadosDeEjecucion resultado=decoratorReintentar.ejecutar();
		repoResultadosEjecucion.agregarResultado(resultado);
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void decoratorNotificarEjecutar() {
		agregarObserver = new AccionAgregarObserver(notificador, criterioTodos);
		decoratorNotificar = new DecoratorNotificador(agregarObserver, notificadorMail);
		ResultadosDeEjecucion resultado=decoratorNotificar.ejecutar();
		repoResultadosEjecucion.agregarResultado(resultado);
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Rule
	public ExpectedException thrownReintento = ExpectedException.none();

	@Test
	public void decoratorReintentarFalla() throws Exception {
		RepositorioDeUsuarios.getInstancia().quitarUsuario(terminal);
		agregarObserver = new AccionAgregarObserver(notificador, criterioSeleccion);
		decoratorReintentar = new DecoratorReintentar(2, agregarObserver);
		thrownReintento.expect(RuntimeException.class);
		thrownReintento.expectMessage("Se supero la cantidad de Reintentos y el proceso fallo");
		decoratorReintentar.ejecutar();
	}

	@Test
	public void decoratorNotificaFalla() throws Exception {
		agregarObserver = new AccionAgregarObserver(notificador, criterioSeleccionBad);
		decoratorNotificar = new DecoratorNotificador(agregarObserver, notificadorMail);
		decoratorNotificar.ejecutar();
		Mockito.verify(notificadorMail).enviarMail("Fallo el sistema");
	}
}
