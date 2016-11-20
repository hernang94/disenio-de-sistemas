package grupo4.HerramientasExternas;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.AttributeConverter;

public class LocalTimeConverter implements
AttributeConverter<LocalTime, Timestamp> {

@Override
public Timestamp convertToDatabaseColumn(LocalTime date) {
return Timestamp.valueOf(LocalDateTime.from(date));
}

@Override
public LocalTime convertToEntityAttribute(Timestamp date) {
return date.toLocalDateTime().toLocalTime();
}

}
