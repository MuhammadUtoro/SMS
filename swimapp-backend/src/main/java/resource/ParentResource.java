package resource;

import java.util.Map;

import dto.parent.ParentRegistrationRequestDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import service.ParentService;

@Path("/parents")
public class ParentResource {
  
  @Inject
  ParentService parentService;

  @POST
  @Path("/register")
  public Response registerParent(ParentRegistrationRequestDTO dto) {
    return Response.ok(Map.of(
          "message", "registered"
          )).build();
  }

}
