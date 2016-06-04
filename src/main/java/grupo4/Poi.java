package grupo4;

import java.time.LocalDateTime;
import org.uqbar.geodds.Point;

public abstract class Poi {
	private Point coordenadas;
	protected String nombre;
	private double x;
	private double y;
	
	

	public Poi(String nombre) {
		this.nombre = nombre;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Point getCoordenadas() {
		return coordenadas;
	}

	public double calcularDistancia(Point punto) {
		return this.coordenadas.distance(punto);
	}

	public void setCoordenadas() {
		this.coordenadas = new Point(x, y);
	}
	

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public boolean estaCerca(Point unPunto) {
		return (calcularDistancia(unPunto) < 0.5);
	}

	public boolean encuentraNombre(String criterio) {
		return (criterio.equalsIgnoreCase(nombre));
	}

	public String getNombre() {
		return nombre;
	}

	public abstract boolean estaDisponible(LocalDateTime fechaConsulta);

	public boolean estaDisponible(LocalDateTime fecha, Servicio servicio){
		return false;
	}

}