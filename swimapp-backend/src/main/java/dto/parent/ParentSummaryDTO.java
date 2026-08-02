package dto.parent;

import java.util.List;
import dto.swimmer.SwimmerSummaryDTO;

// We set the Parent DTO only to show the email when they login
public record ParentSummaryDTO(
        String email,
        List<SwimmerSummaryDTO> swimmers) {
}
