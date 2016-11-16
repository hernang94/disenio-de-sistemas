package Tests;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import grupo4.Acciones.Usuario;
import grupo4.Repositorios.RepositorioCuentas;
import grupo4.Usuarios.Users;
import grupo4.Usuarios.UsuarioTerminal;


public class usuariosTest extends AbstractPersistenceTest implements WithGlobalEntityManager{
	private Users cuenta1;
	private Usuario terminal2;
	@Before
	public void init(){
		terminal2=new Usuario("Mataderos", 9);
		entityManager().persist(terminal2);
		cuenta1= new UsuarioTerminal("Ricardo", "Fort",terminal2);
		RepositorioCuentas.getInstancia().agregarUsuario(cuenta1);
		//entityManager().persist(cuenta1);
		entityManager().flush();
		entityManager().clear();
	}
	@Test
	public void testPrueboTraerElUnicoUsuarioExistente(){
		Users cuenta2= RepositorioCuentas.getInstancia().buscarUsuario("Ricardo", "Fort");
		Assert.assertTrue(cuenta2!=null);
	}
	@Test
	public void testPrueboSiTraeAlgo(){
		List<Users> listaAux=entityManager().createQuery("from Users", Users.class).getResultList();
		Assert.assertTrue(!listaAux.isEmpty());
	}
	@Test
	public void testPrueboSiMeDevuelveUnTerminal(){
		Users cuenta2= RepositorioCuentas.getInstancia().buscarUsuario("Ricardo", "Fort");
		Assert.assertTrue(cuenta2.getDecriminatorValue().equalsIgnoreCase("USUARIO_TERMINAL"));
	}

}
