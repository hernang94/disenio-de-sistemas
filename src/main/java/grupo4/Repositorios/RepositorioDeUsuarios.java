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
	public Usuario buscarUsuario(int id){
		return entityManager().find(Usuario.class, id);
	}
	
	public void quitarUsuario(Usuario usuario) {
		entityManager().remove(usuario);
	}
	public void actualizarUsuario(int id,String terminal,int comuna)
	{
		Usuario usuarioAux=entityManager().find(Usuario.class, id);
		usuarioAux.setTerminal(terminal);
		usuarioAux.setComuna(comuna);
		entityManager().merge(usuarioAux);
		entityManager().flush();
		entityManager().clear();
	}
	public void actualizarObjetoUsuario(Usuario terminal){
		entityManager().merge(terminal);
		entityManager().flush();
		entityManager().clear();
	}
	@SuppressWarnings("unchecked")
	public List<Usuario> getListaDeUsuarios() {
		return (List<Usuario>) entityManager().createQuery("from Usuario").getResultList();
	}
	public List<Usuario>getListaDeUsuariosXComuna(int comuna){
		return getListaDeUsuarios().stream().filter(terminal->terminal.getComuna()==comuna).collect(Collectors.toList());
	}

}
