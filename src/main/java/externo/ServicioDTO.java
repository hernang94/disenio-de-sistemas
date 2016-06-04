package externo;

import java.util.ArrayList;
import java.util.List;

public class ServicioDTO {
	private String nombre;
	private List<RangoServicioDTO> rangos = new ArrayList<>();
	
	

	public ServicioDTO(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public List<RangoServicioDTO> getRangos() {
		return rangos;
	}
	public void agregarRango(RangoServicioDTO rango){
		rangos.add(rango);
	}

}
