package grupo4.Procesos;

import java.time.LocalDateTime;
import java.util.List;

import grupo4.Acciones.ObserverAlmacenador;
import grupo4.Acciones.ObserverNotificador;
import grupo4.Acciones.ObserverReporter;
import grupo4.Acciones.Observers;
import grupo4.Acciones.Usuario;
import grupo4.ComponentesExternos.EmailSender;
import grupo4.Repositorios.RepositorioDeBusquedas;;
public class ProcesoGestionarAcciones implements Proceso{
	private LocalDateTime horaYFecha;
	private long periodicidad;
	private List<Usuario> listaDeUsuarios;
	private EmailSender notificador;
	private RepositorioDeBusquedas almacen;
	private int accion;
	private int observer;
	private int tiempoEstipulado;
	public ProcesoGestionarAcciones(LocalDateTime horaYFecha, long periodicidad, List<Usuario> listaUsuarios, String comuna, EmailSender notificador
			,int accion, int observer, int tiempoEstipulado) {
		this.horaYFecha = horaYFecha;
		this.periodicidad = periodicidad;
		this.listaDeUsuarios = listaUsuarios;
		almacen= RepositorioDeBusquedas.getInstancia();
		this.notificador=notificador;
		this.accion=accion;
		this.observer=observer;
		this.tiempoEstipulado=tiempoEstipulado;
	}
	//Observer: 1-Notificar 2-Almacenar 3-Reportar
	//Accion: 1-Agregar 2-Quitar
	public void ejecutar(){
		switch(accion){
			case 1:
				agregarAccion(observer,tiempoEstipulado);
			break;
			case 2:
				quitarAccion(observer);
			break;
		}
	}
	
	private void quitarAccion(int observer) {
		switch(observer){
		case 1:
			quitarAccionNotificarATodos();
		break;
		case 2:
			quitarAccionAlmacenarATodos();
		break;
		case 3:
			quitarAccionReportarATodos();
		break;
		default:
			// TODO Lanzar una excepción y actualizar repo de Resultados
		}
	}

	private void agregarAccion(int observer,int tiempoEstipulado){
		switch(observer){
		case 1:
			agregarAccionNotificarATodos(tiempoEstipulado);
		break;
		case 2:
			agregarAccionAlmacenarATodos();
		break;
		case 3:
			agregarAccionReportarATodos();
		break;
		default:
			//TODO Lanzar una excepción y actualizar repo de Resultados
		}
	}
	
	private void agregarAccionNotificarATodos(long tiempoEstipulado){
		ObserverNotificador observernotificador=new ObserverNotificador(tiempoEstipulado, notificador);
		agregarAccionATodos(observernotificador);
	}
	
	private void agregarAccionAlmacenarATodos(){
		ObserverAlmacenador almacenador= new ObserverAlmacenador(almacen);
		agregarAccionATodos(almacenador);
	}
	
	private void agregarAccionReportarATodos(){
		ObserverReporter reporter= new ObserverReporter(almacen);
		agregarAccionATodos(reporter);
	}
	
	private void quitarAccionNotificarATodos(){
		quitarAccionATodos(ObserverNotificador.class);
	}
	
	private void quitarAccionAlmacenarATodos(){
		quitarAccionATodos(ObserverAlmacenador.class);
	}
	
	private void quitarAccionReportarATodos(){
		quitarAccionATodos(ObserverReporter.class);
	}
	
	private void agregarAccionATodos(Observers objeto){
		listaDeUsuarios.stream().forEach(usuario -> usuario.agregarObserver(objeto));
	}

	private void quitarAccionATodos(Object objeto){
		listaDeUsuarios.stream().forEach(usuario -> usuario.quitarObserver(obtenerObserverSegunTipo(objeto,usuario)));
	}
	
	private Observers obtenerObserverSegunTipo(Object objeto,Usuario usuario){
		return usuario.getObservers().stream()
				.filter(observer -> observer.getClass().equals(objeto)).findFirst().get();
	}
}
