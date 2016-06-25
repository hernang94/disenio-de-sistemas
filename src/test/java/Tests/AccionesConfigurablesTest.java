package Tests;


import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import DTOexterno.CentroDTO;
import DTOexterno.RangoServicioDTO;
import DTOexterno.ServicioDTO;
import grupo4.Acciones.AlmacenadorDeBusquedas;
import grupo4.Acciones.ObserverNotificador;
import grupo4.Acciones.ObserverReporter;
import grupo4.ComponentesExternos.BancoTransformer;
import grupo4.ComponentesExternos.CGPAdapter;
import grupo4.ComponentesExternos.ComponenteBanco;
import grupo4.ComponentesExternos.ComponenteCGPS;
import grupo4.POIs.Banco;
import grupo4.POIs.CGP;
import grupo4.POIs.Horario;
import grupo4.POIs.LocalComercial;
import grupo4.POIs.Parada;
import grupo4.POIs.Rubro;
import grupo4.POIs.Servicio;
import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.RepositorioDePois;
import grupo4.Repositorios.RepositorioDeTerminales;

public class AccionesConfigurablesTest {
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
	private Map<Integer,Horario> hashMapBanco;
	private Horario horarioBanco;
	private LocalDateTime fechaAux;
	private Map<Integer,Horario> hashMapLocalComercialManiana;
	private Map<Integer,Horario> hashMapLocalComercialTarde;
	private Map<Integer,Horario> hashMapServicio; 
	private RepositorioDeTerminales repo;
	private PrintWriter writer;
	private ObserverNotificador notificador;
	private ObserverReporter reporter;
	private AlmacenadorDeBusquedas almacenador;
	private RepositorioDeBusquedas almacen;
	private List<String> palabrasClavesBanco;
	private List<String> palabrasClavesCGP;
	private List<String> palabrasClavesParada;
	private List<String> palabrasClavesLocalComercial;
	@SuppressWarnings("static-access")
	
	@Before
	public void init() {
		almacen= new RepositorioDeBusquedas();
		notificador=new ObserverNotificador();
		reporter=new ObserverReporter(almacen,writer);
		almacenador= new AlmacenadorDeBusquedas(almacen);
		
		listaCentroAAdaptar=new ArrayList<>();
		
		rangoPrueba= new RangoServicioDTO(1,9,0,18,0);
		
		servicioPrueba=new ServicioDTO("Prueba");
		servicioPrueba.agregarRango(rangoPrueba);
		
		writer=Mockito.mock(PrintWriter.class);
		
		centroPrueba= new CentroDTO(9, "Mataderos,Parque Avellaneda", "Mauro Corvaro", "Calle Falsa 123", "4597-9684");
		centroPrueba.agregarServicio(servicioPrueba);
		listaCentroAAdaptar.add(centroPrueba);
		
		componente = Mockito.mock(ComponenteCGPS.class);
		adaptador = new CGPAdapter();
		adaptador.setComponente(componente);
		
		
		componenteBanco=Mockito.mock(ComponenteBanco.class);
		optimus = new BancoTransformer();
		optimus.setComponente(componenteBanco);
		
		dispositivoTactil = new RepositorioDePois("terminalAbasto",-1,writer);
		dispositivoTactil.agregarAdaptador(adaptador);
		dispositivoTactil.agregarAdaptador(optimus);
		dispositivoTactil.agregarObserver(notificador);
		dispositivoTactil.agregarObserver(reporter);
		dispositivoTactil.agregarObserver(almacenador);	
		horarioBanco= new Horario("10:00", "15:00");
		
		fechaAux= LocalDateTime.now();
		
		hashMapBanco = new HashMap<>();
		hashMapBanco.put(fechaAux.getDayOfWeek().MONDAY.getValue(), horarioBanco);
		hashMapBanco.put(fechaAux.getDayOfWeek().TUESDAY.getValue(), horarioBanco);
		hashMapBanco.put(fechaAux.getDayOfWeek().WEDNESDAY.getValue(), horarioBanco);
		hashMapBanco.put(fechaAux.getDayOfWeek().THURSDAY.getValue(), horarioBanco);
		hashMapBanco.put(fechaAux.getDayOfWeek().FRIDAY.getValue(), horarioBanco);
		palabrasClavesBanco=new ArrayList<>();
		palabrasClavesBanco.add("Santander");
		palabrasClavesBanco.add("Rio");
		palabrasClavesBanco.add("Fantino");
		palabrasClavesBanco.add("Banco");
		palabrasClavesBanco.add("Rojo");
		palabrasClavesBanco.add("Prestamo");
		palabrasClavesBanco.add("Cuenta corriente");
		palabrasClavesBanco.add("Cajero");
		banco= new Banco(hashMapBanco, "Santander Rio",palabrasClavesBanco);
		banco.setX(-34.6409182);
		banco.setY(-58.4758827);
		banco.setCoordenadas();

		palabrasClavesParada=new ArrayList<>();
		palabrasClavesParada.add("Bondi");
		palabrasClavesParada.add("UTN");
		palabrasClavesParada.add("Colectivo");
		palabrasClavesParada.add("Rojo");
		palabrasClavesParada.add("Vidrios polarizados");
		palabrasClavesParada.add("114");
		parada114 = new Parada("114",palabrasClavesParada);
		parada114.setX(-34.6417364);
		parada114.setY(-58.4792636);
		parada114.setCoordenadas();

		rubro = rubro.MUEBLERIA;
		//local = new LocalComercial(rubro, "09:00", "13:00", "14:00", "18:00", 1, 6);
		hashMapLocalComercialManiana=new HashMap<>();
		hashMapLocalComercialManiana.put(fechaAux.getDayOfWeek().MONDAY.getValue(), new Horario("09:00", "13:00"));
		hashMapLocalComercialManiana.put(fechaAux.getDayOfWeek().TUESDAY.getValue(), new Horario("09:00", "13:00"));
		hashMapLocalComercialManiana.put(fechaAux.getDayOfWeek().WEDNESDAY.getValue(), new Horario("09:00", "13:00"));
		hashMapLocalComercialManiana.put(fechaAux.getDayOfWeek().THURSDAY.getValue(), new Horario("09:00", "13:00"));
		hashMapLocalComercialTarde=new HashMap<>();
		hashMapLocalComercialTarde.put(fechaAux.getDayOfWeek().MONDAY.getValue(), new Horario("14:00", "18:00"));
		hashMapLocalComercialTarde.put(fechaAux.getDayOfWeek().TUESDAY.getValue(), new Horario("14:00", "20:00"));
		hashMapLocalComercialTarde.put(fechaAux.getDayOfWeek().THURSDAY.getValue(), new Horario("14:00", "19:00"));
		palabrasClavesLocalComercial=new ArrayList<>();
		palabrasClavesLocalComercial.add("Muebles");
		palabrasClavesLocalComercial.add("Madera");
		palabrasClavesLocalComercial.add("Remaches");
		palabrasClavesLocalComercial.add("Carpintero");
		palabrasClavesLocalComercial.add("Mesa");
		palabrasClavesLocalComercial.add("Silla");
		local = new LocalComercial(rubro,hashMapLocalComercialManiana,hashMapLocalComercialTarde,"Blaisten",palabrasClavesLocalComercial);
		local.setX(-34.6383056);
		local.setY(-58.4814007);
		local.setCoordenadas();
		
		banco2= new Banco(hashMapBanco, "HSBC",palabrasClavesBanco);
		banco2.setX(-34.6383669);
		banco2.setY(-58.4773822);
		banco2.setCoordenadas();		

		hashMapServicio= new HashMap<>();
		hashMapServicio.put(fechaAux.getDayOfWeek().THURSDAY.getValue(), new Horario("12:00", "13:30"));
		hashMapServicio.put(fechaAux.getDayOfWeek().FRIDAY.getValue(), new Horario("12:00", "13:30"));
		Polygon comuna10 = new Polygon();
		comuna10.add(new Point(-34.637466, -58.476939));
		comuna10.add(new Point(-34.6350677, -58.4810659));
		comuna10.add(new Point(-34.6417364, -58.4792636));
		comuna10.add(new Point(-34.6409182, -58.4758827));
		comuna10.add(new Point(-34.6383056, -58.4814007));
		timbrado = new Servicio("timbrado",hashMapServicio);
		palabrasClavesCGP=new ArrayList<>();
		palabrasClavesCGP.add("10");
		palabrasClavesCGP.add("Floresta");
		palabrasClavesCGP.add("Monte Castro");
		palabrasClavesCGP.add("Velez");
		palabrasClavesCGP.add("Versalles");
		palabrasClavesCGP.add("Villa Luro");
		palabrasClavesCGP.add("Villa Real");
		palabrasClavesCGP.add("All Boys");
		cgp = new CGP(comuna10,"CGP10",palabrasClavesCGP);
		cgp.addServicio(timbrado);

		dispositivoTactil.agregarPoi(banco);
		dispositivoTactil.agregarPoi(banco2);
		dispositivoTactil.agregarPoi(parada114);
		dispositivoTactil.agregarPoi(local);
		dispositivoTactil.agregarPoi(cgp);
		
		repo=new RepositorioDeTerminales(writer);
		repo.agregarTerminal(dispositivoTactil);
	}
	
	
	@Test
	public void notificadorArministrador(){
		dispositivoTactil.busquedaLibre("HSBC");
		Mockito.verify(writer).println("Mail enviado al adminisitrador");
	}
	
	@Test
	public void calcularDiferencia(){
		Assert.assertEquals(10, dispositivoTactil.calcularDiferencia(LocalDateTime.of(2016, 06, 05, 18, 15, 10), LocalDateTime.of(2016, 06, 05, 18, 15, 20)));
	}
	
	/*@Test
	public void reportarCantidadBusquedas(){
		dispositivoTactil.busquedaLibre("muebleria");
		dispositivoTactil.obtenerReporteTotalPorFecha();
		Mockito.verify(writer).println("Fecha\t\tCantidad Total");	
	}
	
	@Test
	public void reportePorTerminal(){
		dispositivoTactil.busquedaLibre("muebleria");
		repo.reporteTotalporTerminal();
		Mockito.verify(writer).println("Usuario\t\tCantidad Resultados Totales");
	}
	
	@Test
	public void reporteParcialesporTerminal(){
		dispositivoTactil.busquedaLibre("muebleria");
		repo.reporteParcialporTerminal();
		Mockito.verify(writer).println("Usuario: terminalAbasto");
	}*/
}
