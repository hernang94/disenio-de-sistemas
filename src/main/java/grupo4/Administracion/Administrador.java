package grupo4.Administracion;

import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.RepositorioDePois;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import DTOexterno.BajaPoiExterna;
import grupo4.Acciones.ObserverAlmacenador;
import grupo4.Acciones.ObserverNotificador;
import grupo4.Acciones.ObserverReporter;
import grupo4.Acciones.Observers;
import grupo4.Acciones.Usuario;
import grupo4.ComponentesExternos.BajaPoiAdapter;
import grupo4.ComponentesExternos.EmailSender;

public class Administrador {
	private static Administrador instancia = new Administrador();
	private List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
	private RepositorioDePois repo;

	private Administrador() {

	}
	

	public static Administrador getInstancia() {
		return instancia;
	}



	public void agregarUsuario(Usuario nuevoUsuario) {
		listaDeUsuarios.add(nuevoUsuario);
	}

	public void bajaUsuario(Usuario usuarioABajar) {
		listaDeUsuarios.remove(usuarioABajar);
	}
	
	public void bajarPois(){
		ObjectMapper objectMapper= new ObjectMapper();
		BajaPoiAdapter adaptador=new BajaPoiAdapter(objectMapper);
		List<BajaPoiExterna> lista = adaptador.obtenerPoisABajar();
		lista.stream().forEach(elemento->repo.bajaPoi(elemento.getId()));
	}
	
	
}

