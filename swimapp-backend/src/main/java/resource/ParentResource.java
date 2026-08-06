package resource;

import java.util.List;

import dto.parent.ParentInfoDTO;
import dto.parent.ParentRegistrationRequestDTO;
import dto.parent.ParentRegistrationResponseDTO;
import dto.parent.ParentSummaryDTO;
import dto.parent.UpdateParentInfoDTO;
import dto.swimmer.SwimmerSummaryDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
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

  @GET
  @RolesAllowed("ADMIN")
  public Response getParentsList(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("size") @DefaultValue("20") int size
      ) {
    List<ParentSummaryDTO> parents = parentService.getParentsList(page, size);
    return Response.ok(parents).build();
      }

  @GET
  @Path("/{parentId}")
  @RolesAllowed("ADMIN")
  public Response getParentById(@PathParam("parentId") Long parentId) {
    ParentSummaryDTO parentDTO = parentService.getParentById(parentId);
    return Response.ok(parentDTO).build();
  }

  @POST
  @Path("/{parentId}")
  @RolesAllowed("ADMIN")
  public Response UpdateParentInfoEntity(@PathParam("parentId") Long parentId, UpdateParentInfoDTO dto) {
    ParentSummaryDTO updatedDTO = parentService.UpdateParentInfoEntity(parentId, dto);
    return Response.ok(updatedDTO).build();
  }

  // For profile
  @GET
  @Path("/me/swimmers")
  @RolesAllowed("PARENT")
  public Response getMySwimmers() {
    List<SwimmerSummaryDTO> swimmers = parentService.getMySwimmers();
    return Response.ok(swimmers).build();
  }

  @GET
  @Path("/me")
  @RolesAllowed("PARENT")
  public Response getMyProfile() {
    ParentInfoDTO parentDTO = parentService.getMyProfile();
    return Response.ok(parentDTO).build();
  }

  @PUT
  @Path("/me")
  @RolesAllowed("PARENT")
  public Response updateMyProfile(UpdateParentInfoDTO dto) {
    ParentSummaryDTO updatedDTO = parentService.updateMyProfile(dto);
    return Response.ok(updatedDTO).build();
  }
}
