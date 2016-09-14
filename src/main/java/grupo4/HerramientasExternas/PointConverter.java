package grupo4.HerramientasExternas;

import javax.persistence.AttributeConverter;

import org.uqbar.geodds.Point;

public class PointConverter implements AttributeConverter<Point, Punto>{

	@Override
	public Punto convertToDatabaseColumn(Point attribute) {
		return new Punto(attribute.latitude(), attribute.longitude());
	}

	@Override
	public Point convertToEntityAttribute(Punto dbData) {
		return new Point(dbData.getLatitud(), dbData.getLongitud());
	}

}
