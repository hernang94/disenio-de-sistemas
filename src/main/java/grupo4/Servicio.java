package grupo4;

import java.time.LocalDateTime;

public class Servicio {
	private String nombre;
	private Horario horario;

	public Servicio(String unNombre, String horaDesde, String horaHasta, int diaDesde, int diaHasta) {
		this.nombre = unNombre;
		this.horario = new Horario(horaDesde, horaHasta, diaDesde, diaHasta);
	}

	public boolean estaDisponible(LocalDateTime fechaConsulta) {
		return (horario.estaEnDia(fechaConsulta) && horario.estaEnHorario(fechaConsulta));
	}

	public String getNombre() {
		return nombre;
	}
}
