package resource;

import java.util.List;

import dto.levelrequirement.CreateLevelRequirementDTO;
import dto.levelrequirement.CreateLevelRequirementResponseDTO;
import dto.levelrequirement.LevelRequirementSummaryDTO;
import dto.levelrequirement.UpdateLevelRequirementInfoDTO;
import dto.levelrequirement.UpdateLevelRequirementLevelDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.LevelRequirementService;

@Path("/level-requirements")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LevelRequirementResource {

  @Inject
  LevelRequirementService levelRequirementService;

  @POST
  public Response createLevelRequirement(CreateLevelRequirementDTO dto) {
    CreateLevelRequirementResponseDTO requirementDTO = levelRequirementService.createLevelRequirement(dto);
    return Response.status(Response.Status.CREATED).entity(requirementDTO).build();
  }

  @GET
  public Response getLevelRequirementsList(
      @QueryParam("int") @DefaultValue("0") int page,
      @QueryParam("size") @DefaultValue("20") int size) {
    List<LevelRequirementSummaryDTO> levelRequirements = levelRequirementService.getLevelRequirementsList(page, size);
    return Response.ok(levelRequirements).build();
  }

  @GET
  @Path("/{level_requirement_id}")
  public Response getLevelRequirementById(@PathParam("level_requirement_id") Long level_requirement_id) {
    LevelRequirementSummaryDTO levelRequirement = levelRequirementService.getLevelRequirementById(level_requirement_id);
    return Response.ok(levelRequirement).build();
  }

  @PUT
  @Path("/{level_requirement_id}")
  public Response updateLevelInfoEntity(@PathParam("level_requirement_id") Long level_requirement_id,
      UpdateLevelRequirementInfoDTO dto) {
    LevelRequirementSummaryDTO updatedDTO = levelRequirementService.updateLevelInfoEntity(level_requirement_id, dto);
    return Response.ok(updatedDTO).build();
  }

  @PATCH
  @Path("/{level_requirement_id}/level")
  public Response updateLevelRequirementLevel(@PathParam("level_requirement_id") Long level_requirement_id, UpdateLevelRequirementLevelDTO dto) {
    LevelRequirementSummaryDTO updatedDTO = levelRequirementService.UpdateLevelRequirementLevel(level_requirement_id, dto);
    return Response.ok(updatedDTO).build();
  }

  @DELETE
  @Path("/{level_requirement_id}")
  public Response deleteLevelRequirement(@PathParam("level_requirement_id") Long level_requirement_id) {
    levelRequirementService.deleteLevelRequirement(level_requirement_id);
    return Response.noContent().build();
  }
}
