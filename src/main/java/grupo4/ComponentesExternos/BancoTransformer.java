package grupo4.ComponentesExternos;

import java.util.List;
import grupo4.HerramientasExternas.Cache;
import grupo4.POIs.Poi;

public class BancoTransformer implements BuscadorDePois {
	private ComponenteBanco componente;

	public List<Poi> convertirJson(String jsons) {
		return JsonABancoMapper.getInstancia().adaptarListaBancosExternos(jsons);
	}

	public List<Poi> buscarPois(String criterio) {
		String jsons = componente.getJsonBanco(criterio);
		Cache.getInstancia().actualizarCache(criterio, jsons);
		return convertirJson(jsons);
	}

	public void setComponente(ComponenteBanco componenteBanco) {
		this.componente = componenteBanco;

	}

}
