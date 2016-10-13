package grupo4.ComponentesExternos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import DTOexterno.BancoExterno;
import grupo4.HerramientasExternas.Cache;
import grupo4.HerramientasExternas.Punto;
import grupo4.POIs.Banco;
import grupo4.POIs.Poi;
import grupo4.POIs.Servicio;

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
