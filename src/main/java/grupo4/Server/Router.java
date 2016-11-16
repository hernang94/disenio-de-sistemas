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
	Spark.get("/administrador/principal", controlador::mostrarPrincipalAdmin,engine);
	Spark.get("/administrador/listar", controlador::mostrarPantallaParaListarPois,engine);
	Spark.post("/administrador/listar",controlador::listarPois,engine);
	Spark.get("administrador/buscarTerminal", controlador::mostrarPantallaParaListarTerminales,engine);
	Spark.post("administrador/buscarTerminal", controlador::listarTerminales,engine);
	Spark.get("/terminal/principal", controlador::mostrarPrincipalTerminal,engine);
	Spark.get("/terminal/buscarPois", controlador::mostrarBusquedaPois,engine);
	Spark.post("/terminal/buscarPois", controlador::buscarPoisTerminal,engine);
	Spark.get("/login/logueoIncorrecto", controlador::mostrarLogueoIncorrecto,engine);
	}
}
