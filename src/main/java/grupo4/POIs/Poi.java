package grupo4.POIs;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

import grupo4.HerramientasExternas.Punto;

@org.mongodb.morphia.annotations.Embedded
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Poi {
	protected String nombre;
	@Id
	@GeneratedValue
	private int idPoi;
	@ElementCollection
	private List<String> palabrasClaves;
	@Embedded
	@Column(nullable=true)
	private Punto coordenadas;
	@Transient
	private String tipo=this.getDecriminatorValue();

	protected Poi() {
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
		return (nombre.contains(criterio) || (esUnaPalabraClave(criterio)||criterio.equalsIgnoreCase(tipo)));
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
	public String getDecriminatorValue() {
	    return this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}
	public String getTipo(){
		return tipo;
	}

}