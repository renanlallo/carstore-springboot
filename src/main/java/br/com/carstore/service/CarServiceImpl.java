package br.com.carstore.service;

import br.com.carstore.model.CarDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CarServiceImpl implements CarService  {

    private List<CarDTO> cars;

    public CarServiceImpl(){
        cars = new ArrayList<>();
    }

    @Override
    public List<CarDTO> findAll() {

        return this.cars;

    }

    @Override
    public void save(CarDTO carDTO) {
        if (carDTO.getId() == null || carDTO.getId().isEmpty()) {
            carDTO.setId(UUID.randomUUID().toString());
        }
        cars.add(carDTO);
    }

    @Override
    public void deleteById(String id) {
        cars.removeIf(car -> car.getId().equals(id));
    }

    @Override
    public void update(String id, CarDTO carDTO) {
        boolean updated = false;

        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getId().equals(id)) {
                carDTO.setId(id);
                cars.set(i, carDTO);
                updated = true;
                break;
            }
        }

        if (!updated) {
            save(carDTO);
        }
    }
}


