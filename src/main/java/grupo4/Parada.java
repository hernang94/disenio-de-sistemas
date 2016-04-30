package grupo4;

import java.time.LocalDateTime;

import org.uqbar.geodds.Point;

public class Parada extends Poi {

	public Parada(String lineaAsociada) {
		this.lineaAsociada = lineaAsociada;
	}

	private String lineaAsociada;

	public boolean estaCerca(Point unPunto) {
		if (super.calcularDistancia(unPunto) <= 0.1) {
			return true;
		}
		return false;
	}

	public boolean estaDisponible(LocalDateTime horaConsulta) {
		return true;
	}

	public boolean coincideCon(String criterio) {
		if (criterio.equals(lineaAsociada)) {
			return true;
		}
		return false;
	}
}
