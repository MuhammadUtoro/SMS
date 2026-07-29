package resource;

import dto.parent.ParentRegistrationRequestDTO;
import dto.parent.ParentRegistrationResponseDTO;
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
    ParentRegistrationResponseDTO responseDTO = parentService.registerParent(dto);
    return Response.ok(responseDTO).build();
  }

}
