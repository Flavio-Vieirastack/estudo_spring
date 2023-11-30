package com.example.email.models;
import java.io.Serializable;
import java.time.LocalDateTime;
import com.example.email.enums.StatusEmail;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "email")
public class EmailModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long emailID;
    private String onwnerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    //Todos são colunas, porem esse esta sendo anotado de forma expicita para usar a definição de text
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime sendDate;
    private StatusEmail statusEmail;
}