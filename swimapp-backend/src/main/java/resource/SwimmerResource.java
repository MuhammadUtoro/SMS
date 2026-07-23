package resource;

import java.util.List;

import dto.swimmer.CreateSwimmerDTO;
import dto.swimmer.CreateSwimmerResponseDTO;
import dto.swimmer.SwimmerSummaryDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.DefaultValue;
import service.SwimmerService;

@Path("/swimmers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SwimmerResource {

  @Inject
  SwimmerService swimmerService;

  @POST
  public Response createSwimmer(CreateSwimmerDTO dto) {
    CreateSwimmerResponseDTO swimmerDTO = swimmerService.createSwimmer(dto);
    return Response.status(Response.Status.CREATED).entity(swimmerDTO).build();
  }

  @GET
  public Response getSwimmersList(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("size") @DefaultValue("20") int size) {
    List<SwimmerSummaryDTO> swimmers = swimmerService.getSwimmersList(page, size);
    return Response.status(Response.Status.OK).entity(swimmers).build();
  }

}
