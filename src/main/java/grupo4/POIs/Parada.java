package grupo4.POIs;

import java.time.LocalDateTime;
import java.util.List;

import org.uqbar.geodds.Point;

public class Parada extends Poi {

	public Parada( String nombre, List<String> palabrasClaves) {
		super(nombre, palabrasClaves);
	}

	public boolean estaCerca(Point unPunto) {
		return (super.calcularDistancia(unPunto) <= 0.1);
	}

	public boolean estaDisponible(LocalDateTime horaConsulta) {
		return true;
	}

	public boolean encuentraNombre(String criterio) {
		return (criterio.equals(super.getNombre()));
	}
}
