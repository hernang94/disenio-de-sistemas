package grupo4;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetTime;

public class Horario {
	private int diadesde;
	private int diahasta;
	private LocalTime desde;
	private LocalTime hasta;
	public Horario(String desde, String hasta, int diadesde,int diahasta) { //El formate de las horas limites de trabajo son: "HH:MM"
		this.desde=LocalTime.of(Integer.parseInt(desde.substring(0,1)), Integer.parseInt(desde.substring(3,4)));
		this.hasta=LocalTime.of(Integer.parseInt(hasta.substring(0,1)), Integer.parseInt(hasta.substring(3,4)));
		this.diadesde=diadesde;
		this.diahasta=diahasta;
	}
	public boolean estaEnHorario(LocalTime hora){
		return (hora.isAfter(desde)&&hora.isBefore(hasta));
		
	}
	public boolean estaEnDia(LocalDate dia){
		int undia= dia.getDayOfWeek().getValue();
		return (undia>=diadesde&&undia<=diahasta);
	}
	
}
