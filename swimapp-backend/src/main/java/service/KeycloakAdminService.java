package service;

import java.util.List;
import java.util.UUID;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import dto.user.UserRegistrationDTO;
import enums.UserRole;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

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
    public UUID createUser(UserRegistrationDTO dto, UserRole role) {
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

        // Add rolek
        RoleRepresentation roleRepresentation = keycloak.realm("dio-project")
                                                .roles()
                                                .get(role.name())
                                                .toRepresentation();
        // Assign role
        keycloak.realm("dio-project")
                .users()
                .get(id) // Id that we fetch
                .roles()
                .realmLevel()
                .add(List.of(roleRepresentation));

        return UUID.fromString(id);
    }

    // Updating Email for Parent
    public void updateEmail(UUID keycloakUserId, String email) {
        UserRepresentation user = keycloak.realm("dio-project")
                .users()
                .get(keycloakUserId.toString())
                .toRepresentation();

        user.setEmail(email);
        keycloak.realm("dio-project")
                .users()
                .get(keycloakUserId.toString())
                .update(user);

    }

    // Updating First and Last Name for trainer
    public void updateTrainerInfo(UUID keycloakUserId, String firstName, String lastName) {
        UserRepresentation user = keycloak.realm("dio-project")
                .users()
                .get(keycloakUserId.toString())
                .toRepresentation();
        
        user.setFirstName(firstName);
        user.setLastName(lastName);
        keycloak.realm("dio-project")
                .users()
                .get(keycloakUserId.toString())
                .update(user);
    }

    // Delete user
    public void deleteUser(UUID keycloakUserId) {
        keycloak.realm("dio-project")
                .users()
                .get(keycloakUserId.toString())
                .remove();
    }
}
