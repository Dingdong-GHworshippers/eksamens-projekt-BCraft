package dk.ek.bcrafteksamensprojekt.dto.Case;

import dk.ek.bcrafteksamensprojekt.model.Type;

import java.time.LocalDate;
import java.util.List;

public record CaseResponseDTO(Long id, String title, String description, LocalDate createdAt, Type type, Long customerId, List<CaseMaterialResponseDTO> materials) {
}
