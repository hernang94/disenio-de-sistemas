package grupo4.ComponentesExternos;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Http {
	private HttpClient cliente;
	private HttpGet get;
	private HttpResponse response;
	
	public Http(String direccion) {
		cliente = new DefaultHttpClient();
		get = new HttpGet(direccion);
		response=null;
	}

	public String obtenerString() throws ParseException, IOException{
		obtenerDataHttp();
		return EntityUtils.toString(response.getEntity());
	}
	
	public void obtenerDataHttp(){
		try {
		response = cliente.execute(get);
		} catch (ClientProtocolException e1) {
			throw new RuntimeException("Error ClientProtocolException");
		} catch (IOException e1) {
			throw new RuntimeException("Error IOException");
		}
		
	}

}
