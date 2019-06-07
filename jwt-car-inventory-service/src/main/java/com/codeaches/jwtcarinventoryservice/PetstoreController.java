package com.codeaches.jwtcarinventoryservice;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class PetstoreController {

	@GetMapping("pet")
	@PreAuthorize("hasAuthority('AUTHORIZED_PETSTORE_USER')")
	public String pet(Principal principal) {
		return "Hi " + principal.getName() + ". My pet is dog";
	}

	@GetMapping("favouritePet")
	@PreAuthorize("hasAuthority('AUTHORIZED_PETSTORE_ADMIN')")
	public String favouritePet(Principal principal) {
		return "Hi " + principal.getName() + ". My favourite pet is cat";
	}
}
