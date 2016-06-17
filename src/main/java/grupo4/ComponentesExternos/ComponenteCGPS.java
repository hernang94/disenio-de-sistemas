package grupo4.ComponentesExternos;

import java.util.List;

import DTOexterno.CentroDTO;

public interface ComponenteCGPS{
	public List<CentroDTO> buscarCGPs(String valor);
}
