package config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtReactiveAuthenticationManager implements ReactiveAuthenticationManager {

	private final JwtService jwtService;

	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		return Mono.fromCallable(() -> {
			try {
				JwtService.JwtPayload payload = jwtService.validate(authentication.getCredentials().toString());
				var authorities = payload.roles().stream()
						.map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
						.map(SimpleGrantedAuthority::new)
						.toList();
				return new UsernamePasswordAuthenticationToken(payload.subject(), authentication.getCredentials(), authorities);
			} catch (RuntimeException e) {
				throw new BadCredentialsException("Token JWT invalido", e);
			}
		});
	}
}
