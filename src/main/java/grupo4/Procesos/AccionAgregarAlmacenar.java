package grupo4.Procesos;

import java.time.LocalDateTime;
import java.util.List;

import grupo4.Acciones.ObserverAlmacenador;
import grupo4.Acciones.Observers;
import grupo4.Acciones.Usuario;
import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.RepositorioDeResultadosDeEjecucion;
import grupo4.Repositorios.ResultadosDeEjecucion;

public class AccionAgregarAlmacenar implements Accion {

	private Criterio criterio;

	public AccionAgregarAlmacenar(Criterio criterio) {
		this.criterio = criterio;
	}

	public void ejecutar() {
		ObserverAlmacenador observer = new ObserverAlmacenador(RepositorioDeBusquedas.getInstancia());
		agregarAccion(observer, criterio.obtenerLista());

	}

	private void agregarAccion(Observers objeto, List<Usuario> lista) {
		lista.stream().forEach(usuario -> usuario.agregarObserver(objeto));
		RepositorioDeResultadosDeEjecucion.getInstancia().agregarResultado(
				new ResultadosDeEjecucion(lista.size(), LocalDateTime.now(), lista.size() + " Usuarios afectados"));
	}

}
