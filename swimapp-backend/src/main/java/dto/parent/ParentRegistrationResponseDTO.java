package dto.parent;

import java.util.List;

public record ParentRegistrationResponseDTO(
                String email,
                String firstName,
                String lastName,
                String username,
                List<String> roles) {
}
