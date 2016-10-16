package Tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



import grupo4.HerramientasExternas.Cache;


public class RedisTests {
@Before
public void init(){
	Cache.getInstancia().activarCache();
}
@Test
public void redisTest(){
	Cache.getInstancia().actualizarCache("Hola", "Pepe");
	Assert.assertTrue(Cache.getInstancia().criterioEstaEnCache("Hola"
			+ ""));
}
}
