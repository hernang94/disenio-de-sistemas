package grupo4.Server;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	 public static void main(String[] args) {
		 new Bootstrap().init();
	Spark.port(9999);
	DebugScreen.enableDebugScreen();
	Router.configure();
	}
}
