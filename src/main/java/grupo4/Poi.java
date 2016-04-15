package grupo4;

import org.uqbar.geodds.Point;

public abstract class Poi{
	Point coordenadas;
	String nombre;
	String calle;
	int altura;
	public Point getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(Point coordenadas) {
		this.coordenadas = coordenadas;
	}
	public double calcularDistancia(Point punto){
		return this.coordenadas.distance(punto);
	}
	public boolean estaCerca(Point unPunto){
		if (calcularDistancia(unPunto)<0.5) {
			return true;
		}
		return false;
	}
	public boolean coincideCon(String criterio){
		if(criterio.equalsIgnoreCase(nombre)){
			return true;
		}
		return false;
	}
	//public abstract boolean estaDisponible();

}
