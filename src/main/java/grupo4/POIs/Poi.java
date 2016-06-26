package grupo4.POIs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

public abstract class Poi {
	private Point coordenadas;
	protected String nombre;
	private double x;
	private double y;
	private List<String> palabrasClaves = new ArrayList<>();

	public Poi(String nombre, List<String> palabrasClaves) {
		this.nombre = nombre;
		this.palabrasClaves = palabrasClaves;
	}

	public List<String> getPalabrasClaves() {
		return palabrasClaves;
	}

	public void agregarPalabraClave(String palabraClave) {
		palabrasClaves.add(palabraClave);
	}

	public void quitarPalabraClave(String palabraClave) {
		palabrasClaves.remove(palabraClave);
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

	public boolean estaCerca(Point unPunto) {
		return (calcularDistancia(unPunto) < 0.5);
	}

	public boolean cumpleCriterio(String criterio) {
		return criterio.equalsIgnoreCase(nombre) || esUnaPalabraClave(criterio);
	}

	private boolean esUnaPalabraClave(String criterio) {
		return palabrasClaves.stream().anyMatch(unaPalabraClave -> unaPalabraClave.equalsIgnoreCase(criterio));
	}

	public String getNombre() {
		return nombre;
	}

	public abstract boolean estaDisponible(LocalDateTime fechaConsulta);

	public boolean estaDisponible(LocalDateTime fecha, Servicio servicio) {
		return false;
	}

}