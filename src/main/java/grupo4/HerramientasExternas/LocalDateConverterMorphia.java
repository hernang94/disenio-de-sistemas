package grupo4.HerramientasExternas;

import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.mongodb.morphia.converters.SimpleValueConverter;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;

public class LocalDateConverterMorphia extends TypeConverter implements SimpleValueConverter {
    /**
     * Creates the Converter.
     */
    public LocalDateConverterMorphia() {
        super(LocalDate.class);
    }

    @Override
    public Object decode(final Class<?> targetClass, final Object val, final MappedField optionalExtraInfo) {
        if (val == null) {
            return null;
        }

        if (val instanceof LocalDate) {
            return val;
        }

        if (val instanceof Date) {
            return LocalDateTime.ofInstant(((Date) val).toInstant(), ZoneId.systemDefault()).toLocalDate();
        }

        throw new IllegalArgumentException("Can't convert to LocalDate from " + val);
    }

    @Override
    public Object encode(final Object value, final MappedField optionalExtraInfo) {
        if (value == null) {
            return null;
        }
        LocalDate date = (LocalDate) value;
        return Date.from(date.atStartOfDay()
                             .atZone(ZoneId.systemDefault())
                             .toInstant());
    }
}