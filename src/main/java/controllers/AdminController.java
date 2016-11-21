package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import grupo4.Acciones.Usuario;
import grupo4.POIs.Banco;
import grupo4.POIs.Poi;
import grupo4.Repositorios.RepositorioCuentas;
import grupo4.Repositorios.RepositorioDePois;
import grupo4.Repositorios.RepositorioDeUsuarios;
import grupo4.Server.Router;
import grupo4.Usuarios.Users;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
//#FFBF00;
public class AdminController implements WithGlobalEntityManager{

	public ModelAndView mostrarPantallaParaListarPois(Request req, Response res){
		return new ModelAndView(null,"Administrador/formListarPois.hbs");
	}

	public  ModelAndView mostrarPrincipalAdmin(Request req, Response res){
		Map<String, Users> model= new HashMap<String, Users>();
		model.put("user",Router.getUser() );
		return new ModelAndView(model, "Administrador/principalAdmin.hbs");
	}

	
	public ModelAndView listarPois(Request req, Response res){
		List<Poi> pois=evaluarRetorno(req.queryParams("nombrePoi").toString(), req.queryParams("Tipos").toString());
		Map<String, List<Poi>> model=new HashMap<>();
		model.put("pois", pois);
		return new ModelAndView(model, "Administrador/listadoPoisAdmin.hbs");
	}
	
	public ModelAndView mostrarPantallaParaListarTerminales(Request req, Response res){
		return new ModelAndView(null,"Administrador/formListarTerminales.hbs");
	}
	public ModelAndView listarTerminales(Request req, Response res){
		List<Usuario> terminales;
		if(req.queryParams("comunas").equalsIgnoreCase("Todas")){
			terminales=RepositorioDeUsuarios.getInstancia().getListaDeUsuarios();
		}
		else{
			terminales=RepositorioDeUsuarios.getInstancia().getListaDeUsuariosXComuna(Integer.parseInt(req.queryParams("comunas").toString()));
		}
		Map<String, List<Usuario>> model=new HashMap<>();
		model.put("terminales", terminales);
		return new ModelAndView(model, "Administrador/listadoTerminales.hbs");
	}
	
	public ModelAndView eliminarPoi(Request req, Response res){
		int id=Integer.parseInt(req.params("id"));
		Poi poi= RepositorioDePois.getInstancia().obtenerPoi(id);
		System.out.println(id);
		try {
			entityManager().getTransaction().begin();
			RepositorioDePois.getInstancia().bajaPoi(poi.getId());
			entityManager().getTransaction().commit();
			return new ModelAndView(null, "Administrador/eliminarExito.hbs");
		} catch (Exception e) {
			Map<String,String> model= new HashMap<>();
			model.put("error", e.getMessage());
			return new ModelAndView(model, "Administrador/eliminarError.hbs");
		}
	}
	public ModelAndView mostarEditarPoi(Request req,Response res){
		int id= Integer.parseInt(req.params("id"));
		Poi poi= RepositorioDePois.getInstancia().obtenerPoi(id);
		Map<String,Poi> model=new HashMap<>();
		model.put("poi", poi);
		return new ModelAndView(model, "Administrador/formEditarPoi.hbs");
	}
	public ModelAndView editarPoi(Request req,Response res){
		int id= Integer.parseInt(req.params("id"));
		try {
			entityManager().getTransaction().begin();
			RepositorioDePois.getInstancia().actualizarPoi(id, req.queryParams("nombre").toString(), req.queryParams("direccion").toString(), 
					Double.parseDouble(req.queryParams("latitud").toString()), Double.parseDouble(req.queryParams("longitud").toString()));
			entityManager().getTransaction().commit();
			return new ModelAndView(null, "Administrador/editarExito.hbs");
		} catch (Exception e) {
			Map<String,String> model= new HashMap<>();
			model.put("error", e.getMessage());
			return new ModelAndView(model, "Administrador/editarError.hbs");
		}
	}
private List<Poi> evaluarRetorno(String nombre,String tipo){
	/*if(nombre.isEmpty()&&tipo.isEmpty()){
		return null;
	}*/
	if(tipo.equalsIgnoreCase("Todos")){
		return RepositorioDePois.getInstancia().busquedaPorNombre(nombre);
	}
	if(nombre.isEmpty()){
		return RepositorioDePois.getInstancia().busquedaPorTipo(tipo);
	}
		return RepositorioDePois.getInstancia().busquedaPorNombre(nombre).stream().filter(poi->poi.getTipo().equalsIgnoreCase(tipo)).collect(Collectors.toList());
}
}
