package grupo4.Procesos;

import java.util.List;

import grupo4.Acciones.Usuario;
import grupo4.Repositorios.RepositorioDeUsuarios;

public class CriterioATodos implements Criterio {

	public CriterioATodos() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Usuario> obtenerLista() {
		return RepositorioDeUsuarios.getInstancia().getListaDeUsuarios();
	}

}
