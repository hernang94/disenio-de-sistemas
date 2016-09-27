package grupo4.PoiDTOs;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.annotations.Embedded;

import grupo4.HerramientasExternas.Poligono;
import grupo4.HerramientasExternas.Punto;

public class CgpDTO extends PoiDTO{

	@Embedded
	private List<ServicioDTO> servicios = new ArrayList<>();
	@Embedded
	private Poligono comuna;
	
	public CgpDTO(String nombre, List<String> palabrasClaves, Punto ubicacion) {
		super(nombre, palabrasClaves, ubicacion);
		// TODO Auto-generated constructor stub
	}

}
//Ver el tema del poloigono en mongo