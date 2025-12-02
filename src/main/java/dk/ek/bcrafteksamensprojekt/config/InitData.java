package dk.ek.bcrafteksamensprojekt.config;

import dk.ek.bcrafteksamensprojekt.model.*;
import dk.ek.bcrafteksamensprojekt.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {

    private final MaterialService materialService;
    private final CustomerService customerService;
    private final CaseService caseService;
    private final OfferRequestService offerRequestService;
    private final UserAuthenticationService userAuthenticationService;

    @Override
    public void run(String... args) {

        // -------------------------
        // USERS
        // -------------------------
        userAuthenticationService.createUser("john", "password123");
        userAuthenticationService.createUser("sofie", "password123");


        // -------------------------
        // MATERIALS
        // -------------------------
        Material walnut = materialService.createMaterial(
                new Material(null, "Black Walnut Hardwood", 975.0, "m2")
        );

        Material spruce = materialService.createMaterial(
                new Material(null, "Spruce Timber", 280.0, "m")
        );

        Material plywood12 = materialService.createMaterial(
                new Material(null, "Plywood 12mm", 1200.0, "m2")
        );


        // -------------------------
        // CUSTOMERS
        // -------------------------
        Customer c1 = customerService.createCustomer(
                new Customer(null, "Henrik", "Poulsen", "421123123",
                        "henrik@example.com", "Hasselvej 42", "7100", "Vejle")
        );

        Customer c2 = customerService.createCustomer(
                new Customer(null, "Camilla", "Thomsen", "513123123",
                        "camilla@example.com", "Skovvej 21", "7100", "Vejle")
        );


        // -------------------------
        // CASES
        // -------------------------
        Case shoeRack = new Case();
        shoeRack.setTitle("Entryway Shoe Rack");
        shoeRack.setDescription("Compact walnut shoe rack with two shelves.");
        shoeRack.setCreatedAt(LocalDate.now().minusDays(14));

        shoeRack = caseService.createCase(c1.getId(), shoeRack);

        Case diningBench = new Case();
        diningBench.setTitle("Dining Room Bench");
        diningBench.setDescription("Long spruce bench with Scandinavian style.");
        diningBench.setCreatedAt(LocalDate.now().minusDays(5));

        diningBench = caseService.createCase(c2.getId(), diningBench);


        // -------------------------
        // CASE MATERIALS
        // -------------------------
        caseService.addMaterialToCase(shoeRack.getId(),
                new CaseMaterial(null, "Walnut shelves", 2, 975.0, null, walnut)
        );

        caseService.addMaterialToCase(shoeRack.getId(),
                new CaseMaterial(null, "Support rails", 3, 975.0, null, walnut)
        );

        caseService.addMaterialToCase(diningBench.getId(),
                new CaseMaterial(null, "Spruce planks", 4, 280.0, null, spruce)
        );

        caseService.addMaterialToCase(diningBench.getId(),
                new CaseMaterial(null, "Plywood underside", 2, 1200.0, null, plywood12)
        );


        // -------------------------
        // OFFER REQUESTS
        // -------------------------
        offerRequestService.createOfferRequest(
                new OfferRequest(null,
                        "Martin", "Due",
                        "33445522",
                        "martin@example.com",
                        "Looking for a custom wall-mounted bookshelf.")
        );

        offerRequestService.createOfferRequest(
                new OfferRequest(null,
                        "Kristina", "Ravn",
                        "60607788",
                        "kristina@example.com",
                        "Request for a built-in wardrobe with white finish.")
        );

        System.out.println("Testdata loaded ✔");
    }
}