package grupo4.Server;

import controllers.Controller;
import controllers.HomeController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
	public static void configure(){
	HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
			.create()
			.withDefaultHelpers()
			.withHelper("isTrue", BooleanHelper.isTrue)
			.build();

	Spark.staticFiles.location("/public");
	Spark.get("/", HomeController::home,engine);
	Spark.get("/login", Controller::logeo, engine);
	Spark.post("/login", Controller::Ingreso);
	Spark.get("/principalAdmin", Controller::mostrarPrincipalAdmin,engine);
	}
}
