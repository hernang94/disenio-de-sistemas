package grupo4.ComponentesExternos;

import java.util.List;

import DTOexterno.LocalComercialExterno;
import grupo4.POIs.Poi;

public interface OrigenExterno {
	public List<Poi> buscarPois(String criterio1);

	public List<LocalComercialExterno> obtenerLocalesExternos();
}
