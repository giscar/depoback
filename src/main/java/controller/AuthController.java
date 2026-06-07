package controller;

import config.security.JwtService;
import lombok.RequiredArgsConstructor;
import model.Perfil;
import model.Rol;
import model.dto.AuthRequest;
import model.dto.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import service.UsuarioService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

	private final UsuarioService usuarioService;

	private final JwtService jwtService;

	@PostMapping("/login")
	public Mono<AuthResponse> login(@RequestBody AuthRequest request) {
		return usuarioService.findByRolesForUser(request.getDocumento(), request.getPasswd())
				.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales invalidas")))
				.map(usuario -> {
					List<String> roles = extractRoles(usuario.getPerfiles());
					String token = jwtService.generateToken(usuario.getDocumento(), roles);
					return AuthResponse.builder()
							.tokenType("Bearer")
							.accessToken(token)
							.expiresIn(jwtService.getExpirationSeconds())
							.build();
				});
	}

	private List<String> extractRoles(List<Perfil> perfiles) {
		if (perfiles == null || perfiles.isEmpty()) {
			return List.of("USER");
		}

		List<String> roles = perfiles.stream()
				.filter(Objects::nonNull)
				.flatMap(perfil -> {
					if (perfil.getRoles() == null || perfil.getRoles().isEmpty()) {
						return java.util.stream.Stream.of(perfil.getCodigo());
					}
					return perfil.getRoles().stream().map(Rol::getCodigo);
				})
				.filter(Objects::nonNull)
				.map(String::trim)
				.filter(role -> !role.isEmpty())
				.map(String::toUpperCase)
				.distinct()
				.toList();

		return roles.isEmpty() ? List.of("USER") : roles;
	}
}
