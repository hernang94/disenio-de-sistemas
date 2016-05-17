package grupo4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import externo.CentroDTO;

public class CGPAdapter{
	private ComponenteCGPS componente;
	
	public List<CGP> solicitarCGPs (String valor){
		return adaptarObjetos(this.componente.buscarCGPs(valor));
	}

	private List<CGP> adaptarObjetos(List<CentroDTO> listaExterna) {
		return listaExterna.stream().map(unCentro->adaptar(unCentro)).collect(Collectors.toList());	
	}

	private CGP adaptar(CentroDTO unCentro) {
		CGP aux = new CGP(null,unCentro.getComuna());
		aux.setCalle(unCentro.getDomicilio());
		
	}
	
}
