package grupo4.ComponentesExternos;

import java.io.IOException;
import java.util.List;

import org.apache.http.ParseException;

import grupo4.POIs.Poi;

public interface BuscadorDePois {
	public List<Poi> buscarPois(String criterio1) ;
}
