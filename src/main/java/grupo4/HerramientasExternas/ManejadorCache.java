package grupo4.HerramientasExternas;

import grupo4.ComponentesExternos.BancoTransformer;
import redis.clients.jedis.Jedis;

public class ManejadorCache {
	private Jedis jedis = new Jedis();
	private static ManejadorCache instancia = new ManejadorCache();
	
	public static ManejadorCache getInstancia() {
		return instancia;
	}
	
	public boolean criterioEstaEnCache(String criterio){
		return jedis.exists(criterio);
	}
	
	public void actualizarCache(String key,String value){
		jedis.set(key, value);
	}
}
