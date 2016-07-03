package grupo4.Administracion;

import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.RepositorioDePois;

import java.util.ArrayList;
import java.util.List;

import grupo4.Acciones.ObserverAlmacenador;
import grupo4.Acciones.ObserverNotificador;
import grupo4.Acciones.ObserverReporter;
import grupo4.Acciones.Usuario;
import grupo4.ComponentesExternos.EmailSender;

public class AdministradorDeUsuarios {
	private static AdministradorDeUsuarios instancia = new AdministradorDeUsuarios();
	private List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
	private EmailSender notificador;
	private RepositorioDeBusquedas almacen;

	private AdministradorDeUsuarios() {

	}

	public void agregarUsuario(Usuario nuevoUsuario) {
		listaDeUsuarios.add(nuevoUsuario);
	}

	public void bajaUsuario(Usuario usuarioABajar) {
		listaDeUsuarios.remove(usuarioABajar);
	}

	public void agregarAccionNotificarxComuna(String comuna, long tiempoEstipulado) {
		listaDeUsuarios.stream().filter(usuario -> usuario.getComuna().equalsIgnoreCase(comuna))
				.forEach(usuario -> usuario.agregarObserver(new ObserverNotificador(tiempoEstipulado, notificador)));
	}

	public void agregarAccionAlmacenarxComuna(String comuna) {
		listaDeUsuarios.stream().filter(usuario -> usuario.getComuna().equalsIgnoreCase(comuna))
				.forEach(usuario -> usuario.agregarObserver(new ObserverAlmacenador(almacen)));
	}

	public void agregarAccionReportarxComuna(String comuna) {
		listaDeUsuarios.stream().filter(usuario -> usuario.getComuna().equalsIgnoreCase(comuna))
				.forEach(usuario -> usuario.agregarObserver(new ObserverReporter(almacen)));
	}

	public void quitarAccionNotificarxComuna(String comuna, long tiempoEstipulado) {
		quitarAccionPorComuna(ObserverNotificador.class, comuna);
	}

	public void quitarAccionAlmacenarxComuna(String comuna) {
		quitarAccionPorComuna(ObserverAlmacenador.class, comuna);
	}

	public void quitarAccionReportarxComuna(String comuna) {
		quitarAccionPorComuna(ObserverReporter.class, comuna);
	}
	
	public void quitarAccionPorComuna(Object objeto,String comuna){
		listaDeUsuarios.stream().filter(usuario -> usuario.getComuna().equalsIgnoreCase(comuna))
		.forEach(usuario -> usuario.quitarObserver(usuario.getObservers().stream()
				.filter(observer -> observer.getClass().equals(objeto)).findFirst().get()));
	}
	
	public void quitarAccionATodos(Object objeto){
		listaDeUsuarios.stream().forEach(usuario -> usuario.quitarObserver(usuario.getObservers().stream()
				.filter(observer -> observer.getClass().equals(objeto)).findFirst().get()));
	}
	
	public void quitarAccionNotificarATodos(){
		quitarAccionATodos(ObserverNotificador.class);
	}
	public void quitarAccionAlmacenarATodos(){
		quitarAccionATodos(ObserverAlmacenador.class);
	}
	public void quitarAccionReportarATodos(){
		quitarAccionATodos(ObserverReporter.class);
	}

}
