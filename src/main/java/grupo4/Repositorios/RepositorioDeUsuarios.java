package grupo4.Repositorios;
import java.util.ArrayList;
import java.util.List;

import grupo4.Acciones.Usuario;

public class RepositorioDeUsuarios {
	private List<Usuario> listaDeUsuarios=new ArrayList<>();
	private static RepositorioDeUsuarios instancia = new RepositorioDeUsuarios();
	
	private RepositorioDeUsuarios() {
		
	}
	public static RepositorioDeUsuarios getInstancia() {
		return instancia;
	}
	
	public void agregarUsuario(Usuario usuario){
		listaDeUsuarios.add(usuario);
	}
	public List<Usuario> getListaDeUsuarios() {
		return listaDeUsuarios;
	}
	
}
