package grupo4.HerramientasExternas;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public class InstanciadorMorphia {
	private static MongoClient mongoInstance = new MongoClient();
	private static Morphia mphia = new Morphia();
	private static Datastore db = mphia.createDatastore(mongoInstance, "Resultados_de_Busquedas");
	private static InstanciadorMorphia instancia = new InstanciadorMorphia();
	
	public static Datastore getDb() {
		return db;
	}

	public static InstanciadorMorphia getInstancia() {
		return instancia;
	}
	
}
