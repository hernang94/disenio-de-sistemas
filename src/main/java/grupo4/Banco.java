package grupo4;

import java.time.LocalDateTime;

public class Banco extends Poi {
	private Horario horario;

	public Banco(String hora_desde, String hora_hasta, int dia_desde, int dia_hasta) {
		this.horario = new Horario(hora_desde, hora_hasta, dia_desde, dia_hasta);
	}

	public boolean estaDisponible(LocalDateTime hora_consulta) {
		return (horario.estaEnDia(hora_consulta) && horario.estaEnHorario(hora_consulta));
	}
}
