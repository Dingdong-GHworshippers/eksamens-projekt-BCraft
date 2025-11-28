package dk.ek.bcrafteksamensprojekt.config;

import dk.ek.bcrafteksamensprojekt.model.*;
import dk.ek.bcrafteksamensprojekt.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {

    private final MaterialService materialService;
    private final CustomerService customerService;
    private final CaseService caseService;
    private final OfferRequestService offerRequestService;
    private final UserAuthenticationService userAuthenticationService;

    @Override
    public void run(String... args) throws Exception {

        // --- USERS ---
        User u1 = userAuthenticationService.createUser("John", "NielsenEnjoyer3");
        u1.setFirstName("John");
        u1.setLastName("Nielsen");

        User u2 = userAuthenticationService.createUser("sofie", "oaklover2");
        u2.setFirstName("Sofie");
        u2.setLastName("Nørgaard");

        userAuthenticationService.createUser(u1.getUsername(), u1.getPassword());
        userAuthenticationService.createUser(u2.getUsername(), u2.getPassword());


        // --- MATERIALS ---
        Material walnut = new Material(1L, "Black Walnut Hardwood", 975.0,"m2");


        Material spruce = new Material(2L, "Spruce Timber", 280.0,"m");

        Material plywood12 = new Material(3L, "Plywood 12", 1200.0,"m");


        materialService.createMaterial(walnut);
        materialService.createMaterial(spruce);
        materialService.createMaterial(plywood12);


        // --- CUSTOMERS ---
        Customer c1 = new Customer(1L,"Henrik", "poulsen", "421123123", "Henrik.poulasd@gmail.com", "hasselvej 42", "7100", "Vejle");


        Customer c2 = new Customer(2L,"Camilla", "Thomsen", "513123123", "Camilla.gasd@gmail.com", "camilla 421", "7100", "Vejle");


        customerService.createCustomer(c1);
        customerService.createCustomer(c2);


        // --- CASES ---
        Case shoeRack = new Case(
                1L,
                "Entryway Shoe Rack",
                "Compact walnut shoe rack with two shelves.",
                LocalDate.now().minusDays(14),
                c1,
                null
        );

        Case diningBench = new Case(
                2L,
                "Dining Room Bench",
                "Long spruce bench with clean Scandinavian lines.",
                LocalDate.now().minusDays(5),
                c2,
                null
        );

        // --- CASE MATERIALS ---
        CaseMaterial scm1 = new CaseMaterial(
                3L,
                "Walnut shelves (cut to size)",
                2,
                975.0,
                shoeRack,
                walnut
                );

        CaseMaterial scm2 = new CaseMaterial(
                4L,
                "Walnut support rails",
                3,
                975.0,
                shoeRack,
                walnut
                );

        CaseMaterial dcm1 = new CaseMaterial(
                5L,
                "Spruce planks for bench seat",
                4,
                280.0,
                diningBench,
                spruce
                );

        CaseMaterial dcm2 = new CaseMaterial(
                6L,
                "12mm birch plywood for underside supports",
                2,
                190.0,
                diningBench,
                walnut
                );

        materialService.createMaterial(scm1.getMaterial());
        materialService.createMaterial(scm2.getMaterial());
        materialService.createMaterial(dcm1.getMaterial());
        materialService.createMaterial(dcm2.getMaterial());

        shoeRack.setCaseMaterials(List.of(scm1, scm2));
        diningBench.setCaseMaterials(List.of(dcm1, dcm2));

        caseService.createCase(shoeRack.getId(), shoeRack);
        caseService.createCase(diningBench.getId(), diningBench);


        // --- OFFER REQUESTS ---
        OfferRequest or1 = new OfferRequest(
                "Martin",
                "Due",
                "33445522",
                "martin.d@example.com",
                "Looking for a custom wall-mounted bookshelf in ash wood."
        );

        OfferRequest or2 = new OfferRequest(
                "Kristina",
                "Ravn",
                "60607788",
                "kristina.r@example.com",
                "Request for a built-in wardrobe, white painted finish."
        );

        offerRequestService.createOfferRequest(or1);
        offerRequestService.createOfferRequest(or2);
    }
}
