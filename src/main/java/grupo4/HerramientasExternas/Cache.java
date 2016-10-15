package grupo4.HerramientasExternas;

import java.util.List;

import grupo4.ComponentesExternos.JsonABancoMapper;
import grupo4.POIs.Poi;
import redis.clients.jedis.Jedis;

public class Cache {
	private static Jedis jedis = new Jedis();
	private static Cache instancia;
	//Por Jedis equivale a segundos
	private static int duracionXKey= 60;
	
	public static Cache getInstancia() {
		if(instancia==null){
			instancia= new Cache();
		}
		return instancia;
	}

	public boolean criterioEstaEnCache(String criterio){
		return jedis.exists(criterio);
	}
	
	public void actualizarCache(String key,String value){
		jedis.setex(key,duracionXKey, value);
	}
	
	public List<Poi> obtenerPois(String criterio){
		return JsonABancoMapper.getInstancia().adaptarListaBancosExternos(jedis.get(criterio));
	}
}
