package dto.user;

public record UserRegistrationDTO(
        String email,
        String firstName,
        String lastName,
        String username,
        String password
        ) {
    
}
