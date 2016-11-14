package grupo4.Server;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Spark;
import spark.Spark.*;
import static spark.Spark.get;
import spark.template.handlebars.HandlebarsTemplateEngine;
public class Ingreso {
	 public static void main(String[] args) {
		 Spark.port(9999);
		 try{
		  Map<String, String> map = new HashMap<String, String>();
	        map.put("name", "Sam");
	        String name = "Puto";

	        // hello.hbs file is in resources/templates directory
	        get("/hello", (rq, rs) -> new ModelAndView(map, "hello_helpers.hbs"), new HandlebarsTemplateEngine());}
		 catch(Exception e){
			 e.printStackTrace();
		 }
	    }
}
