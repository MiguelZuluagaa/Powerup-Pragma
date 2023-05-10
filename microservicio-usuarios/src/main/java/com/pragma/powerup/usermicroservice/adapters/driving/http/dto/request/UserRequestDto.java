package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRequestDto {
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String name;

    @NotEmpty(message = "El apellido no puede estar vacio")
    private String surname;

    @NotEmpty(message = "El correo no puede estar vacio")
    @Email(message = "El email es invalido")
    private String email;

    @NotEmpty(message = "El celular no puede estar vacio")
    @Size(max = 13, message = "El numero de celular debe tener maximo 13 digitos")
    //@Pattern(regexp = "[0-9]",message = "El numero de celular no puede contener letras, solo numeros")
    @Digits(integer=13, fraction=0, message="El campo telefono debe contener solo números enteros")
    private String phone;

    //@Pattern(regexp = "[a-zA-Z]",message = "El numero de documento no puede contener letras ni simbolos")
    @Digits(integer=20, fraction=0, message="El campo debe contener solo números enteros")
    @NotEmpty(message = "El numero de documento no puede estar vacio")
    private String dni;

    @NotEmpty(message = "La fecha de nacimiento no puede estar vacio")
    @Pattern( regexp = "^(?:3[01]|[12][0-9]|0?[1-9])([\\-/.])(0?[1-9]|1[1-2])\\1\\d{4}$",message = "Formato fecha incorrecto, formato valido (dd-mm-aaaa)")
    private String birdDate;

    @NotEmpty(message = "La clave no puede estar vacio")
    private String password;


    @NotNull(message = "El rol de el usuario no puede estar vacio")
    private RoleEntity idRole;

}
