package grupo4;

import java.time.LocalDateTime;

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
		return (calcularDistancia(unPunto)<0.5);
	}
	public boolean coincideCon(String criterio){
		return(criterio.equalsIgnoreCase(nombre));
	}
	public abstract boolean estaDisponible(LocalDateTime hora_consulta);

}
