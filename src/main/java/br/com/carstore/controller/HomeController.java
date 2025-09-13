package br.com.carstore.controller;

import br.com.carstore.model.Car;
import br.com.carstore.model.CarDTO;
import br.com.carstore.service.CarService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    private CarService carService;

    public HomeController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("carDTO", new CarDTO());

        return "index";

    }

    @GetMapping("/index")
    public String indexTwo(Model model) {

        model.addAttribute("carDTO", new CarDTO());

        return "index";

    }

    @PostMapping("/cars")
    public String createCar(@ModelAttribute @Valid CarDTO car, BindingResult result, Model model) {

        System.out.println("Recebido: " + car.getName() + " - " + car.getColor());
        System.out.println("ID recebido: " + car.getId());

        if (result.hasErrors()) {
            System.out.println("Erros de validação encontrados.");
            return "index";
        }

        if (car.getId() != null && !car.getId().isEmpty()) {
            System.out.println("Atualizando carro...");
            carService.update(car.getId(), car);
        } else {
            System.out.println("Salvando novo carro...");
            carService.save(car);
        }

        List<CarDTO> cars = carService.findAll();
        System.out.println("Total de carros na lista: " + cars.size());

        model.addAttribute("cars", cars);
        return "dashboard";
    }

    @GetMapping("/cars")
    public String getAllCars(Model model) {

        List<CarDTO> cars = carService.findAll();

        model.addAttribute("cars", cars);

        return "dashboard";

    }

    @PostMapping("/delete/{id}")
    public String deleteCar(@PathVariable String id, Model model) {
        carService.deleteById(id);
        List<CarDTO> cars = carService.findAll();
        model.addAttribute("cars", cars);
        return "dashboard";
    }

    @GetMapping("/edit/{id}")
    public String editCar(@PathVariable String id, Model model) {
        List<CarDTO> cars = carService.findAll();

        for (CarDTO car : cars) {
            if (car.getId().equals(id)) {
                model.addAttribute("carDTO", car);
                break;
            }
        }

        return "index";
    }


}
