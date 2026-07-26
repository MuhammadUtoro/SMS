package resource;

import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;

import java.util.Map;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;

@Path("/api/me")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class AdminResource {

  @Inject
  SecurityIdentity securityIdentity;

  @GET
  public Map<String, Object> me() {
    return Map.of(
        "username", securityIdentity.getPrincipal().getName()
        );
  }
  
}
