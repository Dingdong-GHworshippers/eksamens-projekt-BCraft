package dk.ek.bcrafteksamensprojekt.dto.Case;

import dk.ek.bcrafteksamensprojekt.model.Type;

import java.util.List;

public record CaseRequestDTO(String title, String description, Type type, Long customerId, List<CaseMaterialRequestDTO> materials) {
}
