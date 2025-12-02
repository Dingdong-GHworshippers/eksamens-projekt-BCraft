package dk.ek.bcrafteksamensprojekt.dto;

import java.time.LocalDate;
import java.util.List;

public record CaseResponseDTO(Long id, String title, String description, LocalDate createdAt, String type, Long customerId, List<CaseMaterialResponseDTO> materials) {
}
