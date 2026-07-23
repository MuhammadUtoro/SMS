package dto.swimmer;


// This DTO is for parent adding their swimmer(s)
// for request(POST)
public record CreateSwimmerDTO(
        String first_name,
        String last_name
        ) {

}
