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
	Controller controlador= new Controller();
	Spark.staticFiles.location("/public");
	Spark.get("/", HomeController::home,engine);
	Spark.get("/login", controlador::logeo, engine);
	Spark.post("/login", controlador::Ingreso);
	Spark.get("/logout", controlador::logout);
	Spark.get("/principalAdmin", controlador::mostrarPrincipalAdmin,engine);
	Spark.get("/principalTerminal", controlador::mostrarPrincipalTerminal,engine);
	Spark.get("/logueoIncorrecto", controlador::mostrarLogueoIncorrecto,engine);
	}
}
