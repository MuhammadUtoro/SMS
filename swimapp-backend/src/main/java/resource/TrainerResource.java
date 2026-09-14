package resource;

import java.util.List;

import dto.trainer.CreateTrainerResponseDTO;
import dto.trainer.TrainerRegistrationRequestDTO;
import dto.trainer.TrainerSummaryDTO;
import dto.trainer.UpdateTrainerInfoDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.TrainerService;

@Path("/trainers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TrainerResource {
  
  @Inject
  TrainerService trainerService;

  // Register trainer
  @POST
  @Path("/register")
  @RolesAllowed("ADMIN")
  public Response registerTrainer(TrainerRegistrationRequestDTO dto) {
    CreateTrainerResponseDTO trainerDTO = trainerService.registerTrainer(
        dto
        );
    return Response.ok(trainerDTO).build();
  }

  // Retrieve all trainers
  @GET
  @RolesAllowed("ADMIN")
  public Response getTrainersList(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("size") @DefaultValue("20") int size
      ) {
    List<TrainerSummaryDTO> trainers = trainerService.getTrainersList(page, size);
    return Response.status(Response.Status.OK).entity(trainers).build();
      }
  // Retrieve trainer by ID
  @GET
  @Path("/{trainer_id}")
  @RolesAllowed("ADMIN")
  public Response getTrainerById(@PathParam("trainer_id") Long trainer_id) {
    TrainerSummaryDTO trainerDTO = trainerService.getTrainerById(trainer_id);
    return Response.ok(trainerDTO).build();
  }
  // UpdateTrainer
  @PUT
  @Path("/{trainer_id}")
  @RolesAllowed("ADMIN")
  public Response updateTrainerInfoEntity(@PathParam("trainer_id") Long trainer_id, UpdateTrainerInfoDTO dto) {
    TrainerSummaryDTO updatedDTO = trainerService.updateTrainerInfoEntity(trainer_id, dto);  
    return Response.ok(updatedDTO).build();
  }
  
  // Delete Trainer
  @DELETE
  @Path("/{trainer_id}")
  @RolesAllowed("ADMIN")
  public Response deleteTrainer(@PathParam("trainer_id") Long trainer_id) {
    trainerService.deleteTrainer(trainer_id);
    return Response.noContent().build(); 
  }

  @GET
  @Path("/me")
  @RolesAllowed("TRAINER")
  public Response getMyProfile() {
    TrainerSummaryDTO trainerDTO = trainerService.getMyProfile();
    return Response.ok(trainerDTO).build();
  }

  @PUT
  @Path("/me")
  @RolesAllowed("TRAINER")
  public Response updateMyProfile(UpdateTrainerInfoDTO dto) {
    TrainerSummaryDTO updatedDTO = trainerService.updateMyProfile(dto);
    return Response.ok(updatedDTO).build();
  }
}
