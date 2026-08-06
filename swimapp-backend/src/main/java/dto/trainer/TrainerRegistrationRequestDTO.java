package dto.trainer;

public record TrainerRegistrationRequestDTO(
        String email,
        String firstName,
        String lastName,
        String username,
        String password
        ) {

}
