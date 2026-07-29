package dto.parent;

import java.util.UUID;

import java.util.List;

public record ParentRegistrationResponseDTO(
                String email,
                String firstName,
                String lastName,
                String username,
                UUID keycloakUserId,
                List<String> roles) {
}
