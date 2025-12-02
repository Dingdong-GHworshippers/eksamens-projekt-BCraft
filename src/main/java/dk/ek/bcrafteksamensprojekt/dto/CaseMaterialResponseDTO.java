package dk.ek.bcrafteksamensprojekt.dto;

public record CaseMaterialResponseDTO(Long id, String description, int quantity, Double unitPrice, Double effectiveUnitPrice, Long materialId) {
}
