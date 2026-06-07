package config.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtService {

	private static final String HMAC_ALGORITHM = "HmacSHA256";

	private final ObjectMapper objectMapper;

	@Value("${security.jwt.secret}")
	private String secret;

	@Value("${security.jwt.expiration-seconds:3600}")
	private Long expirationSeconds;

	public String generateToken(String subject, List<String> roles) {
		long now = Instant.now().getEpochSecond();

		Map<String, Object> header = Map.of(
				"alg", "HS256",
				"typ", "JWT"
		);
		Map<String, Object> payload = new HashMap<>();
		payload.put("sub", subject);
		payload.put("roles", roles);
		payload.put("iat", now);
		payload.put("exp", now + expirationSeconds);

		String unsignedToken = encodeJson(header) + "." + encodeJson(payload);
		return unsignedToken + "." + sign(unsignedToken);
	}

	public JwtPayload validate(String token) {
		String[] parts = token.split("\\.");
		if (parts.length != 3) {
			throw new IllegalArgumentException("Token JWT invalido");
		}

		String unsignedToken = parts[0] + "." + parts[1];
		if (!constantTimeEquals(sign(unsignedToken), parts[2])) {
			throw new IllegalArgumentException("Firma JWT invalida");
		}

		Map<String, Object> payload = decodePayload(parts[1]);
		long exp = ((Number) payload.get("exp")).longValue();
		if (Instant.now().getEpochSecond() >= exp) {
			throw new IllegalArgumentException("Token JWT expirado");
		}

		String subject = (String) payload.get("sub");
		List<String> roles = objectMapper.convertValue(payload.get("roles"), new TypeReference<>() {});
		return new JwtPayload(subject, roles);
	}

	public Long getExpirationSeconds() {
		return expirationSeconds;
	}

	private String encodeJson(Map<String, Object> value) {
		try {
			byte[] json = objectMapper.writeValueAsBytes(value);
			return Base64.getUrlEncoder().withoutPadding().encodeToString(json);
		} catch (Exception e) {
			throw new IllegalStateException("No se pudo construir el JWT", e);
		}
	}

	private Map<String, Object> decodePayload(String payload) {
		try {
			byte[] json = Base64.getUrlDecoder().decode(payload);
			return objectMapper.readValue(json, new TypeReference<>() {});
		} catch (Exception e) {
			throw new IllegalArgumentException("Payload JWT invalido", e);
		}
	}

	private String sign(String unsignedToken) {
		try {
			Mac mac = Mac.getInstance(HMAC_ALGORITHM);
			mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), HMAC_ALGORITHM));
			byte[] signature = mac.doFinal(unsignedToken.getBytes(StandardCharsets.UTF_8));
			return Base64.getUrlEncoder().withoutPadding().encodeToString(signature);
		} catch (Exception e) {
			throw new IllegalStateException("No se pudo firmar el JWT", e);
		}
	}

	private boolean constantTimeEquals(String expected, String actual) {
		return MessageDigestUtil.constantTimeEquals(
				expected.getBytes(StandardCharsets.UTF_8),
				actual.getBytes(StandardCharsets.UTF_8)
		);
	}

	public record JwtPayload(String subject, List<String> roles) {
	}
}
