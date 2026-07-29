package service;

import java.util.UUID;

import dto.parent.ParentRegistrationRequestDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.UserRepresentation;

@ApplicationScoped
public class KeycloakAdminService {

    private Keycloak keycloak;

    public KeycloakAdminService() {
        keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8081")
                .realm("master")
                .clientId("admin-cli")
                .grantType("password")
                .username("admin")
                .password("admin")
                .build();
    }

    public UUID createUser(ParentRegistrationRequestDTO dto) {
        UserRepresentation user =  new UserRepresentation();

        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setEnabled(true);

        Response response = keycloak.realm("dio-project").users().create(user);
        if (response.getStatus() != 201) {
            throw new RuntimeException("Failed to create Keycloak user!");
        }

        String location = response.getHeaderString("Location");
        
        String id = location.substring(
                location.lastIndexOf('/') + 1
                );
        return UUID.fromString(id);
    }

}
