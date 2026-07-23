package dto.trainer;


import java.util.UUID;

public record CreateTrainerResponseDTO(
                Long trainer_id,
                UUID keycloak_user_id,
                String first_name,
                String last_name
                ) {
}
