package Tests;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

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
import grupo4.DispositivoTactil;

public class Tests {
	DispositivoTactil dispositivoTactil;
	Parada parada114;
	Servicio pagoFacil;
	Servicio timbrado;
	Banco banco;
	Banco banco2;
	CGP cgp;
	LocalComercial local;
	Point unPuntoABuscar;
	Rubro rubro;
	ComponenteCGPS componente;
	CGPAdapter adaptador;
	ComponenteBanco componenteBanco;
	BancoTransformer optimus;
	
	@SuppressWarnings("static-access")
	@Before
	public void init() {
		
		
		componente = Mockito.mock(ComponenteCGPS.class);
		adaptador = new CGPAdapter();
		adaptador.setComponente(componente);
		
		
		componenteBanco=Mockito.mock(ComponenteBanco.class);
		optimus = new BancoTransformer();
		optimus.setComponente(componenteBanco);
		
		unPuntoABuscar = new Point(-34.638116, -58.4794967);
		dispositivoTactil = new DispositivoTactil();
		dispositivoTactil.setAdaptador(adaptador);
		dispositivoTactil.setBancoTransformer(optimus);

		banco = new Banco();
		banco.setAltura(1200);
		banco.setCalle("Corrientes");
		banco.setNombre("Santander Rio");
		banco.setX(-34.6409182);
		banco.setY(-58.4758827);
		banco.setCoordenadas();

		parada114 = new Parada("114");
		parada114.setAltura(1590);
		parada114.setCalle("Alberdi");
		parada114.setX(-34.6417364);
		parada114.setY(-58.4792636);
		parada114.setCoordenadas();
		parada114.setNombre("Parada114");

		rubro = rubro.MUEBLERIA;
		//local = new LocalComercial(rubro, "09:00", "13:00", "14:00", "18:00", 1, 6);
		local = new LocalComercial(rubro);
		local.cargarHorariosManana(1, "09:00", "13:00");
		local.cargarHorariosTarde(1, "14:00", "18:00");
		local.cargarHorariosManana(2, "09:00", "13:00");
		local.cargarHorariosTarde(2, "14:00", "20:00");
		local.cargarHorariosManana(3, "09:00", "13:00");
		local.cargarHorariosManana(4, "09:00", "13:00");
		local.cargarHorariosTarde(4, "14:00", "19:00");
		local.setAltura(1690);
		local.setCalle("Alberdi");
		local.setX(-34.6383056);
		local.setY(-58.4814007);
		local.setCoordenadas();
		local.setNombre("Blaisten");
		
		banco2 = new Banco ();
		banco2.setAltura(480);
		banco2.setCalle("Mariano Acosta");
		banco2.setNombre("Santander Rio");
		banco2.setX(-34.6383669);
		banco2.setY(-58.4773822);
		banco2.setCoordenadas();		

		Polygon comuna10 = new Polygon();
		comuna10.add(new Point(-34.637466, -58.476939));
		comuna10.add(new Point(-34.6350677, -58.4810659));
		comuna10.add(new Point(-34.6417364, -58.4792636));
		comuna10.add(new Point(-34.6409182, -58.4758827));
		comuna10.add(new Point(-34.6383056, -58.4814007));
		timbrado = new Servicio("timbrado");
		timbrado.cargarHorario(4, "12:00", "13:30");
		timbrado.cargarHorario(5, "12:00", "13:30");
		cgp = new CGP(comuna10);
		cgp.addServicio(timbrado);
		cgp.setAltura(3968);
		cgp.setCalle("Bacacay");
		cgp.setNombre("CGP10");

		dispositivoTactil.agregarPoi(banco);
		dispositivoTactil.agregarPoi(banco2);
		dispositivoTactil.agregarPoi(parada114);
		dispositivoTactil.agregarPoi(local);
		dispositivoTactil.agregarPoi(cgp);
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
		Assert.assertTrue(coincideCon(dispositivoTactil.busquedaLibre("114"),"Parada114"));
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
	public void pruebaAltaCGP(){
		dispositivoTactil.altaCGP("Alberdi");
		Mockito.verify(componente).buscarCGPs("Alberdi");
	}
	@Test
	public void pruebaModificacionCGP(){
		dispositivoTactil.modificacionCGP("Alberdi");
		Mockito.verify(componente).buscarCGPs("Alberdi");
	}
	@Test
	public void pruebaAltaBanco(){
		dispositivoTactil.altaBanco("Santander Rio", "Deposito");
		Mockito.verify(componenteBanco).getJsonBanco("Santander Rio", "Deposito");
	}
	@Test
	public void pruebaModificacionBanco(){
		dispositivoTactil.modificacionBanco("Santander Rio", "Deposito");
		Mockito.verify(componenteBanco).getJsonBanco("Santander Rio", "Deposito");
	}
	
	
}
