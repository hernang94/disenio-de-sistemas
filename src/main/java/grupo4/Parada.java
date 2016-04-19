package grupo4;

import java.time.LocalDateTime;

import org.uqbar.geodds.Point;

public class Parada extends Poi {

	public Parada(int linea_asociada) {
		this.linea_asociada = linea_asociada;
	}

	private int linea_asociada;

	public boolean estaCerca(Point unPunto) {
		if (super.calcularDistancia(unPunto) < 0.1) {
			return true;
		}
		return false;
	}

	public boolean estaDisponible(LocalDateTime hora_consulta) {
		return true;
	}

	public boolean coincideCon(String criterio) {
		if (Integer.parseInt(criterio) == linea_asociada) {
			return true;
		}
		return false;
	}
}
