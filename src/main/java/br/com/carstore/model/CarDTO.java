package br.com.carstore.model;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class CarDTO {

    private String id = UUID.randomUUID().toString();

    @NotBlank(message = "O nome do carro é obrigatório")
    private String name;

    @NotBlank(message = "A cor do carro é obrigatória")
    private String color;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
