package grupo4.Procesos;

import grupo4.Repositorios.RepositorioDePois;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import com.fasterxml.jackson.databind.ObjectMapper;

import DTOexterno.BajaPoiExterna;
import grupo4.Acciones.Usuario;
import grupo4.ComponentesExternos.BajaPoiAdapter;

public class AdministradorDeProcesos {
	private static AdministradorDeProcesos instancia = new AdministradorDeProcesos();
	private List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
	private RepositorioDePois repo;
	private Queue<Proceso> colaDeProcesos;
	private AdministradorDeProcesos() {

	}
	

	public static AdministradorDeProcesos getInstancia() {
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
		lista.stream().forEach(elemento->evaluarYBajarPoi(elemento));
	}
	public void evaluarYBajarPoi(BajaPoiExterna bajaPoi){
		if(bajaPoi.getId()==400){
			//TODO Almacenar en el repositorio de resultados de ejecucion el error
			return;
		}
		repo.bajaPoi(bajaPoi.getId());
	}
	
}

