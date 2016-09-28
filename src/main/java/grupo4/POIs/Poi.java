package grupo4.POIs;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

import grupo4.HerramientasExternas.Punto;
import grupo4.PoiDTOs.PoiDTO;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Poi {
	protected String nombre;
	@Id
	@GeneratedValue
	private int idPoi;
	@Transient
	private double x;
	@Transient
	private double y;
	@ElementCollection
	private List<String> palabrasClaves;
	@Embedded
	private Punto coordenadas;

	@SuppressWarnings("unused")
	private Poi() {
	}

	public Poi(String nombre, List<String> palabrasClaves,Punto coordenadas) {
		this.nombre = nombre;
		this.palabrasClaves = palabrasClaves;
		this.coordenadas = coordenadas;
	}

	public void setPalabrasClaves(List<String> palabrasClaves) {
		this.palabrasClaves = palabrasClaves;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<String> getPalabrasClaves() {
		return palabrasClaves;
	}

	public void agregarPalabraClave(String palabraClave) {
		if (!palabrasClaves.contains(palabraClave)) {
			palabrasClaves.add(palabraClave);
		} else {
			throw new RuntimeException("Palabra clave ya existente");
		}
	}

	public void quitarPalabraClave(String palabraClave) {
		if (palabrasClaves.contains(palabraClave)) {
			palabrasClaves.remove(palabraClave);
		} else {
			throw new RuntimeException("Palabra clave no existente");
		}
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

	public void setCoordenadas(double x, double y) {
		coordenadas = new Punto(x, y);
	}

	public double calcularDistancia(Punto punto) {
		return coordenadas.distance(punto);
	}

	public boolean estaCerca(Punto unPunto) {
		return (calcularDistancia(unPunto) < 0.5);
	}

	public boolean cumpleCriterio(String criterio) {
		return ((criterio.equalsIgnoreCase(nombre)) || (esUnaPalabraClave(criterio)));
	}

	private boolean esUnaPalabraClave(String criterio) {
		return palabrasClaves.stream().anyMatch(unaPalabraClave -> unaPalabraClave.equalsIgnoreCase(criterio));
	}

	public String getNombre() {
		return nombre;
	}

	public abstract boolean estaDisponible(LocalDateTime fechaConsulta);
	
	public abstract PoiDTO instanciaDTO();

	public boolean estaDisponible(LocalDateTime fecha, Servicio servicio) {
		return false;
	}

	public void reemplazarPalabrasClaves(List<String> palabrasAReemplazar) {
		palabrasClaves.clear();
		palabrasClaves.addAll(palabrasAReemplazar);
	}

	public int getId() {
		return idPoi;
	}

	public Punto getCoordenadas() {
		return coordenadas;
	}

}