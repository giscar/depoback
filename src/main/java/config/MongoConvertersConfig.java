package config;

import domain.type.EstadoMontacarga;
import domain.type.EstadoRegister;
import domain.type.EstadoRevision;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.List;

@Configuration
public class MongoConvertersConfig {

	@Bean
	public MongoCustomConversions mongoCustomConversions() {
		return new MongoCustomConversions(List.of(
				new EstadoRevisionReadConverter(),
				new EstadoMontacargaReadConverter(),
				new EstadoRegisterReadConverter()
		));
	}

	@Slf4j
	@ReadingConverter
	static class EstadoRevisionReadConverter implements Converter<String, EstadoRevision> {
		@Override
		public EstadoRevision convert(String source) {
			return parseEnum(source, EstadoRevision.class);
		}
	}

	@Slf4j
	@ReadingConverter
	static class EstadoMontacargaReadConverter implements Converter<String, EstadoMontacarga> {
		@Override
		public EstadoMontacarga convert(String source) {
			return parseEnum(source, EstadoMontacarga.class);
		}
	}

	@Slf4j
	@ReadingConverter
	static class EstadoRegisterReadConverter implements Converter<String, EstadoRegister> {
		@Override
		public EstadoRegister convert(String source) {
			return parseEnum(source, EstadoRegister.class);
		}
	}

	private static <T extends Enum<T>> T parseEnum(String source, Class<T> enumType) {
		if (source == null || source.trim().isEmpty()) {
			return null;
		}

		try {
			return Enum.valueOf(enumType, source.trim());
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
