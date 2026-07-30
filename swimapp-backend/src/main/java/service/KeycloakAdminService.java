package service;

import java.util.UUID;
import java.util.List;

import dto.parent.ParentRegistrationRequestDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;

@ApplicationScoped
public class KeycloakAdminService {

    private Keycloak keycloak;

    // Keycloak Admin
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
    
    // Create user
    public UUID createUser(ParentRegistrationRequestDTO dto) {
        UserRepresentation user =  new UserRepresentation();

        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setEnabled(true);

        // Set password
        CredentialRepresentation credential =  new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(dto.password());
        credential.setTemporary(false);

        user.setCredentials(List.of(credential));

        Response response = keycloak.realm("dio-project").users().create(user);
        if (response.getStatus() != 201) {
            throw new RuntimeException("Failed to create Keycloak user!");
        }

        // Fetch user ID
        String location = response.getHeaderString("Location");
        
        String id = location.substring(
                location.lastIndexOf('/') + 1
                );

        // Add role - PARENT
        RoleRepresentation parentRole = keycloak.realm("dio-project")
                                                .roles()
                                                .get("PARENT")
                                                .toRepresentation();
        // Assign role - PARENT
        keycloak.realm("dio-project")
                .users()
                .get(id) // Id that we fetch
                .roles()
                .realmLevel()
                .add(List.of(parentRole));

        return UUID.fromString(id);
    }

}
