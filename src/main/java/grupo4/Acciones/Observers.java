package grupo4.Acciones;

import java.time.LocalDateTime;

import grupo4.Repositorios.ResultadosDeBusquedas;

public interface Observers {

	//Usar un ENUM y tener los criterios/quitar herencia
	//Usar un metodo polimorfico unico y que cada uno lo redefina
	
	public void realizarAccion(ResultadosDeBusquedas resultado);
	
}
