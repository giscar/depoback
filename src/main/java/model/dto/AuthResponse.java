package model.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuthResponse {

	String tokenType;

	String accessToken;

	Long expiresIn;
}
