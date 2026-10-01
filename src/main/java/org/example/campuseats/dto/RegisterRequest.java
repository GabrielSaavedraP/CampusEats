package org.example.campuseats.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank
    private String username;
    @Email @NotBlank
    private String email;
    //lo estoy metiendo por costumbre y buena practica :D
    @Size(min = 8, message ="Tu password debe de tener por lo menos 8 caracteres por mayor seguridad")
    private String password;
}


