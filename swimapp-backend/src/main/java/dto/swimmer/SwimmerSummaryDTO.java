package dto.swimmer;

import java.time.LocalTime;

// This DTO is to show swimmer info in the table (without ID)
// for response(GET)
public record SwimmerSummaryDTO(
                Long swimmer_id,
                String first_name,
                String last_name,
                String level_name,
                String course_name,
                String course_day,
                LocalTime course_time
                ) {

}
