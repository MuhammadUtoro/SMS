package dto.trainer;


import java.util.UUID;

public record CreateTrainerResponseDTO(
                Long trainerId,
                UUID keycloakUserId,
                String firstName,
                String lastName
                ) {
}
