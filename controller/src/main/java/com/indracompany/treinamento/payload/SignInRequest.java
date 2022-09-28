package com.indracompany.treinamento.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;


@Data
public class SignInRequest {

    @Email
    private String email;

    @NotEmpty
    private String password;

}
