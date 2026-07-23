package resource;

import java.util.List;

import dto.level.CreateLevelDTO;
import dto.level.CreateLevelResponseDTO;
import dto.level.LevelSummaryDTO;
import dto.level.UpdateLevelInfoDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.LevelService;

@Path("/levels")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LevelResource {

  @Inject
  LevelService levelService;

  // Create Level - POST
  @POST
  public Response createLevel(CreateLevelDTO dto) {
    CreateLevelResponseDTO levelDTO = levelService.createLevel(dto);
    return Response.status(Response.Status.CREATED).entity(levelDTO).build();
  }

  // Retrieve all levels - GET
  @GET
  public Response getLevelsList(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("size") @DefaultValue("20") int size) {
    List<LevelSummaryDTO> levels = levelService.getLevelsList(page, size);
    return Response.ok(levels).build();
  }

  // Retrieve level by ID - GET
  @GET
  @Path("/{level_id}")
  public Response getLevelById(@PathParam("level_id") Long level_id) {
    LevelSummaryDTO level = levelService.getLevelById(level_id);
    return Response.ok(level).build();
  }

  // Update Level info - PUT
  @PUT
  @Path("/{level_id}")
  public Response updateLevelInfoEntity(@PathParam("level_id") Long level_id, UpdateLevelInfoDTO dto) {
    LevelSummaryDTO updatedDTO = levelService.updateLevelInfoEntity(level_id, dto);
    return Response.ok(updatedDTO).build();
  }

  @DELETE
  public Response deleteLevel(@PathParam("level_id") Long level_id) {
    levelService.deleteLevel(level_id);
    return Response.noContent().build();
  }
}
