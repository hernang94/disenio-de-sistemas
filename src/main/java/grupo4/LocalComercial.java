package grupo4;

import java.time.LocalDateTime;

import org.uqbar.geodds.Point;



public class LocalComercial extends Poi {
	private Rubro rubro;
	private Horario horario_m;
	private Horario horario_t;
	
	public LocalComercial(Rubro rubro,String desde_m, String hasta_m, String desde_t, String hasta_t, int diadesde,int diahasta) {
		this.rubro = rubro;
		horario_m= new Horario(desde_m, hasta_m,diadesde,diahasta);
		horario_t= new Horario(desde_t, hasta_t,diadesde,diahasta);
	}
	
	public boolean estaCerca(Point unPunto) {
		return (super.calcularDistancia(unPunto) <= rubro.getRadio());
			
	}
	public boolean coincideCon(String criterio){
		return((criterio.equalsIgnoreCase(rubro.getNombre()))||super.coincideCon(criterio));
	}
	public boolean estaDisponible(LocalDateTime hora_consulta){
		boolean criterio1=(horario_m.estaEnHorario(hora_consulta)||horario_t.estaEnHorario(hora_consulta));
		boolean criterio2=(horario_m.estaEnDia(hora_consulta)&&horario_t.estaEnDia(hora_consulta));
		return (criterio1&&criterio2);
	}
}
