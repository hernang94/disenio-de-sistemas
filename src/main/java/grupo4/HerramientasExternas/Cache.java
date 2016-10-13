package grupo4.HerramientasExternas;

import java.util.List;

import grupo4.ComponentesExternos.JsonABancoMapper;
import grupo4.POIs.Poi;
import redis.clients.jedis.Jedis;

public class Cache {
	private Jedis jedis = new Jedis();
	private static Cache instancia = new Cache();
	
	public static Cache getInstancia() {
		return instancia;
	}
	
	public boolean criterioEstaEnCache(String criterio){
		return jedis.exists(criterio);
	}
	
	public void actualizarCache(String key,String value){
		jedis.set(key, value);
	}
	
	public List<Poi> obtenerPois(String criterio){
		return JsonABancoMapper.getInstancia().adaptarListaBancosExternos(jedis.get(criterio));
	}
}
