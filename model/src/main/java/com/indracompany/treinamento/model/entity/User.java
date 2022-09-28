package com.indracompany.treinamento.model.entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.persistence.Table;
import lombok.Data;
import javax.persistence.GenerationType;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "users_ja")
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends GenericEntity<Long>{

	private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Email(message = "Please, provide a valid Email")
    @Column
    private String email;

    private String password;

    private boolean active;

	

	


}
