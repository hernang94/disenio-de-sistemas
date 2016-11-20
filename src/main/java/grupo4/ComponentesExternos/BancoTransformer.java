package grupo4.ComponentesExternos;

import java.util.ArrayList;
import java.util.List;
import grupo4.HerramientasExternas.Cache;
import grupo4.POIs.Poi;

public class BancoTransformer implements BuscadorDePois {
	private ComponenteBanco componente;

	public List<Poi> convertirJson(String jsons) {
		return JsonABancoMapper.getInstancia().adaptarListaBancosExternos(jsons);
	}

	public List<Poi> buscarPois(String criterio) {
		List<Poi> listaARetornar=new ArrayList<>();
		if(Cache.getInstancia().criterioEstaEnCache(criterio)){
		listaARetornar.addAll(Cache.getInstancia().obtenerPois(criterio));
		}
		else{
		String jsons = componente.getJsonBanco(criterio);
		Cache.getInstancia().actualizarCache(criterio, jsons);
		listaARetornar.addAll(convertirJson(jsons));
		}
		return listaARetornar;
	}

	public void setComponente(ComponenteBanco componenteBanco) {
		this.componente = componenteBanco;

	}

}
