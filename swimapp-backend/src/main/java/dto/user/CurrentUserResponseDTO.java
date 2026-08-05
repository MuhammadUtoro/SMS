package dto.user;

import java.util.UUID;
import java.util.Set;

public record CurrentUserResponseDTO(
    String username,
    UUID keycloakUserId,
    Set<String> roles
    ) {
}
