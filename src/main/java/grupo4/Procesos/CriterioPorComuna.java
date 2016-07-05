package grupo4.Procesos;

import java.util.List;
import java.util.stream.Collectors;

import grupo4.Acciones.Usuario;
import grupo4.Repositorios.RepositorioDeUsuarios;

public class CriterioPorComuna implements Criterio {

	private int comuna;
	
	public CriterioPorComuna(int comuna) {
		this.comuna=comuna;
	}

	@Override
	public List<Usuario> obtenerLista() {
		List<Usuario>listaAuxiliar=RepositorioDeUsuarios.getInstancia().getListaDeUsuarios().stream().filter(usuario->usuario.getComuna()==comuna).collect(Collectors.toList());
		return listaAuxiliar;
	}

}
