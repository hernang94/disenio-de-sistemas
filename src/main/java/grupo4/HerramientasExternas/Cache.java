package grupo4.HerramientasExternas;

import java.util.Collections;
import java.util.List;

import grupo4.ComponentesExternos.JsonABancoMapper;
import grupo4.POIs.Poi;
import redis.clients.jedis.Jedis;

public class Cache {
	private static Jedis jedis;
	private static Cache instancia;
	//Por Jedis equivale a segundos. Sirve para setear el tiempo que la key es valida y existe, despues de este tiempo la libera
	private static int duracionXKey= 60;
	
	public static Cache getInstancia() {
		if(instancia==null){
			instancia= new Cache();
		}
		return instancia;
	}

	//Este metodo activa la cache si se la habia desactivado, si se lo llama cuando esta activada no hace nada
	public void activarCache(){
		if(jedis==null){
			jedis= new Jedis();
		}
	}
	
	//Si quiero desactivar la cache dinamicamente: se limpia la db de redis, cierro la conexion y seteo el atributo en null
	public void desactivarCache(){
		jedis.flushDB();
		jedis.close();
		jedis=null;
	}
	
	public boolean criterioEstaEnCache(String criterio){
		if(jedis==null){
			return false;
		}
		else{			
			return jedis.exists(criterio);
		}
	}
	
	public void actualizarCache(String key,String value){
		if(jedis!=null){
			if(value!=null){
			jedis.setex(key,duracionXKey, value);			
			}
		}
	}
	
	public List<Poi> obtenerPois(String criterio){
		if(jedis!=null){
		return JsonABancoMapper.getInstancia().adaptarListaBancosExternos(jedis.get(criterio));
		}
		return Collections.emptyList();
	}
}
