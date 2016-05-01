package Tests;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import grupo4.Banco;
import grupo4.CGP;
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

	@SuppressWarnings("static-access")
	@Before
	public void init() {
		unPuntoABuscar = new Point(-34.638116, -58.4794967);
		dispositivoTactil = new DispositivoTactil();

		Point punto1 = new Point(-34.6409182, -58.4758827);
		banco = new Banco("10:00", "15:00", 1, 5);
		banco.setAltura(1200);
		banco.setCalle("Corrientes");
		banco.setNombre("Santander Rio");
		banco.setCoordenadas(punto1);

		Point punto2 = new Point(-34.6417364, -58.4792636);
		parada114 = new Parada("114");
		parada114.setAltura(1590);
		parada114.setCalle("Alberdi");
		parada114.setCoordenadas(punto2);
		parada114.setNombre("Parada114");

		Point punto3 = new Point(-34.6383056, -58.4814007);
		rubro = rubro.MUEBLERIA;
		local = new LocalComercial(rubro, "09:00", "13:00", "14:00", "18:00", 1, 6);
		local.setAltura(1690);
		local.setCalle("Alberdi");
		local.setCoordenadas(punto3);
		local.setNombre("Blaisten");
		
		Point punto4= new Point(-34.6383669,-58.4773822);
		banco2 = new Banco ("10:00","15:00",1,5);
		banco2.setAltura(480);
		banco2.setCalle("Mariano Acosta");
		banco2.setNombre("Santander Rio");
		banco2.setCoordenadas(punto4);		

		Polygon comuna10 = new Polygon();
		comuna10.add(new Point(-34.637466, -58.476939));
		comuna10.add(new Point(-34.6350677, -58.4810659));
		comuna10.add(new Point(-34.6417364, -58.4792636));
		comuna10.add(new Point(-34.6409182, -58.4758827));
		comuna10.add(new Point(-34.6383056, -58.4814007));
		pagoFacil = new Servicio("PagoFacil", "09:00", "11:00", 1, 5);
		timbrado = new Servicio("timbrado", "12:00", "13:30", 4, 5);
		cgp = new CGP(comuna10);
		cgp.addServicio(pagoFacil);
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
		Assert.assertTrue(dispositivoTactil.consultaDisponibilidad(LocalDateTime.of(2016, 04, 29, 10, 00), pagoFacil));
	}

	@Test
	public void estaDisponibleCGPSinServicio() {
		Assert.assertTrue(dispositivoTactil.consultaDisponibilidad(LocalDateTime.of(2016, 04, 19, 10, 00)));
	}

	@Test
	public void estaDisponibleLocal() {
		boolean a = dispositivoTactil.consultaDisponibilidad(LocalDateTime.of(2016, 04, 19, 17, 00), "Blaisten");
		Assert.assertTrue(a);
	}

	@Test
	public void pruebaHorario() {
		Horario nuevo = new Horario("09:00", "18:00", 1, 5);
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
	
	public boolean coincideCon(List<Poi> listaEncontrada, String criterio)
	{
		return listaEncontrada.stream().allMatch(unPoi-> unPoi.getNombre().equalsIgnoreCase(criterio));
	}
	public boolean coincideCon(List<Poi> listaEncontrada, Servicio servicio)
	{
		return listaEncontrada.stream().allMatch(unPoi-> unPoi.getNombre().equalsIgnoreCase(servicio.getNombre()));
	}
}
