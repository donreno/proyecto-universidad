package cl.envaflex.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import cl.envaflex.jpa.model.Empleado;

@Service("SecurityWS")
@Path("/security")
public class SecurityWS {

	@Autowired
	@Qualifier("authenticationManager")
	AuthenticationManager authenticationManager;

	@GET
	@Produces("application/json")
	@Path("/status")
	public LoginStatus getStatus() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null && !auth.getName().equals("anonymousUser")
				&& auth.isAuthenticated()) {
			return new LoginStatus(true, auth.getName());
		} else {
			return new LoginStatus(false, null);
		}
	}

	@GET
	@Produces("application/json")
	@Path("/login/{j_username}/{j_password}")
	public LoginStatus login(@PathParam("j_username") String username,
			@PathParam("j_password") String password) {

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				username, password);
		Empleado details = new Empleado();
		details.setUser(username);
		details.setPassword(password);
		token.setDetails(details);

		try {
			Authentication auth = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
			return new LoginStatus(auth.isAuthenticated(), auth.getName());
		} catch (BadCredentialsException e) {
			return new LoginStatus(false, null);
		}
	}

	public class LoginStatus {

		private final boolean loggedIn;
		private final String username;

		public LoginStatus(boolean loggedIn, String username) {
			this.loggedIn = loggedIn;
			this.username = username;
		}

		public boolean isLoggedIn() {
			return loggedIn;
		}

		public String getUsername() {
			return username;
		}
	}

}
