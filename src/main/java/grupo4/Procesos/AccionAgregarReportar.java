package grupo4.Procesos;

import java.util.List;

import grupo4.Acciones.ObserverReporter;
import grupo4.Acciones.Observers;
import grupo4.Acciones.Usuario;
import grupo4.Repositorios.RepositorioDeBusquedas;

public class AccionAgregarReportar implements Accion {

	private Criterio criterio;
	
	public AccionAgregarReportar(Criterio criterio) {
		this.criterio = criterio;
	}
	public void ejecutar() {
		ObserverReporter observer = new ObserverReporter(RepositorioDeBusquedas.getInstancia());
		agregarAccion(observer, criterio.obtenerLista());
	}

	private void agregarAccion(Observers objeto,List<Usuario> lista){
		lista.stream().forEach(usuario -> usuario.agregarObserver(objeto));
	}
	
	
}
