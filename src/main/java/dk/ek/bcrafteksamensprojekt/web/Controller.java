package dk.ek.bcrafteksamensprojekt.web;

import dk.ek.bcrafteksamensprojekt.service.UserAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    UserAuthenticationService userAuthenticationService;

    @GetMapping("/")
    public String homePage(){
        return "forward:/index.html";
    }

    @GetMapping("/aboutUs")
    public String contactPage(){
        return "forward:/aboutUs.html";
    }

    @GetMapping("/træprojekter")
    public String woodcraftPage(){
        return "forward:/woodcraft.html";
    }

    @GetMapping("/gulve")
    public String floorPage(){
        return "forward:/flooring.html";
    }

    @GetMapping("/renovering")
    public String renovationPage(){
        return "forward:/renovation.html";
    }

    @GetMapping("/bad")
    public String bathroomPage(){
        return "forward:/bathroom.html";
    }

    @GetMapping("/køkken")
    public String kitchenPage(){
        return "forward:/kitchen.html";
    }

    @GetMapping("/udendørsprojekter")
    public String outdoorPage(){
        return "forward:/outside.html";
    }

    @GetMapping("/portefølje")
    public String folioPage(){
        return "forward:/portfolio.html";
    }

    @GetMapping("/tilbud")
    public String offerPage(){
        return "forward:/offer-request.html";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "forward:/admin/login.html";
    }

    @GetMapping("/cases")
    public String casesPage(HttpServletRequest request){
        if (!userAuthenticationService.validateLogin(request)){
            return "redirect:/login";
        }
        return "forward:/admin/cases.html";
    }

    @GetMapping("/customers")
    public String customersPage(HttpServletRequest request){
        if (!userAuthenticationService.validateLogin(request)){
            return "redirect:/login";
        }
        return "forward:/admin/customers.html";
    }

    @GetMapping("/dashboard")
    public String dashboardPage(HttpServletRequest request){
        if (!userAuthenticationService.validateLogin(request)){
            return "redirect:/login";
        }
        return "forward:/admin/dashboard.html";
    }

    @GetMapping("/materials")
    public String materialsPage(HttpServletRequest request){
        if (!userAuthenticationService.validateLogin(request)){
            return "redirect:/login";
        }
        return "forward:/admin/materials.html";
    }
    @GetMapping("/offers")
    public String offers(HttpServletRequest request) {
        if (!userAuthenticationService.validateLogin(request)){
            return "redirect:/login";
        }
        return "forward:/admin/offers.html";
    }
}
