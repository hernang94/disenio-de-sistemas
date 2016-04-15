package grupo4;

import org.uqbar.geodds.Point;

public class Parada extends Poi {

	private int linea_asociada;
	
	

	public boolean estaCerca(Point unPunto) {
		if (super.calcularDistancia(unPunto) < 0.1) {
			return true;
		}
		return false;
	}

	public boolean estaDisponible() {
		return true;
	}
	public boolean coincideCon(String criterio){
		if(Integer.parseInt(criterio)==linea_asociada){
			return true;
		}
		return false;
	}
}
