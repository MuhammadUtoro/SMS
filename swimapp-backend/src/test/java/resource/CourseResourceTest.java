import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class CourseResourceTest {
    @Test
    void getCoursesList_shouldReturnCourses() {
        given().when().get("/courses").then().statusCode(200).contentType("application/json").body("size()", equalTo(0));
    }
}
