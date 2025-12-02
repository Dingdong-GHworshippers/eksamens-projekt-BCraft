package dk.ek.bcrafteksamensprojekt.service;

import dk.ek.bcrafteksamensprojekt.dto.Customer.CustomerMapper;
import dk.ek.bcrafteksamensprojekt.dto.Customer.CustomerRequestDTO;
import dk.ek.bcrafteksamensprojekt.dto.Customer.CustomerResponseDTO;
import dk.ek.bcrafteksamensprojekt.exceptions.NotFoundException;
import dk.ek.bcrafteksamensprojekt.model.Customer;
import dk.ek.bcrafteksamensprojekt.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto) {
        Customer customer = customerMapper.toEntity(dto);
        customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }



    public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO dto) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found: " + id));

        existing.setFirstName(dto.firstName());
        existing.setLastName(dto.lastName());
        existing.setEmail(dto.email());
        existing.setPhoneNumber(dto.phoneNumber());
        existing.setAddress(dto.address());
        existing.setZipCode(dto.zipCode());
        existing.setCity(dto.city());

        customerRepository.save(existing);

        return customerMapper.toDto(existing);
    }

    // Returns custom NotFoundException
    public CustomerResponseDTO findById(Long id) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found: " + id));

        return customerMapper.toDto(c);
    }

    // Filters customers to contain search criteria
    public List<Customer> findCustomersByName(String name){
        return customerRepository.findAll().stream()
                .filter(c -> c.getFullName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<CustomerResponseDTO> findAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toDto)
                .toList();
    }

    public void deleteCustomer(Long id) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found: " + id));

        customerRepository.delete(c);
    }
}
