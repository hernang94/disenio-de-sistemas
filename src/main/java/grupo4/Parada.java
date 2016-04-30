package grupo4;

import java.time.LocalDateTime;

import org.uqbar.geodds.Point;

public class Parada extends Poi {

	public Parada(String lineaAsociada) {
		this.lineaAsociada = lineaAsociada;
	}

	private String lineaAsociada;

	public boolean estaCerca(Point unPunto) {
		return (super.calcularDistancia(unPunto) <= 0.1);
	}

	public boolean estaDisponible(LocalDateTime horaConsulta) {
		return true;
	}

	public boolean encuentraNombre(String criterio) {
		return (criterio.equals(lineaAsociada));
	}
}
