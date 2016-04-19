package grupo4;

public class Rubro {
	private String nombre;
	private double radio;

	public double getRadio() {
		return this.radio;
	}

	public Rubro(String nombre, double radio) {
		super();
		this.nombre = nombre;
		this.radio = radio;
	}

	public String getNombre() {
		return this.nombre;
	}
}
