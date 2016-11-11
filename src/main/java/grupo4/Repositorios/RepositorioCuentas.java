package grupo4.Repositorios;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import grupo4.HerramientasExternas.InstanciadorMorphia;
import grupo4.POIs.Poi;
import grupo4.POIs.Users;

public class RepositorioCuentas implements WithGlobalEntityManager{
	private static RepositorioCuentas instancia = new RepositorioCuentas();
	private List<Users> cuentas= new ArrayList<>();
	public static RepositorioCuentas getInstancia() {
		return instancia;
	}
	public void agregarUsuario(Users usuario){
		Users usuarioAux= buscarUsuario(usuario.getUsuario(),usuario.getContrasenia());
		if ( usuarioAux!= null) {
			throw new RuntimeException("Usuario ya existente");
		} else {
			//InstanciadorMorphia.getDb().save(usuario);
			
			entityManager().persist(usuario);
			
		}
	}
	public Users buscarUsuario(String usuario,String password){
		List<Users>usuariosEnBD=actualizarLista();
		Users usuarioAux=usuariosEnBD.stream().filter(user->evaluarUsuario(user, usuario, password)).findFirst().orElse(null);
		return usuarioAux;
	}
	private boolean evaluarUsuario(Users usuario,String nombre,String password){
		return(usuario.getUsuario().equalsIgnoreCase(nombre)&&usuario.getContrasenia().equalsIgnoreCase(password));
	}
	@SuppressWarnings("unchecked")
	public List<Users> actualizarLista(){
		//return InstanciadorMorphia.getDb().find(Users.class).asList();
		return entityManager().createQuery("from Users").getResultList();
	}
	
}
