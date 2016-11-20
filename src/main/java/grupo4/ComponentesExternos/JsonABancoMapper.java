package grupo4.ComponentesExternos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import DTOexterno.BancoExterno;
import grupo4.HerramientasExternas.Punto;
import grupo4.POIs.Banco;
import grupo4.POIs.Poi;
import grupo4.POIs.Servicio;

public class JsonABancoMapper {
	private static JsonABancoMapper instancia;
	private static ObjectMapper objectMapper;
	
	public static JsonABancoMapper getInstancia() {
		if(instancia==null){
			instancia= new JsonABancoMapper();
		}
		return instancia;
	}
	
	public static void setObjectMapper(ObjectMapper objectMapper) {
		JsonABancoMapper.objectMapper = objectMapper;
	}

	public List<Poi> adaptarListaBancosExternos(String jsons) {
		List<Poi> listaDeBancos;
		try {
			List<BancoExterno> listaParaAdaptar = objectMapper.readValue(jsons,
					TypeFactory.defaultInstance().constructCollectionLikeType(List.class, BancoExterno.class));
			listaDeBancos = listaParaAdaptar.stream().map(banco -> adaptarBanco(banco)).collect(Collectors.toList());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return listaDeBancos;
	}

	private Poi adaptarBanco(BancoExterno bancoExterno) {
		FactoryHorarioBanco horarioBanco = new FactoryHorarioBanco();
		List<String> palabrasClavesBanco = new ArrayList<>();
		Banco banco = new Banco(horarioBanco.dameHorarioBanco(), bancoExterno.getBanco(), "N/C",palabrasClavesBanco,new Punto(bancoExterno.getX(), bancoExterno.getY()));
		List<Servicio> listaDeServicios = bancoExterno.getServicios().stream()
				.map(nombre -> new Servicio(nombre, horarioBanco.dameHorarioBanco())).collect(Collectors.toList());
		banco.setListaServicios(listaDeServicios);
		return banco;
	}
	

}
