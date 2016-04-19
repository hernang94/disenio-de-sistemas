package Tests;

import org.junit.Before;
import org.uqbar.geodds.Point;

import grupo4.Banco;
import grupo4.CGP;
import grupo4.LocalComercial;
import grupo4.Parada;
import grupo4.Poi;
import grupo4.Rubro;
import grupo4.Terminal;

public class Tests {
	@Before
	public void init(){
		Point unPunto= new Point(2,4);
		Terminal terminal= new Terminal();
		terminal.setUnbicacionTerminal(unPunto);
		
		
		Point punto1= new Point(1,2);
		Banco banco = new Banco("10:00","15:00",1,5);
		banco.setAltura(1200);
		banco.setCalle("Corrientes");
		banco.setNombre("Santander Rio");
		banco.setCoordenadas(punto1);
		
		Point punto2= new Point(4,7);
		Parada parada114= new Parada(114);
		parada114.setAltura(1590);
		parada114.setCalle("Alberdi");
		parada114.setCoordenadas(punto2);
		parada114.setNombre("Parada114");
		
		Point punto3= new Point(5,7);
		Rubro rubro =new Rubro("muebleria",3.2);
		LocalComercial local= new LocalComercial(rubro,"10:00","13:00","17:00","20:30",1,6);
		local.setAltura(1690);
		local.setCalle("Alberdi");
		local.setCoordenadas(punto3);
		local.setNombre("Blaisten");
		
		Point punto4= new Point(6,9);
		CGP cgp= new CGP("");
		cgp.setAltura(3968);
		cgp.setCalle("Bacacay");
		cgp.setCoordenadas(punto4);
		cgp.setNombre("CGP");
		
		
		terminal.agregarPoi(poi1);
		terminal.agregarPoi(poi2);
		terminal.agregarPoi(poi3);
		terminal.agregarPoi(poi4);
		
		
	}
}
