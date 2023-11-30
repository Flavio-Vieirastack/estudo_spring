package com.example.email.dtos;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailDTO {
    //Essa é a classe responsavel por receber os dados do usuário, aqui ficam apenas dados que vão ser recebidos ou enviado no corpo da requisição e e a penas aqui que ocorrem as validações
    @NotBlank
    private String onwnerRef;
    @NotBlank
    @Email
    private String emailFrom;
    @NotBlank
    @Email
    private String emailTo;
    @NotBlank
    private String subject;
    @NotBlank
    private String text;
}