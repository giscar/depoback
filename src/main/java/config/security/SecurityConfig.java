package config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	private final ReactiveAuthenticationManager authenticationManager;

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		AuthenticationWebFilter jwtFilter = new AuthenticationWebFilter(authenticationManager);
		jwtFilter.setServerAuthenticationConverter(new BearerTokenAuthenticationConverter());

		return http
				.csrf(ServerHttpSecurity.CsrfSpec::disable)
				.httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
				.formLogin(ServerHttpSecurity.FormLoginSpec::disable)
				.authorizeExchange(exchange -> exchange
						.pathMatchers(HttpMethod.OPTIONS).permitAll()
						.pathMatchers("/auth/login").permitAll()
						.pathMatchers(HttpMethod.GET, "/montacargas/**").authenticated()
						.pathMatchers("/montacargas/**").hasRole("ADMIN")
						.anyExchange().permitAll()
				)
				.addFilterAt(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION)
				.build();
	}

	private static class BearerTokenAuthenticationConverter implements ServerAuthenticationConverter {

		@Override
		public Mono<org.springframework.security.core.Authentication> convert(ServerWebExchange exchange) {
			String authorization = exchange.getRequest().getHeaders().getFirst("Authorization");
			if (authorization == null || !authorization.startsWith("Bearer ")) {
				return Mono.empty();
			}

			String token = authorization.substring(7);
			return Mono.just(new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(token, token));
		}
	}
}
