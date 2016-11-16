package grupo4.Repositorios;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import grupo4.Acciones.Usuario;

public class RepositorioDeUsuarios implements WithGlobalEntityManager {
	private static RepositorioDeUsuarios instancia = new RepositorioDeUsuarios();

	public static RepositorioDeUsuarios getInstancia() {
		return instancia;
	}

	public void agregarUsuario(Usuario usuario) {
		try {
			entityManager().persist(usuario);
		} catch (EntityExistsException e) {
			throw new RuntimeException("Usuario ya existente");
		}
	}

	public void quitarUsuario(Usuario usuario) {
		entityManager().remove(usuario);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> getListaDeUsuarios() {
		return (List<Usuario>) entityManager().createQuery("from Usuario").getResultList();
	}
	public List<Usuario>getListaDeUsuariosXComuna(int comuna){
		return getListaDeUsuarios().stream().filter(terminal->terminal.getComuna()==comuna).collect(Collectors.toList());
	}

}
