package grupo4.Procesos;


import grupo4.Acciones.ObserverAlmacenador;

public class QuitarAlmacenar extends AccionGestionObserver{

	
	public QuitarAlmacenar(Criterio criterio){
		super(criterio);
	}
	@Override
	public void ejecutar() {
		quitarAccion(ObserverAlmacenador.class, criterio.obtenerLista());
	}
}
