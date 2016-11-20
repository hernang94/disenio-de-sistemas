package grupo4.Server;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import grupo4.Acciones.Usuario;
import grupo4.HerramientasExternas.Punto;
import grupo4.POIs.Banco;
import grupo4.POIs.Horario;
import grupo4.POIs.Parada;
import grupo4.POIs.Poi;
import grupo4.POIs.Servicio;
import grupo4.Repositorios.RepositorioCuentas;
import grupo4.Repositorios.RepositorioDePois;
import grupo4.Usuarios.Users;
import grupo4.Usuarios.UsuarioAdministrador;
import grupo4.Usuarios.UsuarioTerminal;

public class Bootstrap implements WithGlobalEntityManager,EntityManagerOps, TransactionalOps{
	private Poi banco;
	private Poi parada114;
	private List<Servicio> servicios;
	public void init(){
		withTransaction(()->{
		Usuario terminal=new Usuario("Abasto", 9);
		entityManager().persist(terminal);
		Users u1= new UsuarioTerminal(terminal.getTerminal(), "terminal", terminal);
		Users u2= new UsuarioAdministrador("admin", "w23e");
		RepositorioCuentas.getInstancia().agregarUsuario(u1);
		RepositorioCuentas.getInstancia().agregarUsuario(u2);
		Map<DayOfWeek, Horario> hashMapBanco;
		Horario horarioBanco;
		horarioBanco = new Horario("10:00", "15:00");
		hashMapBanco = new HashMap<>();
		hashMapBanco.put(DayOfWeek.MONDAY, horarioBanco);
		hashMapBanco.put(DayOfWeek.TUESDAY, horarioBanco);
		hashMapBanco.put(DayOfWeek.WEDNESDAY, horarioBanco);
		hashMapBanco.put(DayOfWeek.THURSDAY, horarioBanco);
		hashMapBanco.put(DayOfWeek.FRIDAY, horarioBanco);
		List<String> palabrasClavesBanco = new ArrayList<>();
		palabrasClavesBanco.add("Santander");
		palabrasClavesBanco.add("Rio");
		palabrasClavesBanco.add("Fantino");
		palabrasClavesBanco.add("Banco");
		palabrasClavesBanco.add("Rojo");
		palabrasClavesBanco.add("Prestamo");
		palabrasClavesBanco.add("Cuenta corriente");
		palabrasClavesBanco.add("Cajero");
		servicios=new ArrayList<>();
		servicios.add(new Servicio("Cajero", null));
		banco = new Banco(hashMapBanco, "Santander Rio","Calle falsa 123" , palabrasClavesBanco,new Punto(-34.6409182,-58.4758827));
		RepositorioDePois.getInstancia().agregarPoi(banco);
		parada114 = new Parada("114","Tandil y Lacarra" ,null,new Punto(-34.6417364,-58.4792636));
		RepositorioDePois.getInstancia().agregarPoi(parada114);
		entityManager().flush();
		entityManager().clear();});
	}

}
