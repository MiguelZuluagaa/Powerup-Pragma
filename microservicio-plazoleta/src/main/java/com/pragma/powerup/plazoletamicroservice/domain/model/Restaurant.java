package com.pragma.powerup.plazoletamicroservice.domain.model;

public class Restaurant {

    private Long id;
    private String name;
    private String nit;
    private String direction;
    private String phone;
    private String urlLogo;
    private Long idUserOwner;

    public Restaurant(Long id, String name, String nit, String direction, String phone, String urlLogo, Long idUserOwner) {
        this.id = id;
        this.name = name;
        this.nit = nit;
        this.direction = direction;
        this.phone = phone;
        this.urlLogo = urlLogo;
        this.idUserOwner = idUserOwner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public Long getIdUserOwner() {
        return idUserOwner;
    }

    public void setIdUserOwner(Long idUserOwner) {
        this.idUserOwner = idUserOwner;
    }
}
