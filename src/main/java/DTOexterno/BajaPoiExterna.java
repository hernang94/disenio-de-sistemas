package DTOexterno;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BajaPoiExterna {
private int id;
private String fecha;


public BajaPoiExterna(int id, String fecha) {
	super();
	this.id = id;
	this.fecha = fecha;
}

public BajaPoiExterna(){
	
}
public int getId() {
	return id;
}
@JsonProperty("id")
public void setId(int id) {
	this.id = id;
}
@JsonProperty("status_code")
public void setIdFake(int status_code){
	this.id=status_code;
}
public String getFecha() {
	return fecha;
}
@JsonProperty("deletedAt")
public void setFecha(String fecha) {
	this.fecha = fecha;
}
@JsonProperty("error")
public void setFechaFake(String error){
	this.fecha=error;
}

}
