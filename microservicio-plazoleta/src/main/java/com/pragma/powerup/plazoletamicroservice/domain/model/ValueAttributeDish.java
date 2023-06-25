package com.pragma.powerup.plazoletamicroservice.domain.model;

public class ValueAttributeDish {

    private Long id;
    private String value;

    public ValueAttributeDish(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public ValueAttributeDish(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
