package controllers;

import java.util.HashMap;
import java.util.Map;

import grupo4.POIs.Users;
import grupo4.Repositorios.RepositorioCuentas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
//#FFBF00;
public class Controller {
	static Users u1;
	public static ModelAndView registro(Request req, Response res){
		return new ModelAndView(null, "registro.hbs");
	}
	public static ModelAndView logeo(Request req, Response res){
		return new ModelAndView(null, "login.hbs");
	}
	public static Void Ingreso(Request req, Response res){
		System.out.println(req.queryParams("usuario").toString());
		System.out.println(req.queryParams("contrasenia").toString());
		u1= RepositorioCuentas.getInstancia().buscarUsuario(req.queryParams("usuario").toString(), req.queryParams("contrasenia").toString());
		
		if (u1!=null){
			res.redirect("/principalAdmin");
		}
		res.redirect("/login");
		return null;
	}
	public static ModelAndView mostrarPrincipalAdmin(Request req, Response res){
		Map<String, Users> model= new HashMap<String, Users>();
		model.put("user", u1);
		return new ModelAndView(model, "principalAdmin.hbs");
	}
}
