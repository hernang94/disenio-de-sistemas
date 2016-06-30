package grupo4.ComponentesExternos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import DTOexterno.BancoExterno;
import grupo4.POIs.Banco;
import grupo4.POIs.Poi;
import grupo4.POIs.Servicio;

public class BancoTransformer implements Adaptadores {
	private ComponenteBanco componente;
	private ObjectMapper objectMapper;

	public BancoTransformer() {
		this.objectMapper = new ObjectMapper();
	}

	public List<Poi> convertirJson(String jsons) {
		List<BancoExterno> listaParaAdaptar = new ArrayList<>();
		try {
			listaParaAdaptar = objectMapper.readValue(jsons,
					TypeFactory.defaultInstance().constructCollectionLikeType(List.class, BancoExterno.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return adaptarListaBancosExternos(listaParaAdaptar);
	}

	private List<Poi> adaptarListaBancosExternos(List<BancoExterno> listaParaAdaptar) {
		List<Poi> listaDeBancos = new ArrayList<>();
		listaDeBancos = listaParaAdaptar.stream().map(banco -> adaptarBanco(banco)).collect(Collectors.toList());
		return listaDeBancos;
	}

	private Poi adaptarBanco(BancoExterno bancoExterno) {
		FactoryHorarioBanco horarioBanco = new FactoryHorarioBanco();
		List<String> palabrasClavesBanco = new ArrayList<>();
		Banco banco = new Banco(horarioBanco.dameHorarioBanco(), bancoExterno.getBanco(), palabrasClavesBanco);
		banco.setX(bancoExterno.getX());
		banco.setY(bancoExterno.getY());
		List<Servicio> listaDeServicios = bancoExterno.getServicios().stream()
				.map(nombre -> new Servicio(nombre, horarioBanco.dameHorarioBanco())).collect(Collectors.toList());
		banco.setCoordenadas();
		banco.setListaServicios(listaDeServicios);
		return banco;
	}

	public List<Poi> buscarPois(String criterio) {
		String jsons = componente.getJsonBanco(criterio);
		return convertirJson(jsons);
	}

	public void setComponente(ComponenteBanco componenteBanco) {
		this.componente = componenteBanco;

	}

}
