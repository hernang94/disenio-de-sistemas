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
	public  ModelAndView registro(Request req, Response res){
		return new ModelAndView(null, "registro.hbs");
	}
	public  ModelAndView logeo(Request req, Response res){
		return new ModelAndView(null, "login.hbs");
	}
	public  Void Ingreso(Request req, Response res){
		u1= RepositorioCuentas.getInstancia().buscarUsuario(req.queryParams("usuario").toString(), req.queryParams("contrasenia").toString());
		
		if (u1!=null){
			res.redirect("/principalAdmin");
		}
		res.redirect("/logueoIncorrecto");
		return null;
	}
	public  ModelAndView mostrarPrincipalAdmin(Request req, Response res){
		Map<String, Users> model= new HashMap<String, Users>();
		model.put("user", u1);
		return new ModelAndView(model, "principalAdmin.hbs");
	}
	public  ModelAndView mostrarLogueoIncorrecto(Request req, Response res){
		return new ModelAndView(null, "logueoIncorrecto.hbs");
	}
	public Void logout(Request req, Response res){
		u1=null;
		res.redirect("/");
		return null;
	}
}
