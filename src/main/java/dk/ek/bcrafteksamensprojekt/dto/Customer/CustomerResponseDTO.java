package dk.ek.bcrafteksamensprojekt.dto.Customer;

public record CustomerResponseDTO(Long id, String firstName, String lastName, String email, String phoneNumber, String address, String zipCode, String city) {
}
