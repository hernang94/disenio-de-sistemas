package grupo4.Administracion;

import grupo4.Repositorios.RepositorioDeBusquedas;
import java.util.ArrayList;
import java.util.List;

import grupo4.Acciones.ObserverAlmacenador;
import grupo4.Acciones.ObserverNotificador;
import grupo4.Acciones.ObserverReporter;
import grupo4.Acciones.Observers;
import grupo4.Acciones.Usuario;
import grupo4.ComponentesExternos.EmailSender;

public class AdministradorDeUsuarios {
	private static AdministradorDeUsuarios instancia = new AdministradorDeUsuarios();
	private List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
	private EmailSender notificador;
	private RepositorioDeBusquedas almacen;

	private AdministradorDeUsuarios() {

	}
	

	public static AdministradorDeUsuarios getInstancia() {
		return instancia;
	}



	public void agregarUsuario(Usuario nuevoUsuario) {
		listaDeUsuarios.add(nuevoUsuario);
	}

	public void bajaUsuario(Usuario usuarioABajar) {
		listaDeUsuarios.remove(usuarioABajar);
	}
  
	
	public void agregarAccionNotificarxComuna(String comuna, long tiempoEstipulado) {
		ObserverNotificador observernotificador=new ObserverNotificador(tiempoEstipulado, notificador);
		agregarAccionxComuna(comuna, observernotificador);
	}

	public void agregarAccionAlmacenarxComuna(String comuna) {
		ObserverAlmacenador almacenador= new ObserverAlmacenador(almacen);
		agregarAccionxComuna(comuna, almacenador);
	}

	public void agregarAccionReportarxComuna(String comuna) {
		ObserverReporter reporter= new ObserverReporter(almacen);
		agregarAccionxComuna(comuna, reporter);
	}
	
	public void agregarAccionNotificarATodos(long tiempoEstipulado){
		ObserverNotificador observernotificador=new ObserverNotificador(tiempoEstipulado, notificador);
		agregarAccionATodos(observernotificador);
	}
	
	public void agregarAccionAlmacenarATodos(){
		ObserverAlmacenador almacenador= new ObserverAlmacenador(almacen);
		agregarAccionATodos(almacenador);
	}
	
	public void agregarAccionReportarATodos(){
		ObserverReporter reporter= new ObserverReporter(almacen);
		agregarAccionATodos(reporter);
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
	
	public void quitarAccionNotificarATodos(){
		quitarAccionATodos(ObserverNotificador.class);
	}
	public void quitarAccionAlmacenarATodos(){
		quitarAccionATodos(ObserverAlmacenador.class);
	}
	public void quitarAccionReportarATodos(){
		quitarAccionATodos(ObserverReporter.class);
	}
	
	
	public void agregarAccionxComuna(String comuna,Observers objeto){
		listaDeUsuarios.stream().filter(usuario -> usuario.getComuna().equalsIgnoreCase(comuna))
		.forEach(usuario -> usuario.agregarObserver(objeto));
	}
	public void agregarAccionATodos(Observers objeto){
		listaDeUsuarios.stream().forEach(usuario -> usuario.agregarObserver(objeto));
	}

	public void quitarAccionPorComuna(Object objeto,String comuna){
		listaDeUsuarios.stream().filter(usuario -> usuario.getComuna().equalsIgnoreCase(comuna))
		.forEach(usuario -> usuario.quitarObserver(obtenerObserverSegunTipo(objeto,usuario)));
	}
	
	public void quitarAccionATodos(Object objeto){
		listaDeUsuarios.stream().forEach(usuario -> usuario.quitarObserver(obtenerObserverSegunTipo(objeto,usuario)));
	}
	public Observers obtenerObserverSegunTipo(Object objeto,Usuario usuario){
		return usuario.getObservers().stream()
				.filter(observer -> observer.getClass().equals(objeto)).findFirst().get();
	}
}

