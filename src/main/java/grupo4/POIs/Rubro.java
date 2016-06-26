package grupo4.POIs;

public enum Rubro {

	MUEBLERIA("muebleria", 3.2), INFORMATICA("informatica", 4.5), VETERINARIA("veterinaria",
			1.2), GASTRONOMIA("gastronomia", 2.1), MECANICA("mecanica", 5.4), METALURGICA("metalurgica", 6.1);

	private String nombre;
	private double radio;

	public double getRadio() {
		return this.radio;
	}

	private Rubro(String nombre, double radio) {
		this.nombre = nombre;
		this.radio = radio;
	}

	public String getNombre() {
		return this.nombre;
	}
}
