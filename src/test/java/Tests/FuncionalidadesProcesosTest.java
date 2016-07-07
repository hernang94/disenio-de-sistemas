package Tests;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import DTOexterno.CentroDTO;
import DTOexterno.RangoServicioDTO;
import DTOexterno.ServicioDTO;
import grupo4.Acciones.ObserverAlmacenador;
import grupo4.Acciones.ObserverNotificador;
import grupo4.Acciones.ObserverReporter;
import grupo4.Acciones.Usuario;
import grupo4.ComponentesExternos.BancoTransformer;
import grupo4.ComponentesExternos.CGPAdapter;
import grupo4.ComponentesExternos.ComponenteBanco;
import grupo4.ComponentesExternos.ComponenteCGPS;
import grupo4.ComponentesExternos.EmailSender;
import grupo4.POIs.Banco;
import grupo4.POIs.CGP;
import grupo4.POIs.Horario;
import grupo4.POIs.LocalComercial;
import grupo4.POIs.Parada;
import grupo4.POIs.Rubro;
import grupo4.POIs.Servicio;
import grupo4.Procesos.AccionBajaPoi;
import grupo4.Procesos.AdministradorDeProcesos;
import grupo4.Procesos.AgregarAlmacenar;
import grupo4.Procesos.AgregarNotificar;
import grupo4.Procesos.AgregarReportar;
import grupo4.Procesos.CriterioATodos;
import grupo4.Procesos.CriterioPorComuna;
import grupo4.Procesos.CriterioPorSeleccion;
import grupo4.Procesos.Proceso;
import grupo4.Procesos.QuitarAlmacenar;
import grupo4.Procesos.QuitarNotificar;
import grupo4.Procesos.QuitarReportar;
import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.RepositorioDePois;
import grupo4.Repositorios.RepositorioDeResultadosDeEjecucion;
import grupo4.Repositorios.RepositorioDeUsuarios;

public class FuncionalidadesProcesosTest {
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
	private ObserverReporter reporter;
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

	private AdministradorDeProcesos adminProcesos;
	private Proceso proceso;
	private Proceso proceso2;
	private AccionBajaPoi accionBajaPoi;
	private RepositorioDeResultadosDeEjecucion repoResultadosEjecucion;
	private CriterioATodos criterioTodos;
	private CriterioPorComuna criterioComuna;
	private CriterioPorSeleccion criterioSeleccion;
	private AgregarAlmacenar agregarAlmacenarTodos;
	private AgregarAlmacenar agregarAlmacenarComuna;
	private AgregarAlmacenar agregarAlmacenarSeleccion;
	private QuitarAlmacenar quitarAlmacenarTodos;
	private QuitarAlmacenar quitarAlmacenarComuna;
	private QuitarAlmacenar quitarAlmacenarSeleccion;
	private AgregarNotificar agregarNotificarTodos;
	private AgregarNotificar agregarNotificarComuna;
	private AgregarNotificar agregarNotificarSeleccion;
	private QuitarNotificar quitarNotificarTodos;
	private QuitarNotificar quitarNotificarComuna;
	private QuitarNotificar quitarNotificarSeleccion;
	private AgregarReportar agregarReportarTodos;
	private AgregarReportar agregarReportarComuna;
	private AgregarReportar agregarReportarSeleccion;
	private QuitarReportar quitarReportarTodos;
	private QuitarReportar quitarReportarComuna;
	private QuitarReportar quitarReportarSeleccion;
	private RepositorioDeUsuarios repoUsuarios;
	private List<String> listaTerminales;

	@SuppressWarnings("static-access")
	@Before
	public void init() {
		repoDePois = RepositorioDePois.getInstancia();
		notificadorMail = Mockito.mock(EmailSender.class);
		repositorioBusquedas = RepositorioDeBusquedas.getInstancia();

		notificador = new ObserverNotificador(-1, notificadorMail);// tiempoEstipulado=-1
		reporter = new ObserverReporter(repositorioBusquedas);
		almacenador = new ObserverAlmacenador(repositorioBusquedas);

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
		repoDePois.agregarAdaptador(adaptador);
		repoDePois.agregarAdaptador(optimus);

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
		banco = new Banco(10, hashMapBanco, "Santander Rio", palabrasClavesBanco);
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
		parada114 = new Parada(20, "114", palabrasClavesParada);
		parada114.setX(-34.6417364);
		parada114.setY(-58.4792636);
		parada114.setCoordenadas();

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
		local = new LocalComercial(40, rubro, hashMapLocalComercialManiana, hashMapLocalComercialTarde, "Blaisten",
				palabrasClavesLocalComercial);
		local.setX(-34.6383056);
		local.setY(-58.4814007);
		local.setCoordenadas();

		banco2 = new Banco(30, hashMapBanco, "HSBC", palabrasClavesBanco);
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
		cgp = new CGP(50, comuna10, "CGP10", palabrasClavesCGP);
		cgp.addServicio(timbrado);

		repoDePois.agregarPoi(banco);
		repoDePois.agregarPoi(banco2);
		repoDePois.agregarPoi(parada114);
		repoDePois.agregarPoi(local);
		repoDePois.agregarPoi(cgp);

		terminal = new Usuario("Terminal Abasto", repoDePois, 10);
		terminal.agregarObserver(notificador);
		terminal.agregarObserver(reporter);
		terminal.agregarObserver(almacenador);

		terminal2 = new Usuario("Terminal Alto Palermo", repoDePois, 1);
		notificador2 = new ObserverNotificador(1, notificadorMail);
		terminal2.agregarObserver(notificador2);

		accionBajaPoi = new AccionBajaPoi(repoDePois);

		repoResultadosEjecucion = RepositorioDeResultadosDeEjecucion.getInstancia();
		repoUsuarios = RepositorioDeUsuarios.getInstancia();
		adminProcesos = AdministradorDeProcesos.getInstancia();

		criterioTodos = new CriterioATodos();
		criterioComuna = new CriterioPorComuna(10);
		listaTerminales = new ArrayList<>();
		listaTerminales.add("Terminal Abasto");
		listaTerminales.add("Terminal Alto Palermo");
		criterioSeleccion = new CriterioPorSeleccion(listaTerminales);

		agregarAlmacenarTodos = new AgregarAlmacenar(criterioTodos);
		agregarAlmacenarComuna = new AgregarAlmacenar(criterioComuna);
		agregarAlmacenarSeleccion = new AgregarAlmacenar(criterioSeleccion);
		quitarAlmacenarTodos = new QuitarAlmacenar(criterioTodos);
		quitarAlmacenarComuna = new QuitarAlmacenar(criterioComuna);
		quitarAlmacenarSeleccion = new QuitarAlmacenar(criterioSeleccion);

		repoUsuarios.agregarUsuario(terminal);
		repoUsuarios.agregarUsuario(terminal2);
		
		agregarNotificarTodos = new AgregarNotificar(criterioTodos,5,notificadorMail);
		agregarNotificarComuna = new AgregarNotificar(criterioComuna,10,notificadorMail);
		agregarNotificarSeleccion = new AgregarNotificar(criterioSeleccion,0,notificadorMail);
		quitarNotificarTodos = new QuitarNotificar(criterioTodos);
		quitarNotificarComuna = new QuitarNotificar(criterioComuna);
		quitarNotificarSeleccion = new QuitarNotificar(criterioSeleccion);

		agregarReportarTodos = new AgregarReportar(criterioTodos);
		agregarReportarComuna = new AgregarReportar(criterioComuna);
		agregarReportarSeleccion = new AgregarReportar(criterioSeleccion);
		quitarReportarTodos = new QuitarReportar(criterioTodos);
		quitarReportarComuna = new QuitarReportar(criterioComuna);
		quitarReportarSeleccion = new QuitarReportar(criterioSeleccion);
	}

	@After
	public void limpiarSingleton() {
		repoDePois.reset();
		repositorioBusquedas.reset();
		repoUsuarios.reset();
		repoResultadosEjecucion.getlistaDeResultados().clear();
	}

	@Test
	public void ejecutarProceso() throws InterruptedException{
		adminProcesos.crearProcesoNuevo(agregarAlmacenarTodos, LocalDateTime.of(2016, 07, 07, 15, 51), 0);
		//proceso = new Proceso(LocalDateTime.of(2016, 07, 07, 15, 47), 0, agregarAlmacenarTodos);
		//adminProcesos.ejecutarProceso(proceso);
		Thread.sleep(60000);
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}
	
	@Test
	public void agregarAlmacenarATodos() {
		agregarAlmacenarTodos.ejecutar();
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void agregarAlmacenarPorComuna() {
		agregarAlmacenarComuna.ejecutar();
		Assert.assertEquals(1, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void agregarAlmacenarSeleccion() {
		agregarAlmacenarSeleccion.ejecutar();
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}
	
	@Test
	public void procesoAgregarAlmacenarTodos() {
		proceso = new Proceso(LocalDateTime.now(), 0, agregarAlmacenarTodos);
		proceso.ejecutar();
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void quitarAlmacenarATodos() {
		agregarAlmacenarTodos.ejecutar();
		quitarAlmacenarTodos.ejecutar();
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void quitarAlmacenarPorComuna() {
		agregarAlmacenarComuna.ejecutar();
		quitarAlmacenarComuna.ejecutar();
		Assert.assertEquals(1, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void quitarAlmacenarSeleccion() {
		agregarAlmacenarSeleccion.ejecutar();
		quitarAlmacenarSeleccion.ejecutar();
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}
	
	@Test
	public void procesoQuitarAlmacenarTodos() {
		proceso = new Proceso(LocalDateTime.now(), 0, agregarAlmacenarTodos);
		proceso.ejecutar();
		proceso2 = new Proceso(LocalDateTime.now(), 0, quitarAlmacenarTodos);
		proceso2.ejecutar();
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}
	
	@Test
	public void agregarNotificarATodos() {
		agregarNotificarTodos.ejecutar();
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void agregarNotificarPorComuna() {
		agregarNotificarComuna.ejecutar();
		Assert.assertEquals(1, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void agregarNotificarSeleccion() {
		agregarNotificarSeleccion.ejecutar();
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}
	
	@Test
	public void quitarNotificarATodos() {
		agregarNotificarTodos.ejecutar();
		quitarNotificarTodos.ejecutar();
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void quitarNotificarPorComuna() {
		agregarNotificarComuna.ejecutar();
		quitarNotificarComuna.ejecutar();
		Assert.assertEquals(1, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void quitarNotificarSeleccion() {
		agregarNotificarSeleccion.ejecutar();
		quitarNotificarSeleccion.ejecutar();
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void agregarReportarATodos() {
		agregarReportarTodos.ejecutar();
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void agregarReportarPorComuna() {
		agregarReportarComuna.ejecutar();
		Assert.assertEquals(1, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void agregarReportarSeleccion() {
		agregarReportarSeleccion.ejecutar();
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}
	
	@Test
	public void quitarReportarATodos() {
		agregarReportarTodos.ejecutar();
		quitarReportarTodos.ejecutar();
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void quitarReportarPorComuna() {
		agregarReportarComuna.ejecutar();
		quitarReportarComuna.ejecutar();
		Assert.assertEquals(1, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}

	@Test
	public void quitarReportarSeleccion() {
		agregarReportarSeleccion.ejecutar();
		quitarReportarSeleccion.ejecutar();
		Assert.assertEquals(2, repoResultadosEjecucion.getlistaDeResultados().stream().findFirst().get()
				.getCantidadDeElementosAfectados());
	}
	
}
