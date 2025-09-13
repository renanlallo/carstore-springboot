package br.com.carstore.model;

import java.util.List;

public class CarResponseEntity {

    public CarResponseEntity() {

    }

    public CarResponseEntity(List<CarDTO> cars) {
        this.cars = cars;
    }

    private List<CarDTO> cars;

    public List<CarDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarDTO> cars) {
        this.cars = cars;
    }

}
