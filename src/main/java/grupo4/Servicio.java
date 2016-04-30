package grupo4;

import java.time.LocalDateTime;

public class Servicio {
	private String nombre;
	private Horario horario;

	public Servicio(String unNombre, String horaDesde, String horaHasta, int diaDesde, int diaHasta) {
		this.nombre = unNombre;
		this.horario = new Horario(horaDesde, horaHasta, diaDesde, diaHasta);
	}

	public boolean estaDisponible(LocalDateTime horaConsulta) {
		return (horario.estaEnDia(horaConsulta) && horario.estaEnHorario(horaConsulta));
	}

	public String getNombre() {
		return nombre;
	}
}
