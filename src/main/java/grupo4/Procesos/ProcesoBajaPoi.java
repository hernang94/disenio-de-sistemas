package grupo4.Procesos;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import DTOexterno.BajaPoiExterna;
import grupo4.ComponentesExternos.BajaPoiAdapter;
import grupo4.Repositorios.RepositorioDePois;

public class ProcesoBajaPoi implements Proceso{
	RepositorioDePois repo;
	
	public ProcesoBajaPoi(RepositorioDePois repositorio){
		this.repo=repositorio;
	}
	
	public void ejecutar(){
		bajarPois();
	}
	
	
	public void bajarPois(){
		ObjectMapper objectMapper= new ObjectMapper();
		BajaPoiAdapter adaptador=new BajaPoiAdapter(objectMapper);
		List<BajaPoiExterna> lista = adaptador.obtenerPoisABajar();
		lista.stream().forEach(elemento->evaluarYBajarPoi(elemento));
	}
	public void evaluarYBajarPoi(BajaPoiExterna bajaPoi){
		if(bajaPoi.getId()==400){
			//TODO Almacenar en el repositorio de resultados de ejecucion el error
			return;
		}
		repo.bajaPoi(bajaPoi.getId());
	}
}
