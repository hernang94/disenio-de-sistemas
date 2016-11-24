package controllers;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import grupo4.Repositorios.RepositorioCuentas;
import grupo4.Server.Router;
import grupo4.Usuarios.Users;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController implements WithGlobalEntityManager {
	Users u1;
	public  ModelAndView logeo(Request req, Response res){
		return new ModelAndView(null, "login.hbs");
	}
	public  Void Ingreso(Request req, Response res){
		u1= RepositorioCuentas.getInstancia().buscarUsuario(req.queryParams("usuario").toString(), req.queryParams("contrasenia").toString());
		if (u1!=null){
			Router.setUser(req.session().id(),u1);
			if(u1.getDecriminatorValue().equalsIgnoreCase("ADMINISTRADOR"))
			res.redirect("/administrador/principal");
			else{
				Router.setUser(req.session().id(),u1);
				res.redirect("/terminal/principal");
			}
		}
		res.redirect("/login/logueoIncorrecto");
		return null;
	}
	public Void logout(Request req, Response res){
		Router.deleteUser(req.session().id());;
		res.redirect("/");
		return null;
	}
	public  ModelAndView mostrarLogueoIncorrecto(Request req, Response res){
		return new ModelAndView(null, "logueoIncorrecto.hbs");
	}
}
