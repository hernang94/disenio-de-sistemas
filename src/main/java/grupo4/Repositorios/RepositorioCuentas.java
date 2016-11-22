package grupo4.Repositorios;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import grupo4.HerramientasExternas.InstanciadorMorphia;
import grupo4.POIs.Poi;
import grupo4.Usuarios.Users;

public class RepositorioCuentas implements WithGlobalEntityManager{
	private static RepositorioCuentas instancia = new RepositorioCuentas();
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
	public void quitarUsuario(Users usuario){
		try{
			entityManager().remove(usuario);
		}
		catch(Exception e){
			throw new RuntimeException("No se pudo eliminar el usuario");
	}
	}
	public void actualizarUsuario(int id,String nombre)
	{	
		Users usuarioAux=entityManager().find(Users.class, id);
		usuarioAux.setUsuario(nombre);
		entityManager().merge(usuarioAux);
		entityManager().flush();
		entityManager().clear();
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
