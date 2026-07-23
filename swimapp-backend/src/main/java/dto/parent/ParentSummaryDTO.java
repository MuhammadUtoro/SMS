package dto.parent;

import java.util.List;
import entity.Swimmer;

// We set the Parent DTO only to show the email when they login
public record ParentSummaryDTO(
        String email,
        List<Swimmer> swimmers) {
}
