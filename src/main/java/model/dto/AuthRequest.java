package model.dto;

import lombok.Data;

@Data
public class AuthRequest {

	private String documento;

	private String passwd;
}
