package Tests;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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

import externo.CentroDTO;
import externo.RangoServicioDTO;
import externo.ServicioDTO;

import grupo4.Banco;
import grupo4.BancoTransformer;
import grupo4.CGP;
import grupo4.CGPAdapter;
import grupo4.ComponenteBanco;
import grupo4.ComponenteCGPS;
import grupo4.Horario;
import grupo4.LocalComercial;
import grupo4.Parada;
import grupo4.Poi;
import grupo4.Rubro;
import grupo4.Servicio;
import grupo4.RepositorioDePois;
import grupo4.RepositorioDeTerminales;

public class Tests {
	private List<CentroDTO> listaCentroAAdaptar;
	private RepositorioDePois dispositivoTactil;
	private Parada parada114;
	private Servicio pagoFacil;
	private Servicio timbrado;
	private CentroDTO centroPrueba;
	private ServicioDTO servicioPrueba;
	private RangoServicioDTO rangoPrueba;
	private Banco banco;
	private Banco banco2;
	private CGP cgp;
	private LocalComercial local;
	private Point unPuntoABuscar;
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
	@SuppressWarnings("static-access")
	
	@Before
	public void init() {
		listaCentroAAdaptar=new ArrayList<>();
		
		
		rangoPrueba= new RangoServicioDTO(1,9,0,18,0);
		
		servicioPrueba=new ServicioDTO("Prueba");
		servicioPrueba.agregarRango(rangoPrueba);
		
		
		centroPrueba= new CentroDTO(9, "Mataderos,Parque Avellaneda", "Mauro Corvaro", "Calle Falsa 123", "4597-9684");
		centroPrueba.agregarServicio(servicioPrueba);
		listaCentroAAdaptar.add(centroPrueba);
		
		componente = Mockito.mock(ComponenteCGPS.class);
		adaptador = new CGPAdapter();
		adaptador.setComponente(componente);
		
		
		componenteBanco=Mockito.mock(ComponenteBanco.class);
		optimus = new BancoTransformer();
		optimus.setComponente(componenteBanco);
		
		unPuntoABuscar = new Point(-34.638116, -58.4794967);
		dispositivoTactil = new RepositorioDePois("terminalAbasto",0);
		dispositivoTactil.agregarAdaptador(adaptador);
		dispositivoTactil.agregarAdaptador(optimus);
		
		horarioBanco= new Horario("10:00", "15:00");
		
		fechaAux= LocalDateTime.now();
		
		hashMapBanco = new HashMap<>();
		hashMapBanco.put(fechaAux.getDayOfWeek().MONDAY.getValue(), horarioBanco);
		hashMapBanco.put(fechaAux.getDayOfWeek().TUESDAY.getValue(), horarioBanco);
		hashMapBanco.put(fechaAux.getDayOfWeek().WEDNESDAY.getValue(), horarioBanco);
		hashMapBanco.put(fechaAux.getDayOfWeek().THURSDAY.getValue(), horarioBanco);
		hashMapBanco.put(fechaAux.getDayOfWeek().FRIDAY.getValue(), horarioBanco);
		banco= new Banco(hashMapBanco, "Santander Rio");
		banco.setX(-34.6409182);
		banco.setY(-58.4758827);
		banco.setCoordenadas();

		parada114 = new Parada("114");
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
		local = new LocalComercial(rubro,hashMapLocalComercialManiana,hashMapLocalComercialTarde,"Blaisten");
		local.setX(-34.6383056);
		local.setY(-58.4814007);
		local.setCoordenadas();
		
		banco2 = new Banco (hashMapBanco,"Santander Rio");
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
		cgp = new CGP(comuna10,"CGP10");
		cgp.addServicio(timbrado);

		dispositivoTactil.agregarPoi(banco);
		dispositivoTactil.agregarPoi(banco2);
		dispositivoTactil.agregarPoi(parada114);
		dispositivoTactil.agregarPoi(local);
		dispositivoTactil.agregarPoi(cgp);
		
		repo=new RepositorioDeTerminales();
		repo.agregarTerminal(dispositivoTactil);
	}
	
	@Test
	public void cercaniaAParada() {
		Assert.assertFalse(dispositivoTactil.consultaCercania("114",unPuntoABuscar));
	}

	@Test
	public void cercaniaABanco() {
		Assert.assertTrue(dispositivoTactil.consultaCercania("santander rio",unPuntoABuscar));
	}

	@Test
	public void cercaniaACGP() {
		Assert.assertTrue(dispositivoTactil.consultaCercania("CGP10",unPuntoABuscar));
	}

	@Test
	public void cercaniaALocal() {
		Assert.assertTrue(dispositivoTactil.consultaCercania("Blaisten",unPuntoABuscar));
	}

	@Test
	public void estaDisponibleColectivo() {
		Assert.assertTrue(dispositivoTactil.consultaDisponibilidad(LocalDateTime.of(2016, 04, 19, 11, 00), "114"));
	}

	@Test
	public void estaDisponibleBanco() {
		Assert.assertTrue(dispositivoTactil.consultaDisponibilidad(LocalDateTime.of(2016, 04, 19, 11, 00), "santander rio"));
	}

	@Test
	public void estaDisponibleCGP() {
		Assert.assertFalse(dispositivoTactil.consultaDisponibilidad(LocalDateTime.of(2016, 04, 29, 10, 00), timbrado));
	}

	@Test
	public void estaDisponibleCGPSinServicio() {
		Assert.assertFalse(dispositivoTactil.consultaDisponibilidad(LocalDateTime.of(2016, 04, 19, 10, 00)));
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
		Assert.assertTrue(coincideCon(dispositivoTactil.busquedaLibre("muebleria"),"Blaisten"));
	}

	@Test
	public void pruebaBusquedaLibrexLinea() {
		Assert.assertTrue(coincideCon(dispositivoTactil.busquedaLibre("114"),"114"));
	}

	@Test
	public void pruebaBusquedaLibrexCGPxServicio() {
		Assert.assertTrue(coincideCon(dispositivoTactil.busquedaLibre("timbrado"),"CGP10"));
	}

	@Test
	public void pruebaBusquedaLibrexBanco() {
		Assert.assertTrue(coincideCon(dispositivoTactil.busquedaLibre("santander rio"),"Santander rio"));
	}

	@Test
	public void pruebaPoligono() {
		Assert.assertTrue(cgp.estaCerca(new Point(-34.638116, -58.4794967)));
	}
	@Test
	public void pruebaServicioNoDisponible(){
		Assert.assertFalse(timbrado.estaDisponible(LocalDateTime.of(2016, 05, 8, 10, 00)));
	}
	@Test
	public void pruebaBusquedaDeServicioCuandoNoTiene(){
		Assert.assertFalse(banco.estaDisponible(LocalDateTime.of(2016, 04, 19, 11, 00), pagoFacil));
	}
	@Test
	public void consultarCercaniaABanco(){
		Assert.assertFalse(banco.estaCerca(new Point(-40.638116, -58.4794967)));
	}
	@Test
	public void bancoNoDisponible(){
		Assert.assertFalse(dispositivoTactil.consultaDisponibilidad(LocalDateTime.of(2016, 05, 8, 11, 00), "santander rio"));
			
	}
	@Test
	public void paradaCerca(){
		Assert.assertTrue(parada114.estaCerca(new Point(-34.6417164, -58.4792636)));		
	}
	public boolean coincideCon(List<Poi> listaEncontrada, String criterio)
	{
		return listaEncontrada.stream().allMatch(unPoi-> unPoi.getNombre().equalsIgnoreCase(criterio));
	}
	public boolean coincideCon(List<Poi> listaEncontrada, Servicio servicio)
	{
		return listaEncontrada.stream().allMatch(unPoi-> unPoi.getNombre().equalsIgnoreCase(servicio.getNombre()));
	}
			
	@Test
	public void busquedaExterna(){
		dispositivoTactil.busquedaLibre("HSBC");
		Mockito.verify(componente).buscarCGPs("HSBC");
		Mockito.verify(componenteBanco).getJsonBanco("HSBC");
	}
	
	@Test
	public void pruebaAdaptador(){
		Assert.assertTrue(adaptador.adaptarObjetos(listaCentroAAdaptar).get(0).getNombre().equalsIgnoreCase("9"));
	}
	
	@Test
	public void notificadorArministrador(){
		Assert.assertEquals("Mail enviado al adminisitrador", dispositivoTactil.notificarAlAdministrador());
	}
	
	@Test
	public void calcularDiferencia(){
		Assert.assertEquals(10, dispositivoTactil.calcularDiferenciaYNotificar(LocalDateTime.of(2016, 06, 05, 18, 15, 10), LocalDateTime.of(2016, 06, 05, 18, 15, 20)));
	}
	
	@Test
	public void reportarCantidadBusquedas(){
		dispositivoTactil.busquedaLibre("muebleria");
		dispositivoTactil.ReportarBusquedas();		
	}
	
	@Test
	public void reportePorTerminal(){
		dispositivoTactil.busquedaLibre("muebleria");
		repo.reportePorTerminal();
	}
	
	@Test
	public void reporteParcialesporTerminal(){
		dispositivoTactil.busquedaLibre("muebleria");
		repo.reportarParcialesTerminal("terminalAbasto");
	}
}
