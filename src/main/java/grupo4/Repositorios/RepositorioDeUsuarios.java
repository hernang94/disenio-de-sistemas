package grupo4.Repositorios;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import grupo4.Acciones.Usuario;

public class RepositorioDeUsuarios {
	// private List<Usuario> listaDeUsuarios = new ArrayList<>();
	private static RepositorioDeUsuarios instancia = new RepositorioDeUsuarios();
	EntityManager manager = PerThreadEntityManagers.getEntityManager();

	public static RepositorioDeUsuarios getInstancia() {
		return instancia;
	}

	public void agregarUsuario(Usuario usuario) {
		// listaDeUsuarios.add(usuario);
		try {
			manager.persist(usuario);
			manager.flush();
		} catch (EntityExistsException e) {
			throw new RuntimeException("Usuario ya existente");
		}
	}

	public void quitarUsuario(Usuario usuario) {
		// listaDeUsuarios.remove(usuario);
		manager.remove(usuario);
		/*
		 * if(manager.createQuery("delete from Usuario where idUsuario=:id"
		 * ).setParameter("id", id).executeUpdate()<1){ throw new
		 * RuntimeException("No existe el Usuario"); };
		 */
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> getListaDeUsuarios() {
		return (List<Usuario>) manager.createQuery("from Usuario").getResultList();
	}

	/*
	 * public void reset() { listaDeUsuarios.clear(); }
	 */

}
