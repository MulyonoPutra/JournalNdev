package com.labs.katandev.domain.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Login {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
