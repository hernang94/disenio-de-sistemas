package grupo4;

import org.uqbar.geodds.Point;

public class LocalComercial extends Poi {
	private Rubro rubro;

	public LocalComercial(Rubro rubro) {
		super();
		this.rubro = rubro;
	}

	public boolean estaCerca(Point unPunto) {
		if (super.calcularDistancia(unPunto) <= rubro.getRadio()) {
			return true;
		}
		return false;
	}
	public boolean coincideCon(String criterio){
		if((criterio.equalsIgnoreCase(rubro.getNombre()))||super.coincideCon(criterio)){
			return true;
		}
		return false;
	}
}
