package Tests;

import java.time.LocalDateTime;

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
import grupo4.Rubro;
import grupo4.Servicio;
import grupo4.Terminal;

public class Tests {
	Terminal terminal;
	Parada parada114;
	Servicio pagoFacil;
	Servicio timbrado;
	Banco banco;
	CGP cgp;
	LocalComercial local;

	@Before
	public void init() {
		Point unPunto = new Point(-34.638116, -58.4794967);
		terminal = new Terminal();
		terminal.setUnbicacionTerminal(unPunto);

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
		Rubro rubro = new Rubro("muebleria", 3.2);
		local = new LocalComercial(rubro, "09:00", "13:00", "14:00", "18:00", 1, 6);
		local.setAltura(1690);
		local.setCalle("Alberdi");
		local.setCoordenadas(punto3);
		local.setNombre("Blaisten");

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

		terminal.agregarPoi(banco);
		terminal.agregarPoi(parada114);
		terminal.agregarPoi(local);
		terminal.agregarPoi(cgp);
	}

	@Test
	public void cercaniaAParada() {
		Assert.assertEquals(false, terminal.consultaCercania("114"));
	}

	@Test
	public void cercaniaABanco() {
		Assert.assertEquals(true, terminal.consultaCercania("santander rio"));
	}

	@Test
	public void cercaniaACGP() {
		Assert.assertEquals(true, terminal.consultaCercania("CGP10"));
	}

	@Test
	public void cercaniaALocal() {
		Assert.assertEquals(true, terminal.consultaCercania("Blaisten"));
	}

	@Test
	public void estaDisponibleColectivo() {
		Assert.assertEquals(true, terminal.consultaDisponibilidad(LocalDateTime.now(), "114"));
	}

	@Test
	public void estaDisponibleBanco() {
		Assert.assertEquals(false, terminal.consultaDisponibilidad(LocalDateTime.now(), "santander rio"));
	}

	@Test
	public void estaDisponibleCGP() {
		Assert.assertEquals(true, terminal.consultaDisponibilidad(LocalDateTime.of(2016, 04, 19, 10, 00), "pagoFacil"));
	}

	@Test
	public void estaDisponibleCGPSinServicio() {
		Assert.assertEquals(true, terminal.consultaDisponibilidad(LocalDateTime.of(2016, 04, 19, 10, 00)));
	}

	@Test
	public void estaDisponibleLocal() {
		boolean a = terminal.consultaDisponibilidad(LocalDateTime.of(2016, 04, 19, 17, 00), "Blaisten");
		Assert.assertEquals(true, a);
	}

	@Test
	public void pruebaHorario() {
		Horario nuevo = new Horario("09:00", "18:00", 1, 5);
		Assert.assertEquals(true, nuevo.estaEnHorario(LocalDateTime.of(2016, 04, 19, 17, 00)));
	}

	@Test
	public void pruebaBusquedaLibrexRubro() {
		Assert.assertEquals(1, terminal.busquedaLibre("muebleria").size());
	}

	@Test
	public void pruebaBusquedaLibrexLinea() {
		Assert.assertEquals(1, terminal.busquedaLibre("114").size());
	}

	@Test
	public void pruebaBusquedaLibrexCGPxServicio() {
		Assert.assertEquals(1, terminal.busquedaLibre("timbrado").size());
	}

	@Test
	public void pruebaBusquedaLibrexBanco() {
		Assert.assertEquals(1, terminal.busquedaLibre("santander rio").size());
	}

	@Test
	public void pruebaPoligono() {
		Assert.assertEquals(true, cgp.estaCerca(new Point(-34.638116, -58.4794967)));
	}
}
