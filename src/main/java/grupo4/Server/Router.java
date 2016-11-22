package grupo4.Server;

import controllers.AdminController;
import controllers.HomeController;
import controllers.LoginController;
import controllers.TerminalController;
import grupo4.Usuarios.Users;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
	private static Users u1;
	public static void configure(){
	HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
			.create()
			.withDefaultHelpers()
			.withHelper("isTrue", BooleanHelper.isTrue)
			.build();
	LoginController controladorLogueo= new LoginController();
	AdminController controladorAdmin=new AdminController();
	TerminalController controladorTerminal= new TerminalController();
	Spark.staticFiles.location("/public");
	Spark.get("/", HomeController::home,engine);
	Spark.post("/login", controladorLogueo::Ingreso);
	Spark.get("/logout", controladorLogueo::logout);
	Spark.get("/login/logueoIncorrecto", controladorLogueo::mostrarLogueoIncorrecto,engine);
	Spark.get("/administrador/principal", controladorAdmin::mostrarPrincipalAdmin,engine);
	Spark.get("/administrador/listar", controladorAdmin::mostrarPantallaParaListarPois,engine);
	Spark.post("/administrador/listar",controladorAdmin::listarPois,engine);
	Spark.get("administrador/buscarTerminal", controladorAdmin::mostrarPantallaParaListarTerminales,engine);
	Spark.post("administrador/buscarTerminal", controladorAdmin::listarTerminales,engine);
	Spark.post("administrador/eliminar/pois/:id", controladorAdmin::eliminarPoi,engine);
	Spark.get("administrador/editar/pois/:id", controladorAdmin::mostarEditarPoi,engine);
	Spark.post("administrador/editar/pois/:id", controladorAdmin::editarPoi,engine);
	Spark.post("administrador/eliminar/terminales/:id", controladorAdmin::eliminarTerminal,engine);
	Spark.get("administrador/editar/terminales/:id", controladorAdmin::mostarEditarTerminal,engine);
	Spark.post("administrador/editar/terminales/:id", controladorAdmin::editarTerminal,engine);
	Spark.get("administrador/configurarAcciones/terminales/:id", controladorAdmin::mostrarPrincipalConfigurarAcciones,engine);
	Spark.get("administrador/configurarAcciones/agregar/terminales/:id", controladorAdmin::mostrarAgregarAccion,engine);
	Spark.post("administrador/configurarAcciones/agregar/terminales/:id", controladorAdmin::agregarAccion,engine);
	Spark.get("administrador/configurarAcciones/eliminar/terminales/:id", controladorAdmin::mostrarEliminarAccion,engine);
	Spark.post("administrador/configurarAcciones/eliminar/terminales/:id", controladorAdmin::eliminarAccion,engine);
	Spark.get("/administrador/agregarTerminal", controladorAdmin::mostrarAgregarTerminal,engine);
	Spark.post("/administrador/agregarTerminal", controladorAdmin::agregarTerminal,engine);
	Spark.get("/terminal/principal", controladorTerminal::mostrarPrincipalTerminal,engine);
	Spark.get("/terminal/buscarPois", controladorTerminal::mostrarBusquedaPois,engine);
	Spark.post("/terminal/buscarPois", controladorTerminal::buscarPoisTerminal,engine);
	Spark.get("/terminal/pois/:id", controladorTerminal::mostrarDetallePoi,engine);
	}
	public static Users getUser() {
		return u1;
	}
	public static void setUser(Users u1) {
		Router.u1 = u1;
	}
}
