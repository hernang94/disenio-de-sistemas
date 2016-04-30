package grupo4;

import java.time.LocalDateTime;

public class Banco extends Poi {
	private Horario horario;

	public Banco(String horaDesde, String horaHasta, int diaDesde, int diaHasta) {
		this.horario = new Horario(horaDesde, horaHasta, diaDesde, diaHasta);
	}

	public boolean estaDisponible(LocalDateTime horaConsulta) {
		return (horario.estaEnDia(horaConsulta) && horario.estaEnHorario(horaConsulta));
	}
}
