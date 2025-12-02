package dk.ek.bcrafteksamensprojekt.dto;

public record CaseRequestDTO(String title, String description, String type, Long customerId, List<CaseMaterialRequestDTO> materials) {
}
