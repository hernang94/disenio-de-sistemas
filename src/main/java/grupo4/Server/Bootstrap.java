package grupo4.Server;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import grupo4.Acciones.Usuario;
import grupo4.POIs.Users;
import grupo4.Repositorios.RepositorioCuentas;

public class Bootstrap implements WithGlobalEntityManager,EntityManagerOps, TransactionalOps{
	
	public void init(){
		withTransaction(()->{
		Usuario terminal=new Usuario("Abasto", 9);
		entityManager().persist(terminal);
		Users u1= new Users("hernan", "gallo", terminal);
		RepositorioCuentas.getInstancia().agregarUsuario(u1);
		entityManager().flush();
		entityManager().clear();});
	}

}
