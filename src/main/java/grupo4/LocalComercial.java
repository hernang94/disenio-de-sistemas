package grupo4;

import org.uqbar.geodds.Point;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetTime;
public class LocalComercial extends Poi {
	private Rubro rubro;
	private Horario horario_m;
	private Horario horario_t;
	private int dia_desde;
	private int dia_hasta;
	
	public LocalComercial(Rubro rubro,String desde_m, String hasta_m, String desde_t, String hasta_t) {
		this.rubro = rubro;
		horario_m= new Horario(desde_m, hasta_m);
		horario_t= new Horario(desde_t, hasta_t);
	}
	
	public boolean estaCerca(Point unPunto) {
		if (super.calcularDistancia(unPunto) <= rubro.getRadio()) {
			return true;
		}
		return false;
	}
	public boolean coincideCon(String criterio){
		if((criterio.equalsIgnoreCase(rubro.getNombre()))||super.coincideCon(criterio)){
			return true;
		}
		return false;
	}
	public boolean estaDisponible(){
		LocalTime horarioactual= LocalTime.now();
		boolean cretirio1=(horario_m.estaEnHorario(horarioactual)||horario_t.estaEnHorario(horarioactual));
	}
}
