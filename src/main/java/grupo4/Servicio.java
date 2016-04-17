package grupo4;

public class Servicio {
	private String nombre;
	private Horario horario;

	public Servicio(String unNombre, String hora_desde, String hora_hasta, int dia_desde, int dia_hasta) {
		this.nombre = unNombre;
		this.horario = new Horario(hora_desde, hora_hasta, dia_desde, dia_hasta);
	}

	public boolean estaDisponible() {
		return (horario.estaEnDia() && horario.estaEnHorario());
	}

	public String getNombre() {
		return nombre;
	}
}
