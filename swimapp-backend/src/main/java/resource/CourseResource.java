package resource;

import java.util.List;

import dto.course.CourseSummaryDTO;
import dto.course.CreateCourseDTO;
import dto.course.CreateCourseResponseDTO;
import dto.course.UpdateCourseInfoDTO;
import dto.course.UpdateCourseLevelDTO;
import dto.course.UpdateCourseTrainerDTO;
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
import service.CourseService;

@Path("/courses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CourseResource {

  @Inject
  CourseService courseService;

  @POST
  public Response createCourse(CreateCourseDTO dto) {
    CreateCourseResponseDTO courseDTO = courseService.createCourse(dto);
    return Response.status(Response.Status.CREATED).entity(courseDTO).build();
  }

  @GET
  public Response getCoursesList(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("size") @DefaultValue("20") int size) {
    List<CourseSummaryDTO> courses = courseService.getCoursesList(page, size);
    return Response.ok(courses).build();
  }

  @GET
  @Path("/{course_id}")
  public Response getCourseById(@PathParam("course_id") Long course_id) {
    CourseSummaryDTO course = courseService.getCourseById(course_id);
    return Response.ok(course).build();
  }

  @PUT
  @Path("/{course_id}")
  public Response updateCourseInfoEntity(@PathParam("course_id") Long course_id, UpdateCourseInfoDTO dto) {
    CourseSummaryDTO updatedDTO = courseService.updateCourseInfoEntity(course_id, dto);
    return Response.ok(updatedDTO).build();
  }
  
  @PATCH
  @Path("/{course_id}/level")
  public Response updateCourseLevel(@PathParam("course_id") Long course_id, UpdateCourseLevelDTO dto) {
    CourseSummaryDTO updatedDTO = courseService.updateCourseLevel(course_id, dto);
    return Response.ok(updatedDTO).build();
  }

  @PATCH
  @Path("/{course_id}/trainer")
  public Response updateCourseTrainer(@PathParam("course_id") Long course_id, UpdateCourseTrainerDTO dto) {
    CourseSummaryDTO updatedDTO = courseService.updateCourseTrainer(course_id, dto);
    return Response.ok(updatedDTO).build();
  }

  @DELETE
  @Path("/{course_id}")
  public Response deleteCourse(@PathParam("course_id") Long course_id) {
    courseService.deleteCourse(course_id);
    return Response.noContent().build();
  }
}
