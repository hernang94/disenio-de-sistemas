package Tests;

import java.io.IOException;
import java.util.List;

import org.apache.http.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import grupo4.ComponentesExternos.Http;
import grupo4.HerramientasExternas.Cache;
import grupo4.POIs.Poi;

public class RedisTests {
	private Http http;
	
	@Before
	public void init() {
		Cache.getInstancia().activarCache();
		http = new Http("http://private-96b476-ddsutn.apiary-mock.com/banks?banco=banco&servicio=servicio");
	}

	@Test
	public void redisTest() {
		Cache.getInstancia().actualizarCache("Hola", "Pepe");
		Assert.assertTrue(Cache.getInstancia().criterioEstaEnCache("Hola"));
	}
	
	@Test
	public void desactivoCache(){
		Cache.getInstancia().actualizarCache("Hola", "Pepe");
		Cache.getInstancia().desactivarCache();
		Assert.assertFalse(Cache.getInstancia().criterioEstaEnCache("Hola"));
	}
	
	@Test
	public void mappeoDePois() throws ParseException, IOException{
		Cache.getInstancia().actualizarCache("Bancos", http.obtenerString());
		List<Poi> poisEnCache=Cache.getInstancia().obtenerPois("Bancos");
		Assert.assertTrue(poisEnCache.stream().allMatch(poi->poi.getNombre().equalsIgnoreCase("Banco de la Plaza")));
	}
	
	
}
