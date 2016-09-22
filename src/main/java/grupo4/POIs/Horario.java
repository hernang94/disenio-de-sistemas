package grupo4.POIs;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Embeddable;

@Embeddable
public class Horario {
	/*
	 * @Id
	 * 
	 * @GeneratedValue private int idHorario;
	 */
	// preguntar a julieta :) (persistir LocalTime)
	private LocalTime desde;
	private LocalTime hasta;

	@SuppressWarnings("unused")
	private Horario() {

	}

	public Horario(String desde, String hasta) { // El formato de las hora limites de trabajo son:"HH:MM"
		this.desde = LocalTime.of(Integer.parseInt(desde.substring(0, 2)), Integer.parseInt(desde.substring(3, 5)));
		this.hasta = LocalTime.of(Integer.parseInt(hasta.substring(0, 2)), Integer.parseInt(hasta.substring(3, 5)));
	}

	public boolean estaEnHorario(LocalDateTime fechaConsulta) {
		return (fechaConsulta.toLocalTime().isAfter(desde) && fechaConsulta.toLocalTime().isBefore(hasta));

	}
}
