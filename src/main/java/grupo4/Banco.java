package grupo4;

public class Banco extends Poi {
	private Horario horario;
	
	public Banco(String hora_desde,String hora_hasta,int dia_desde,int dia_hasta){
		this.horario= new Horario(hora_desde,hora_hasta,dia_desde,dia_hasta);
	}
	public boolean estaDisponible(){
		return(horario.estaEnDia()&&horario.estaEnHorario());
	}
}
