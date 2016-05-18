package grupo4;

import java.time.LocalDateTime;
import java.util.Map;

import org.uqbar.geodds.Point;

public abstract class Poi {
	private Point coordenadas;
	protected String nombre;
	private String calle;
	private int altura;
	private double x;
	private double y;
	
	

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

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public void setAltura(int altura) {
		this.altura = altura;
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
	protected void inicializarHash(Map<Integer,Horario> hashmap){
		for (int i = 1; i <= 7; i++) {
			hashmap.put(i, null);
		}
	}
	public abstract boolean estaDisponible(LocalDateTime fechaConsulta);

	public boolean estaDisponible(LocalDateTime fecha, Servicio servicio){
		return false;
	}

}