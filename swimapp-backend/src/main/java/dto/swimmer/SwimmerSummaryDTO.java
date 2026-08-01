package dto.swimmer;

import java.time.LocalTime;

// This DTO is to show swimmer info in the table (without ID)
// for response(GET)
public record SwimmerSummaryDTO(
                Long swimmerId,
                String firstName,
                String lastName,
                String levelName,
                String courseName,
                String courseDay,
                LocalTime courseTime
                ) {

}
