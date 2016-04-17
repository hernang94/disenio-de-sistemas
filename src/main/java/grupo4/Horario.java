package grupo4;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetTime;
public class Horario {
	private LocalTime desde;
	private LocalTime hasta;
	public Horario(String desde, String hasta) { //El formate de las horas limites de trabajo son: "HH:MM"
		this.desde=LocalTime.of(Integer.parseInt(desde.substring(0,1)), Integer.parseInt(desde.substring(3,4)));
		this.hasta=LocalTime.of(Integer.parseInt(hasta.substring(0,1)), Integer.parseInt(hasta.substring(3,4)));
	}
	public boolean estaEnHorario(LocalTime hora){
		return (hora.isAfter(desde)&&hora.isBefore(hasta));
	}
	
}
