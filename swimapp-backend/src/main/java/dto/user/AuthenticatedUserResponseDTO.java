package dto.user;

import java.util.UUID;
import java.util.Set;

public record AuthenticatedUserResponseDTO(
    String username,
    UUID keycloak_user_id,
    Set<String> roles
    ) {
}
