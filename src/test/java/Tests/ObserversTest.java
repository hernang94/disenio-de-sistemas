package Tests;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import DTOexterno.CentroDTO;
import DTOexterno.RangoServicioDTO;
import DTOexterno.ServicioDTO;
import grupo4.Acciones.ObserverAlmacenador;
import grupo4.Acciones.ObserverNotificador;
import grupo4.Acciones.ObserverReporterParcial;
import grupo4.Acciones.ObserverReporterPorFecha;
import grupo4.Acciones.ObserverReporterTotal;
import grupo4.Acciones.Usuario;
import grupo4.ComponentesExternos.BancoTransformer;
import grupo4.ComponentesExternos.CGPAdapter;
import grupo4.ComponentesExternos.ComponenteBanco;
import grupo4.ComponentesExternos.ComponenteCGPS;
import grupo4.ComponentesExternos.EmailSender;
import grupo4.HerramientasExternas.Poligono;
import grupo4.HerramientasExternas.Punto;
import grupo4.POIs.Banco;
import grupo4.POIs.CGP;
import grupo4.POIs.Horario;
import grupo4.POIs.LocalComercial;
import grupo4.POIs.Parada;
import grupo4.POIs.Rubro;
import grupo4.POIs.Servicio;
import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.RepositorioDePois;
import grupo4.Repositorios.ResultadoDeBusqueda;

public class ObserversTest extends AbstractPersistenceTest implements WithGlobalEntityManager{
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
	private ObserverReporterParcial reporterParcial;
	private ObserverReporterPorFecha reporterPorFecha;
	private ObserverReporterTotal reporterTotal;
	private ObserverAlmacenador almacenador;
	private List<String> palabrasClavesBanco;
	private List<String> palabrasClavesCGP;
	private List<String> palabrasClavesParada;
	private List<String> palabrasClavesLocalComercial;
	private Usuario terminal;
	private EmailSender notificadorMail;
	private RepositorioDeBusquedas repositorioBusquedas;
	private ObserverNotificador notificadorFalla;
	private Usuario terminalFalla;
	private EntityManager em;
	private ResultadoDeBusqueda resultadoPrueba;
	@SuppressWarnings("static-access")

	@Before
	public void init() {
		em=PerThreadEntityManagers.getEntityManager();
		repoDePois = RepositorioDePois.getInstancia();
		notificadorMail = Mockito.mock(EmailSender.class);
		repositorioBusquedas = RepositorioDeBusquedas.getInstancia();

		notificador = new ObserverNotificador(-1, notificadorMail);// tiempoEstipulado=-1
		reporterParcial = new ObserverReporterParcial();
		reporterPorFecha = new ObserverReporterPorFecha();
		reporterTotal = new ObserverReporterTotal();
		
		
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
		banco = new Banco(hashMapBanco, "Santander Rio", palabrasClavesBanco);
		banco.setX(-34.6409182);
		banco.setY(-58.4758827);

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
		local = new LocalComercial(rubro, hashMapLocalComercialManiana, hashMapLocalComercialTarde, "Blaisten",
				palabrasClavesLocalComercial);
		local.setX(-34.6383056);
		local.setY(-58.4814007);

		banco2 = new Banco(hashMapBanco, "HSBC", palabrasClavesBanco);
		banco2.setX(-34.6383669);
		banco2.setY(-58.4773822);

		hashMapServicio = new HashMap<>();
		hashMapServicio.put(DayOfWeek.THURSDAY, new Horario("12:00", "13:30"));
		hashMapServicio.put(DayOfWeek.FRIDAY, new Horario("12:00", "13:30"));
		Poligono comuna10 = new Poligono();
		comuna10.add(new Punto(-34.637466, -58.476939));
		comuna10.add(new Punto(-34.6350677, -58.4810659));
		comuna10.add(new Punto(-34.6417364, -58.4792636));
		comuna10.add(new Punto(-34.6409182, -58.4758827));
		comuna10.add(new Punto(-34.6383056, -58.4814007));
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

		repoDePois.agregarPoi(banco);
		/*repoDePois.agregarPoi(banco2);
		repoDePois.agregarPoi(parada114);
		repoDePois.agregarPoi(local);
		repoDePois.agregarPoi(cgp);*/
		terminal = new Usuario("Terminal Abasto",10);
		terminal.agregarObserver(notificador);
		terminal.agregarObserver(reporterPorFecha);
		terminal.agregarObserver(reporterTotal);
		terminal.agregarObserver(reporterParcial);

		terminal.agregarObserver(almacenador);

		terminalFalla = new Usuario("Terminal Abasto",1);
		notificadorFalla = new ObserverNotificador(1, notificadorMail);
		terminalFalla.agregarObserver(notificadorFalla);

		resultadoPrueba= new ResultadoDeBusqueda("Terminal Abasto", 5, "Hola que hace?", LocalDate.now(), 3);
		em.persist(resultadoPrueba);
		
	}

	@After
	public void limpiarSingleton() {
		RepositorioDePois.getInstancia().reset();
		RepositorioDeBusquedas.getInstancia().reset();
	}

	@Test
	public void notificadorArministrador() {
		terminal.busquedaLibre("HSBC");
		Mockito.verify(notificadorMail).enviarMail("Tiempo de busqueda mayor al estipulado");
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void notificadorAdministradorFalla() {
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("Tiempo de busqueda menor al estipulado");
		terminalFalla.busquedaLibre("HSBC");

	}

	@Test
	public void calcularDiferencia() {
		Assert.assertEquals(10, terminal.calcularDiferencia(LocalDateTime.of(2016, 06, 05, 18, 15, 10),
				LocalDateTime.of(2016, 06, 05, 18, 15, 20)));
	}

	@Test
	public void reportarCantidadBusquedas() {
		terminal.busquedaLibre("muebles");
		terminal.busquedaLibre("Blaisten");
		Assert.assertEquals(2, repositorioBusquedas.getListaFechaCant(terminal.getTerminal()).get(0).getCantidad());
	}

	@Test
	public void reporteParcialesporTerminal() {
		terminal.busquedaLibre("Blaisten");
		Assert.assertTrue(repositorioBusquedas.getlistaBusquedas(terminal.getTerminal()).contains(1));
	}

	@Test
	public void reporteTotal() {
		terminal.busquedaLibre("Blaisten");
		terminal.busquedaLibre("muebles");
		Assert.assertTrue(2 == repositorioBusquedas.reporteTotal().get(terminal.getTerminal()));
	}

	@Test
	public void quitarObserver() {
		terminal.quitarObserver(reporterParcial);
		Assert.assertEquals(4, terminal.getObservers().size());
	}
}
