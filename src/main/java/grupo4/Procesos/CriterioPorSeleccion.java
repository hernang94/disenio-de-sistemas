package grupo4.Procesos;

import java.util.ArrayList;
import java.util.List;

import grupo4.Acciones.Usuario;
import grupo4.Repositorios.RepositorioDeUsuarios;

public class CriterioPorSeleccion implements Criterio {

	List<String>listaDeNombresTerminales;
	
	public CriterioPorSeleccion(List<String> lista) {
		this.listaDeNombresTerminales=lista;
		
	}
	
	public List<Usuario> obtenerLista() {
		List<Usuario> listaARetornar=new ArrayList<>();
		listaDeNombresTerminales.stream().forEach(nombre->agregarALista(nombre,listaARetornar));
		return listaARetornar;
	}

	private void agregarALista(String nombre, List<Usuario> listaARetornar) {
		RepositorioDeUsuarios repo=RepositorioDeUsuarios.getInstancia();
		
		Usuario auxiliar=repo.getListaDeUsuarios().stream().filter(usuario->usuario.getTerminal().equalsIgnoreCase(nombre)).findFirst().get();
		listaARetornar.add(auxiliar);
	}

}
