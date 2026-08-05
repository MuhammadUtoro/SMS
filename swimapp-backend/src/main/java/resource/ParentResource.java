package resource;

import java.util.List;

import dto.parent.ParentRegistrationRequestDTO;
import dto.parent.ParentRegistrationResponseDTO;
import dto.parent.ParentSummaryDTO;
import dto.swimmer.SwimmerSummaryDTO;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST; 
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import service.ParentService;

@Path("/parents")
@Authenticated
public class ParentResource {
  
  @Inject
  ParentService parentService;

  @POST
  @Path("/register")
  public Response registerParent(ParentRegistrationRequestDTO dto) {
    ParentRegistrationResponseDTO responseDTO = parentService.registerParent(dto);
    return Response.ok(responseDTO).build();
  }

  @GET
  public Response getParentsList(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("size") @DefaultValue("20") int size
      ) {
    List<ParentSummaryDTO> parents = parentService.getParentsList(page, size);
    return Response.ok(parents).build();
      }

  // For profile

  @GET
  @Path("/me/swimmers")
  public Response getMySwimmers() {
    List<SwimmerSummaryDTO> swimmers = parentService.getMySwimmers();

    return Response.ok(swimmers).build();
  }
}
