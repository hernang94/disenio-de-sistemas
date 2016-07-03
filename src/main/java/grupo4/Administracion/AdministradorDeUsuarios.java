package grupo4.Administracion;

import grupo4.Repositorios.RepositorioDePois;

import java.util.ArrayList;
import java.util.List;

import grupo4.Acciones.ObserverNotificador;
import grupo4.Acciones.Usuario;
import grupo4.ComponentesExternos.EmailSender;
public class AdministradorDeUsuarios {
	private static AdministradorDeUsuarios instancia= new AdministradorDeUsuarios();
	private List<Usuario> listaDeUsuarios=new ArrayList<Usuario>();
	private EmailSender notificador;
	private AdministradorDeUsuarios(){
		
	}
	
	public void agregarUsuario(Usuario nuevoUsuario){
		listaDeUsuarios.add(nuevoUsuario);
	}
	public void bajaUsuario(Usuario usuarioABajar){
		listaDeUsuarios.remove(usuarioABajar);
	}
	public void agregarAccionNotificarxComuna(String comuna, long tiempoEstipulado){
		listaDeUsuarios.stream().filter(usuario->usuario.getComuna().equalsIgnoreCase(comuna)).forEach(usuario->usuario.agregarObserver(new ObserverNotificador(tiempoEstipulado, notificador)));
	}
}
