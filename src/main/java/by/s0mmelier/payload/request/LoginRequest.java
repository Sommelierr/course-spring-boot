package by.s0mmelier.payload.request;

import lombok.*;
import lombok.experimental.PackagePrivate;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class LoginRequest {
	@NotBlank
	private String username;

	@NotBlank
	private String password;
}
