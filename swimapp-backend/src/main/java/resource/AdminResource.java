package resource;

import java.util.UUID;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import dto.user.AuthenticatedUserResponseDTO;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/me")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
@SecurityRequirement(name="Keycloak")
public class AdminResource {

  @Inject
  SecurityIdentity securityIdentity;

  @Inject
  JsonWebToken jwt;

  @GET
  public Response me() {
   AuthenticatedUserResponseDTO responseDTO = new AuthenticatedUserResponseDTO(
      securityIdentity.getPrincipal().getName(),
      UUID.fromString(jwt.getSubject()),
      securityIdentity.getRoles());
    return Response.ok(responseDTO).build();
  }
}
